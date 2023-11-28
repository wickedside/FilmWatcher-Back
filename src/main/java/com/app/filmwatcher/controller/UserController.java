package com.app.filmwatcher.controller;

import com.app.filmwatcher.model.User;
import com.app.filmwatcher.repository.RoleRepository;
import com.app.filmwatcher.repository.UserRepository;
import com.app.filmwatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
   // private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        //this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/loginPage")
    public String auth(){
        return "login";
    }

    @GetMapping("/regPage")
    public String getRegPage(Model model){
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/regSave")
    public String saveReg(Model model, @ModelAttribute("userForm") User userForm){
        User user = new User(userForm.getUsername(), userForm.getPassword(), userForm.getEmail());
        user.setRole(roleRepository.findRoleByRoleId(2L));
        userRepository.save(user);
        System.out.println(user.getUsername());
        return "redirect:/api/loginPage";
    }

    @GetMapping("/getMainPage")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/logoutPage")
    public String getLogoutPage(){
        return "logoutPage";
    }

    @GetMapping("/moviePage")
    public String getMoviePage(){
        return "movie";
    }

 /*   @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("User logged in successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok("User logged out successfully");
    }*/


}


