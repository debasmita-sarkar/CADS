package com.cads.controllers;

import com.cads.db.models.Signup;
import com.cads.services.SignupService;
import com.cads.utils.URLConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignupController {
    @Autowired
    private SignupService signupService;

    @RequestMapping(value = URLConstants.SIGNUP, method = RequestMethod.POST)
    @CrossOrigin("*")
    public @ResponseBody Signup postNewSignup(@Validated @RequestBody Signup signup) {
        return signup;
    }
}
