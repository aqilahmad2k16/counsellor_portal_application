package org.learnify.com.counsellor_portal_app.dtos.requestDtos;

import lombok.Data;

@Data
public class CounsellorDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String pwd;
}
