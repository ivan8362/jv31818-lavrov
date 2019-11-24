package org.itstep.msk.app.controllers;

import org.itstep.msk.app.entities.Event;
import org.itstep.msk.app.services.impl.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/v1/events")
public class EventController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/newevent")
    public String getEventRegistration(Model model) {
        model.addAttribute("event", new Event());

        return "newevent";
    }

    @PostMapping("/newevent")
    public String createEvent(){
        return "redirect:/v1/events";
    }

    @GetMapping
    public ModelAndView getAllUsers(Map<String, Object> model) {
        model.put("events", eventsService.getAllEvents());

        return new ModelAndView("events", model);
    }
}
