package org.learnify.com.counsellor_portal_app.services.ImpleServices;

import org.learnify.com.counsellor_portal_app.dtos.requestDtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.models.Counsellor;
import org.learnify.com.counsellor_portal_app.repositories.CounsellorRepository;
import org.learnify.com.counsellor_portal_app.services.CounsellorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CounsellorImpleService implements CounsellorService {
    @Autowired
    private CounsellorRepository counsellorRepository;

    Logger logger = Logger.getLogger(CounsellorImpleService.class.getName());

    @Override
    public CounsellorDto counsellorLogin(String email, String pwd) {
        logger.info("Start of counsellor login method: CousellorService");
        Optional<Counsellor> counselloroptional = counsellorRepository.findByEmailAndPassword(email, pwd);
        if(counselloroptional.isPresent()) {
            logger.info("Found counsellor with email: " + email + " and password: " + pwd);
            CounsellorDto counsellorDto = new CounsellorDto();
            Counsellor counsellor = counselloroptional.get();
            BeanUtils.copyProperties(counsellor, counsellorDto);
            return counsellorDto;
        }
        logger.info("End of counsellor login method: counsellorRepository");
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
