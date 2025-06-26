package org.learnify.com.counsellor_portal_app.dtos;

import lombok.Data;

@Data
public class CounsellorDto {
    private Long counsellorId;
    private String name;
    private String email;
    private Long phoneNumber;
    private String pwd;
}
