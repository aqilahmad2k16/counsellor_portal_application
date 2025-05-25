package org.learnify.com.counsellor_portal_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.learnify.com.counsellor_portal_app.dtos.requestDtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.services.CounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counsellor/v1/api")
public class CounsellorController {

    private CounsellorService counsellorService;

    @Autowired
    public CounsellorController(CounsellorService counsellorService) {
        this.counsellorService = counsellorService;
    }

    /*
    * we have to show empty form fields to UI, create method that bind java object to the form fields in the form
     */
    @GetMapping("/")
    public String index(Model model) {
        //to form binding java object to ui
        CounsellorDto counsellorDto = new CounsellorDto();
        model.addAttribute("counsellor", counsellorDto);
        return "index";
    }

    /*
    * once, I will show the gui of the counsellor with empty field, we have to do login
    * once, login is completed we have to show current login counsellor dashboard report
     */

    @PostMapping("/login")
    public String login(@RequestBody CounsellorDto counsellorDto,
                        HttpServletRequest request,
                        Model model) {
        CounsellorDto counsellorDto1 = counsellorService.counsellorLogin(counsellorDto.getEmail(), counsellorDto.getPwd());

        if(counsellorDto1 == null) {
            model.addAttribute("emsg: ", "Invalid email or password");
            return "index";
        } else {
            //after successful login, store id in the session
            HttpSession session = request.getSession(true);
            session.setAttribute("counsellor_Id", counsellorDto1.getId());
        }
        return "redirect: dashboard";
    }
}
