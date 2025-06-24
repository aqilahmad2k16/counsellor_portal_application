//package org.learnify.com.counsellor_portal_app.services;
//
//import org.learnify.com.counsellor_portal_app.dtos.EnquiryDto;
//import org.learnify.com.counsellor_portal_app.dtos.FilterDto;
//import org.learnify.com.counsellor_portal_app.dtos.DashboardDto;
//import org.learnify.com.counsellor_portal_app.exceptions.CounsellorNotFoundException;
//
//import java.util.List;
//
//public interface EnquiryService {
//    public boolean upsertEnquiry(EnquiryDto enquiryDto, Long counsellorId);
//    public List<EnquiryDto> getAllEnquiry(Long counsellorId) throws CounsellorNotFoundException;
//    public EnquiryDto getEnquiry(Long enquiryId);
//    public List<EnquiryDto> filterEnquiry(FilterDto filterDto);
//    public DashboardDto getDashboard(Long counsellorId);
//}
