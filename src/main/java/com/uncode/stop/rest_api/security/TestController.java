package com.uncode.stop.rest_api.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/personal")
    public String personal() {
        return "Hello Personal";
    }

    @GetMapping("/habitante")
    public String habitante() {
        return "Hello Habitante";
    }

}
