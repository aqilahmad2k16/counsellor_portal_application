package org.learnify.com.counsellor_portal_app.services.ImpleServices;

import org.learnify.com.counsellor_portal_app.dtos.requestDtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.services.CounsellorService;
import org.springframework.stereotype.Service;

@Service
public class CounsellorImpleService implements CounsellorService {
    @Override
    public CounsellorDto counsellorLogin(String email, String pwd) {
        return null;
    }

    @Override
    public boolean counsellorRegister(CounsellorDto counsellorDto) {
        return false;
    }

    @Override
    public boolean isEmailUnique(String email) {
        return false;
    }
}
