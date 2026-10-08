package com.vedant.smartcomplaintmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({
        "/",
        "/dashboard",
        "/complaints",
        "/submit",
        "/track",
        "/feedback",
        "/notifications",
        "/profile",
        "/settings",
        "/student",
        "/student/login",
        "/student/register",
        "/admin",
        "/admin/login",
        "/admin/register"
    })
    public String forwardToReact() {
        return "forward:/index.html";
    }
}