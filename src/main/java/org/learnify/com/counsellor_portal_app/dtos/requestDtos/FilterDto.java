package org.learnify.com.counsellor_portal_app.dtos.requestDtos;

import lombok.Data;
import org.learnify.com.counsellor_portal_app.models.ClassMode;
import org.learnify.com.counsellor_portal_app.models.EnquiryStatus;

@Data
public class FilterDto {
    private String classMode;
    private String enquiryStatus;
    private String courseName;
}
