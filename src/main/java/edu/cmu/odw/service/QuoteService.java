package edu.cmu.odw.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import edu.cmu.odw.model.Quote;

@Service
public interface QuoteService {
	
	public Quote randomQuote();
	
    public ArrayList<Quote> authorQuotesByAuthorId(long id);
    
    public ArrayList<Quote> allQuotes();
    
    public Quote save(Quote quote);
    
}
