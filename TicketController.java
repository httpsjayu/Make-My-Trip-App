package in.jay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.jay.binding.Passenger;
import in.jay.binding.Ticket;
import in.jay.service.TicketService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Controller
public class TicketController {

    @Autowired
    private TicketService service;

    // Show the booking form
    @GetMapping("/")
    public String bookTicket(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "index"; // index.html
    }

    // Handle the booking form POST
    @PostMapping("/ticket")
    public Mono<String> handleBookTicket(Model model, Passenger p) {
        return service.bookTicket(p)
            .map(ticket -> {
                model.addAttribute("msg", "Ticket booked successfully");
                return "index"; // Return view name
            });
    }

    // Show all tickets
    @GetMapping("/tickets")
    public Mono<String> viewAllTickets(Model model) {
        return service.viewAllTickets()
            .map(tickets -> {
                model.addAttribute("tickets", tickets);
                return "tickets"; // Return tickets.html
            });
    }
}
