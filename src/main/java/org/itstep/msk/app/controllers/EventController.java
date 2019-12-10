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

import java.util.*;

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
    public String showEvent(@PathVariable Integer id, Model model) {
        Optional<Event> event = eventRepository.findById(id);

        model.addAttribute("event", event.orElse(new Event()));

        return "event";
    }

    @GetMapping("/{id}/users")
    public String showEventAttendees(@PathVariable Integer id, Model model) {
        Optional<Event> event = eventRepository.findById(id);

        model.addAttribute("event", event.orElse(new Event()));

        Set<Integer> users = eventRepository.findUsersForEvent(event.get().getId());
        List<String> intString = new ArrayList<>();

        for (Integer i : users) {
            intString.add(String.valueOf(i));
        }

        String usersString = String.join(", ", intString);
        model.addAttribute("users", usersString);

        return "eventattendees";
    }

    @PostMapping("/register/{eventid}")
    public String register(
            /*@ModelAttribute User user,
            @ModelAttribute Event event*/
            @RequestParam Integer userid,
            @RequestParam Integer eventid
    ) {
        Optional<User> userFromRepo = userRepository.findById(userid);
        Optional<Event> eventFromRepo = eventRepository.findById(eventid);

        if (userFromRepo.isPresent() && eventFromRepo.isPresent()){
            eventFromRepo.get().addUser(userFromRepo.get());
            userFromRepo.get().addEvent(eventFromRepo.get());
            eventRepository.save(eventFromRepo.get());
            userRepository.save(userFromRepo.get());
        } else {
            return "error";
        }

        return "redirect:/events/" + eventFromRepo.get().getId();
    }
}
