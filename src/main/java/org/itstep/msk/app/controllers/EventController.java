package org.itstep.msk.app.controllers;

import org.itstep.msk.app.entities.Event;
import org.itstep.msk.app.entities.User;
import org.itstep.msk.app.repositories.EventRepository;
import org.itstep.msk.app.repositories.UserRepository;
import org.itstep.msk.app.services.impl.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventsService eventsService;

    @GetMapping("/newevent")
    public String getEventRegistration(Model model) {
        model.addAttribute("event", new Event());

        return "newevent";
    }

    @PostMapping("/newevent")
    public String createEvent(@ModelAttribute Event event){
        eventsService.createEvent(event);

        return "redirect:/events/" + event.getId();
    }

    @GetMapping
    public ModelAndView getAllEvents(Map<String, Object> model) {
        model.put("events", eventsService.getAllEvents());

        return new ModelAndView("events", model);
    }

    @GetMapping("/{id}")
    public String showApartment(@PathVariable Integer id, Model model) {
        Optional<Event> event = eventRepository.findById(id);

        model.addAttribute("event", event.orElse(new Event()));

        return "event";
    }

    @PostMapping("/register/{eventid}")
    public String register(
            @ModelAttribute Integer userid,
            @ModelAttribute Integer eventid) {
        Set<User> users = new HashSet<>();
        Optional<User> user = userRepository.findById(userid);

        if (user.isPresent()){
            users.add(user.get());
        } else {
            return "error";
        }

        Optional<Event> event = eventRepository.findById(eventid);

        if (event.isPresent()){
            event.get().setUsers(users);
            eventRepository.save(event.get());
        } else {
            return "error";
        }

        return "redirect:/events/" + event.get().getId();
    }
}
