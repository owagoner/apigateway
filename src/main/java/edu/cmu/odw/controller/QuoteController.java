package edu.cmu.odw.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.odw.model.Author;
import edu.cmu.odw.model.Quote;
import edu.cmu.odw.service.QuoteService;

@RestController
public class QuoteController {

	@Autowired
	private QuoteService quoteService;
	
	@RequestMapping("/api/quote/random")
	public Quote random() {
		return quoteService.randomQuote();
	}

	@RequestMapping(value = "/api/quote/author/{id}")
	public ArrayList<Quote> author(@PathVariable("id") int id) {
		return quoteService.authorQuotesByAuthorId(id);
	}

	@RequestMapping(value = "/api/quote", method = RequestMethod.POST)
	public void saveQuote(@RequestBody Quote quote) {
		System.out.println(quote);
        //String authorName = quote.getAuthor().getName();
		//Author a = authorService.findByName(authorName);

		//if (a == null) {
		//	System.out.println("Saving author");
		//	authorService.save(quote.getAuthor());
		//} else {
		//	quote.setAuthor(a);
		//}

		System.out.println("Saving quote");
		quoteService.save(quote);
	}

	public Quote fallback() {
		Quote q = new Quote();
		Author a = new Author("Confucius");
		q.setText("The superior man is modest in his speech, but exceeds in his actions.");
		q.setAuthor(a);
		return q;
	}
}
