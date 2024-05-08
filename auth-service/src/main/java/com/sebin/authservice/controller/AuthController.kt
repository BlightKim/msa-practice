package com.sebin.authservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {
    @GetMapping("/login/oauth2/code/{provider}")
    fun login(): String {
        return "login"
    }


}
