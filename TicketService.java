package in.jay.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.jay.binding.Passenger;
import in.jay.binding.Ticket;
import reactor.core.publisher.Mono;

@Service
public class TicketService {
	
	private final String Book_Ticket_Url = "http://localhost:8080/ticket";
	private final String View_All_Ticket = "http://localhost:8080/tickets";
	
	public Mono<Ticket> bookTicket(Passenger p) {
		
		WebClient webClient = WebClient.create();
		       return webClient.post()
		                       .uri("Book_Ticket_Url")
		                       .body(BodyInserters.fromValue(p))
		                       .retrieve()
		                       .bodyToMono(Ticket.class);
	
	}
	
	public Mono<Ticket[]> viewAllTickets(){
		
		WebClient webClient = WebClient.create();
		
		return webClient.get()
				        .uri("View_All_Tickets")
				        .retrieve()
				        .bodyToMono(Ticket[].class);
		
	}

}
