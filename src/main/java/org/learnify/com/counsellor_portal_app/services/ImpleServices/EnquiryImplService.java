package org.learnify.com.counsellor_portal_app.services.ImpleServices;

import org.apache.catalina.mapper.Mapper;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.FilterDto;
import org.learnify.com.counsellor_portal_app.dtos.responseDtos.DashboardDto;
import org.learnify.com.counsellor_portal_app.exceptions.CounsellorNotFoundException;
import org.learnify.com.counsellor_portal_app.models.Counsellor;
import org.learnify.com.counsellor_portal_app.models.Enquiry;
import org.learnify.com.counsellor_portal_app.repositories.CounsellorRepository;
import org.learnify.com.counsellor_portal_app.repositories.EnquiryRepository;
import org.learnify.com.counsellor_portal_app.services.EnquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Counsellor> counsellorOptional = counsellorRepository.findById(counsellorId);
        Enquiry enquiry = null;
        if(counsellorOptional.isPresent()) {
            enquiry = Enquiry.from(enquiryDto, counsellorOptional.get());
        } else {
            logger.warn("Counsellor with id " + counsellorId + " not found");
            enquiry = Enquiry.fromIfNull(enquiryDto, counsellorId);
        }

        counsellorRepository.save(enquiry.getCounsellor());
        enquiryRepository.save(enquiry);
        logger.info("EnquiryImplService upsertEnquiry: completed");
        return true;
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
        return null;
    }

    @Override
    public List<EnquiryDto> filterEnquiry(FilterDto filterDto) {
        return List.of();
    }

    @Override
    public DashboardDto getDashboard(Long counsellorId) {
        return null;
    }
}
