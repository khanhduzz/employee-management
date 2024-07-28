package com.fpt.fsa.employee_management.controllers;

import com.fpt.fsa.employee_management.dtos.response.AuthResponseDto;
import com.fpt.fsa.employee_management.entities.Login;
import com.fpt.fsa.employee_management.services.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping({"/", "/login"})
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/employee/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestBody @Valid Login request, BindingResult bindingResult, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/employee/login");
        }
        AuthResponseDto response = authService.login(request.account(), request.password());
        session.setAttribute("response", response);
        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout() {
        return "/employee/login";
    }

}
