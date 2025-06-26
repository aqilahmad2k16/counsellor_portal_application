package org.learnify.com.counsellor_portal_app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.learnify.com.counsellor_portal_app.dtos.CounsellorDto;
import org.learnify.com.counsellor_portal_app.dtos.DashboardDto;
import org.learnify.com.counsellor_portal_app.services.CounsellorService;
import org.learnify.com.counsellor_portal_app.services.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/counsellor/v1/api")
public class CounsellorController {

    private CounsellorService counsellorService;
    private EnquiryService enquiryService;

    @Autowired
    public CounsellorController(CounsellorService counsellorService
    , EnquiryService enquiryService) {
        this.counsellorService = counsellorService;
        this.enquiryService = enquiryService;
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
            session.setAttribute("counsellor_Id", counsellorDto1.getCounsellorId());
        }
        return "redirect: dashboard";
    }

    /*
    * when the login is success, show dashboard to the user
     */
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        Long counsellorId = (Long) session.getAttribute("counsellor_Id");
        DashboardDto counsellorDto = enquiryService.getDashboard(counsellorId);
        model.addAttribute("dashboardInfo", counsellorDto);
        return "dashboardReportView";
    }

    /*
    * for registration, first we have to bind the data to the model with empty field
    * then save the registration data
     */
    @GetMapping("/register")
    public String register(Model model) {
        CounsellorDto counsellorDto = new CounsellorDto();
        model.addAttribute("counsellor", counsellorDto);
        return "registerView";
    }

    @PostMapping("/register")
    public String handleRegistration(CounsellorDto counsellorDto, Model model) {
        boolean status = counsellorService.isEmailUnique(counsellorDto.getEmail());
        if(status) {
            boolean isRegister = counsellorService.counsellorRegister(counsellorDto);
            if(isRegister) {
                model.addAttribute("msg: ", "Successfully registered");
            } else {
                model.addAttribute("emsg: ", "Registration failed");
            }
        } else {
            model.addAttribute("msg: ", "Email already used");
        }

        return "registerView";
    }
}
