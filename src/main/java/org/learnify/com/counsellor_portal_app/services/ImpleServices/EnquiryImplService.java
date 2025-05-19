package org.learnify.com.counsellor_portal_app.services.ImpleServices;

import org.apache.catalina.mapper.Mapper;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.FilterDto;
import org.learnify.com.counsellor_portal_app.dtos.responseDtos.DashboardDto;
import org.learnify.com.counsellor_portal_app.exceptions.CounsellorNotFoundException;
import org.learnify.com.counsellor_portal_app.models.ClassMode;
import org.learnify.com.counsellor_portal_app.models.Counsellor;
import org.learnify.com.counsellor_portal_app.models.Enquiry;
import org.learnify.com.counsellor_portal_app.models.EnquiryStatus;
import org.learnify.com.counsellor_portal_app.repositories.CounsellorRepository;
import org.learnify.com.counsellor_portal_app.repositories.EnquiryRepository;
import org.learnify.com.counsellor_portal_app.services.EnquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryImplService implements EnquiryService {
    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

    Logger logger = LoggerFactory.getLogger(EnquiryImplService.class);
    @Autowired
    private Mapper mapper;

    @Override
    public boolean upsertEnquiry(EnquiryDto enquiryDto, Long counsellorId) {
        logger.info("EnquiryImplService upsertEnquiry: started");
        logger.info("declare Enquiry instance and initialized it's value: " + enquiryDto.toString());
        Enquiry enquiry = new Enquiry();
        BeanUtils.copyProperties(enquiryDto, enquiry);
        Counsellor counsellor = counsellorRepository
                                .findById(counsellorId).orElseThrow();
        enquiry.setCounsellor(counsellor);
        Enquiry saveEntity = enquiryRepository.save(enquiry);
        logger.info("EnquiryImplService upsertEnquiry: completed");
        return saveEntity.getId() != null;
    }

    @Override
    public List<EnquiryDto> getAllEnquiry(Long counsellorId) throws CounsellorNotFoundException {
        logger.info("EnquiryImplService getAllEnquiry: started");
        List<Enquiry> enquiries = enquiryRepository.findAllByCounsellorId(counsellorId);
        List<EnquiryDto> enquiryDtoList;
        try {
            enquiryDtoList = enquiries.stream()
                    .map(Enquiry::to).toList();
        } catch (Exception e) {
            logger.error("Counsellor with id " + counsellorId + " not found");
            throw new CounsellorNotFoundException("Counsellor with this Id " + counsellorId + " not found");
        }

        logger.info("EnquiryImplService getAllEnquiry: completed");
        return enquiryDtoList;
    }

    @Override
    public EnquiryDto getEnquiry(Long enquiryId) {
        logger.info("EnquiryImplService getEnquiry: started");
        Enquiry enquiry = enquiryRepository.findById(enquiryId).orElse(null);
        if (enquiry == null) {
            logger.error("EnquiryImplService getEnquiry: enquiry not found");
            throw new NullPointerException("Enquiry with this Id " + enquiryId + " not found");
        }
        logger.info("EnquiryImplService getEnquiry: completed");
        return Enquiry.to(enquiry);
    }

    @Override
    public List<EnquiryDto> filterEnquiry(FilterDto filterDto, Long counsellorId) {
        logger.info("EnquiryImplService filterEnquiry: started");

        /*Specification<Enquiry> specification = Specification.where(null);

        if (filterDto.getClassMode() != null) {
            specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("classMode"), filterDto.getClassMode())));
        }

        if (filterDto.getCourseName() != null) {
            specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("courseName"), filterDto.getCourseName())));
        }

        if (filterDto.getEnquiryStatus() != null) {
            specification = specification.and(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("enquiryStatus"), filterDto.getEnquiryStatus())));
        }

        List<Enquiry> enquiries = enquiryRepository.findAll(specification);
        if (enquiries.isEmpty()) {
            logger.error("EnquiryImplService filterEnquiry: enquiries not found");
            throw new NoSuchElementException("No Such Enquiries is Found");
        }*/

        //QBE
        Enquiry enquiry = new Enquiry();

        if(filterDto.getClassMode() != null && filterDto.getClassMode().isEmpty()) {
            enquiry.setClassMode(ClassMode.valueOf(filterDto.getClassMode()));
        }

        if(filterDto.getEnquiryStatus() != null && filterDto.getEnquiryStatus().isEmpty()){
            enquiry.setEquiryStatus(EnquiryStatus.valueOf(filterDto.getEnquiryStatus()));
        }

        if(filterDto.getCourseName() != null && filterDto.getCourseName().isEmpty()){
            enquiry.setCourseName(filterDto.getCourseName());
        }

        Counsellor counsellor= counsellorRepository.findById(counsellorId).orElseThrow();
        enquiry.setCounsellor(counsellor);
        List<Enquiry> totalEnquiry = enquiryRepository.findAll(Example.of(enquiry));

        logger.info("EnquiryImplService filterEnquiry: completed");
        return totalEnquiry.stream()
                .map(e -> {
                    EnquiryDto dto = new EnquiryDto();
                    BeanUtils.copyProperties(e, dto);
                    return dto;
                }).toList();
    }

    @Override
    public DashboardDto getDashboard(Long counsellorId) {
        logger.info("EnquiryImplService getDashboard: started");
        List<Enquiry> enquiryList = enquiryRepository.findAllByCounsellorId(counsellorId);
        logger.info("Found {} enquiries for counsellorId: {}", enquiryList.size(), counsellorId);
        Long totalEnquiryCount = (long) enquiryList.size();
        Long openEnquiryCount = enquiryList.stream()
                .filter(enquiry -> enquiry.getEquiryStatus().equals(EnquiryStatus.OPEN))
                .count();
        Long enrolledEnquiryCount = enquiryList.stream()
                .filter(enquiry -> enquiry.getEquiryStatus().equals(EnquiryStatus.ENROLLED))
                .count();
        Long lostEnquiryCount = enquiryList.stream()
                .filter(e -> e.getEquiryStatus().equals(EnquiryStatus.LOST))
                .count();
        /*DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setTotalEnquiries(totalEnquiryCount);
        dashboardDto.setOpenEnquiries(openEnquiryCount);
        dashboardDto.setEnrolledEnquiries(enrolledEnquiryCount);
        dashboardDto.setLostEnquiries(lostEnquiryCount);*/

        logger.info("EnquiryImplService getDashboard: completed");
        return DashboardDto.builder()
                .totalEnquiries(totalEnquiryCount)
                .openEnquiries(openEnquiryCount)
                .enrolledEnquiries(enrolledEnquiryCount)
                .lostEnquiries(lostEnquiryCount)
                .build();
    }
}
