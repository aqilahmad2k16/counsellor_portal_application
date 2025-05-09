package org.learnify.com.counsellor_portal_app.controllers;

import jakarta.validation.Valid;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.FilterDto;
import org.learnify.com.counsellor_portal_app.dtos.responseDtos.DashboardDto;
import org.learnify.com.counsellor_portal_app.exceptions.CounsellorNotFoundException;
import org.learnify.com.counsellor_portal_app.exceptions.DashboardNotFoundException;
import org.learnify.com.counsellor_portal_app.services.EnquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enquireis/v1/api")
public class EnquiryController {
    @Autowired
    private EnquiryService enquiryService;

    Logger logger = LoggerFactory.getLogger(EnquiryController.class);

    @PostMapping("/enquiry/{counsellorId}")
    public ResponseEntity<?> upsertEnquiry(@Valid @RequestBody EnquiryDto enquiryDto, @PathVariable Long counsellorId) {
        logger.info("adding enquiry: " + enquiryDto.toString() + "by counsellorId: " + counsellorId);
        boolean isEquirySaved = enquiryService.upsertEnquiry(enquiryDto, counsellorId);
        if(isEquirySaved) {
            logger.info("Enquiry successfully uploaded: " + enquiryDto.toString() + "by counsellorId: " + counsellorId);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }
        logger.info("Enquiry is not uploaded: " + enquiryDto.toString());
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    /*
    * %%%%%% This api gives the dashboard of a counsellor who is login
     */
    @GetMapping("/counsellor-dashboard")
    public ResponseEntity<DashboardDto> getCounsellorDashboard(@RequestParam Long counsellorId) throws DashboardNotFoundException {
        logger.info("Request received for the counsellor dashboard with ID: {}",  counsellorId);
        DashboardDto dashboardDto = enquiryService.getDashboard(counsellorId);
        if(dashboardDto == null) {
            logger.warn("Dashboard not found for counsellor ID: {}", counsellorId);
            throw new DashboardNotFoundException("Dashboard not found for the Id: " + counsellorId);
        }

        logger.info("Returning dashboard for the counsellor ID: {}",  counsellorId);
        return ResponseEntity.ok(dashboardDto);
    }

    /*
    * %%%%%%%%%%%%%%%% this api is used to get all enquiries based on the filter will apply
     */
    @GetMapping("/filter")
    public ResponseEntity<List<EnquiryDto>> applyFilter(@RequestBody FilterDto filterDto) {
        logger.info("Request received for the filter");
        List<EnquiryDto> EnquiryDtos = enquiryService.filterEnquiry(filterDto);
        logger.info("Returning: " + EnquiryDtos.size() + "EnquiryDtos");
        return ResponseEntity.ok(EnquiryDtos);
    }
}
