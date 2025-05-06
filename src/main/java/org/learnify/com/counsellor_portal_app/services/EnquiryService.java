package org.learnify.com.counsellor_portal_app.services;

import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.FilterDto;
import org.learnify.com.counsellor_portal_app.dtos.responseDtos.DashboardDto;
import org.learnify.com.counsellor_portal_app.exceptions.CounsellorNotFoundException;
import org.learnify.com.counsellor_portal_app.models.Enquiry;

import java.util.List;

public interface EnquiryService {
    public boolean upsertEnquiry(EnquiryDto enquiryDto, Long counsellorId);
    public List<EnquiryDto> getAllEnquiry(Long counsellorId) throws CounsellorNotFoundException;
    public EnquiryDto getEnquiry(Long enquiryId);
    public List<EnquiryDto> filterEnquiry(FilterDto filterDto);
    public DashboardDto getDashboard(Long counsellorId);
}
