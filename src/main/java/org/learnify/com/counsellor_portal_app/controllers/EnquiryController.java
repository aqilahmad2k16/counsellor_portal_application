package org.learnify.com.counsellor_portal_app.controllers;

import org.learnify.com.counsellor_portal_app.dtos.requestDtos.EnquiryDto;
import org.learnify.com.counsellor_portal_app.services.EnquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enquireis/v1/api")
public class EnquiryController {
    @Autowired
    private EnquiryService enquiryService;

    Logger logger = LoggerFactory.getLogger(EnquiryController.class);

    @PostMapping("/enquiry/{counsellorId}")
    public ResponseEntity<?> upsertEnquiry(@RequestBody EnquiryDto enquiryDto, @PathVariable Long counsellorId) {
        logger.info("adding enquiry: " + enquiryDto.toString() + "by counsellorId: " + counsellorId);
        boolean isEquirySaved = enquiryService.upsertEnquiry(enquiryDto, counsellorId);
        if(isEquirySaved) {
            logger.info("Enquiry successfully uploaded: " + enquiryDto.toString() + "by counsellorId: " + counsellorId);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
