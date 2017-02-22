package edu.cmu.odw.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import edu.cmu.odw.model.Author;
import edu.cmu.odw.model.Quote;
import edu.cmu.odw.service.QuoteService;

@EnableCircuitBreaker
@RestController
public class QuoteController {

	@Autowired
	private QuoteService quoteService;
	
	@HystrixCommand(fallbackMethod = "fallbackQuote")
	@RequestMapping("/api/quote/random")
	public Quote random() {
		return quoteService.randomQuote();
	}
	
	@HystrixCommand(fallbackMethod = "fallbackAuthor")
	@RequestMapping(value = "/api/quote/author/{id}")
	public ArrayList<Quote> author(@PathVariable("id") int id) {
		return quoteService.authorQuotesByAuthorId(id);
	}

	@RequestMapping(value = "/api/quote", method = RequestMethod.POST)
	public void saveQuote(@RequestBody Quote quote) {
		System.out.println(quote);
		System.out.println("Saving quote");
		quoteService.save(quote);
	}

	public ArrayList<Quote> fallbackAuthor(){
		ArrayList<Quote> list = new ArrayList<Quote>();
		Quote q1 = new Quote();
		Author a1 = new Author("Confucius");
		q1.setText("The superior man is modest in his speech, but exceeds in his actions.");
		q1.setAuthor(a1);
		
		Quote q2 = new Quote();
		Author a2 = new Author("Other Fallback");
		q2.setText("Fallback text.");
		q2.setAuthor(a2);
		
		list.add(q1);
		list.add(q2);
		
		return list;
	}
	
	public Quote fallbackQuote() {
		Quote q = new Quote();
		Author a = new Author("Confucius");
		q.setText("The superior man is modest in his speech, but exceeds in his actions.");
		q.setAuthor(a);
		return q;
	}
}
