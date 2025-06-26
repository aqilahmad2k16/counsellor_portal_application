package org.learnify.com.counsellor_portal_app.dtos;

import lombok.Data;

@Data
public class EnquiryDto {
    private Long enquiryId;
    private String name;
    private String classMode;
    private String enquiryStatus;
    private String courseName;
    private Long phNo;
}
