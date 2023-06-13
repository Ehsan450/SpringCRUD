package io.github.hossensyedriadh.springcrud.controller;

import io.github.hossensyedriadh.springcrud.entity.User;
import io.github.hossensyedriadh.springcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView findAll(Model model) {
        List<User> allUsers = userService.users();
        model.addAttribute("users", allUsers);

        return new ModelAndView("users/users");
    }

    @GetMapping("/save")
    public ModelAndView saveForm(Model model) {
        model.addAttribute("user", new User());

        return new ModelAndView("users/addUsers");
    }

    @GetMapping("/{username}")
    public ModelAndView find(@PathVariable("username") String username, Model model) {
        Optional<User> user = userService.user(username);

        user.ifPresent(value -> model.addAttribute("user", value));

        return new ModelAndView("users/user");
    }

    @PostMapping("/")
    public ModelAndView save(@ModelAttribute User user) {
        User savedUser = this.userService.save(user);

        return new ModelAndView(new RedirectView("/users/" + savedUser.getUsername()));
    }
}
