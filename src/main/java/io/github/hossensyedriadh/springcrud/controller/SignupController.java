package io.github.hossensyedriadh.springcrud.controller;

import io.github.hossensyedriadh.springcrud.entity.User;
import io.github.hossensyedriadh.springcrud.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping("/sign-up")
public class SignupController {
    private final SignupService signupService;

    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @GetMapping
    public ModelAndView signup() {
        return new ModelAndView("utils/sign-up");
    }

    @PostMapping("/")
    public ModelAndView doSignup(@Valid @ModelAttribute User user) {
        this.signupService.signup(user);

        return new ModelAndView(new RedirectView("/login"));
    }
}
