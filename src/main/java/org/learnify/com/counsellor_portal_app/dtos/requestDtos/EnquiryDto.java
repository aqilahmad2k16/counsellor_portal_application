package org.learnify.com.counsellor_portal_app.dtos.requestDtos;

import lombok.Data;
import org.learnify.com.counsellor_portal_app.models.ClassMode;
import org.learnify.com.counsellor_portal_app.models.EnquiryStatus;

@Data
public class EnquiryDto {
    private String id;
    private String name;
    private String classMode;
    private String enquiryStatus;
    private String courseName;
    private String phNo;
}
