package org.learnify.com.counsellor_portal_app.services;

import org.learnify.com.counsellor_portal_app.dtos.CounsellorDto;

public interface CounsellorService {
    public CounsellorDto counsellorLogin(String email, String pwd);
    public boolean counsellorRegister(CounsellorDto counsellorDto);
    public boolean isEmailUnique(String email);
}
