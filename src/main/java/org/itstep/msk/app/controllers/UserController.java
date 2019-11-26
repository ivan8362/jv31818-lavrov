package org.itstep.msk.app.controllers;

import org.itstep.msk.app.services.EventManagerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private EventManagerApi eventManagerService;

    @GetMapping("/all")
    public ModelAndView getAllUsers(Map<String, Object> model) {
        model.put("users", eventManagerService.getAllUsers());

        return new ModelAndView("users", model);
    }
}

