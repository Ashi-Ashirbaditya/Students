package net.ads.student.controller;

import net.ads.student.dto.TeacherRegis;
import net.ads.student.model.Teachers;
import net.ads.student.service.TeacherServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class TeacherController {

    @Autowired
    private TeacherServ teacherserv;

    // Registration form view
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new TeacherRegis());
        return "registration";
    }

    // Handle Teacher registration
    @PostMapping("/registration")
    public String registerTeacher(@ModelAttribute("user") TeacherRegis teacherregis, Model model) {
        try{
            Teachers teacher = teacherserv.save(teacherregis);
            model.addAttribute("success", true);
            return "registration";}
        catch(IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }


    // Login form view
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/user/profile")
    public String userProfile(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("roles", userDetails.getAuthorities());
        } else {
            model.addAttribute("username", "Guest");
            model.addAttribute("roles", "None");
        }
        return "teacher-profile";
    }
}

