package com.example.demo.controllers;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String info(@ModelAttribute("user") User user, ModelMap model, Principal principal) {
        model.addAttribute("authUser", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/info";
    }

//    @PostMapping()
//    public String create(@ModelAttribute("user") User user,
//                         @RequestParam(value = "checkbox_roles", required = false) Long[] rolesId) {
//        Set<Role> roles = new HashSet<>();
//        for (Long role : rolesId) {
//            roles.add(roleService.getRoleById(role));
//        }
//        user.setRoles(roles);
//        userService.add(user);
//        return "redirect:/admin";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user,
//                         @RequestParam(value = "checkbox_roles", required = false) Long[] rolesId) {
//        Set<Role> roles = new HashSet<>();
//        for (Long role : rolesId) {
//            roles.add(roleService.getRoleById(role));
//        }
//        user.setRoles(roles);
//        userService.update(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }
}
