package edu.cmu.odw.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import edu.cmu.odw.model.Author;
import edu.cmu.odw.model.Quote;

@Component
public class QuoteServiceImpl implements QuoteService {

    Random random = new Random();
    
    @Value("${service.quote.uri}")
	private String quoteServiceUri;
    
    @Autowired
    private AuthorService authorService;
    
    @Override
    public Quote randomQuote() {
    	RestTemplate rt = new RestTemplate(); 
		String uri = quoteServiceUri + "/api/quote/random";
    	
		Quote quote = rt.getForObject(uri, Quote.class);
		quote.setAuthor(authorService.findById(quote.getAuthorId()));
		return quote;
    }

	@Override
	public ArrayList<Quote> authorQuotesByAuthorId(long id) {
		RestTemplate rt = new RestTemplate(); 
		String uri = quoteServiceUri + "/api/quote/author/" + id;
		
		ResponseEntity<Quote[]> re = rt.getForEntity(uri, Quote[].class);
		
		ArrayList<Quote> quotes = new ArrayList<Quote>(Arrays.asList(re.getBody()));    	
    	Author author = authorService.findById(id);
    	
    	for(Quote q: quotes){
    		q.setAuthor(author);
    	}
    	
    	return quotes;
	}

	@Override
	public ArrayList<Quote> allQuotes() {
		RestTemplate rt = new RestTemplate();
		
		String uri = quoteServiceUri + "/api/quote";		
		
		ResponseEntity<Quote[]> re = rt.getForEntity(uri, Quote[].class);		
		
		ArrayList<Quote> quotes = new ArrayList<Quote>(Arrays.asList(re.getBody()));
		
		for(Quote q : quotes){
			q.setAuthor(authorService.findById(q.getAuthorId()));			
		}
		
		return quotes;
	}

	@Override
	public Quote save(Quote quote) {
		Author rAuthor = quote.getAuthor();
		Author author = authorService.findByName(rAuthor.getName());
		
		if(author == null){
			//Save author
			author = authorService.save(rAuthor);
		}
		quote.setAuthorId(author.getId());
		
		RestTemplate rt = new RestTemplate();
		String uri = quoteServiceUri + "/api/quote";
		
		ResponseEntity<Quote> re = rt.postForEntity(uri, quote, Quote.class);
		Quote response = re.getBody();
		return response;
	}

}
