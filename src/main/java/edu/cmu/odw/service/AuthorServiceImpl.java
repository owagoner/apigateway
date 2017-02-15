package edu.cmu.odw.service;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import edu.cmu.odw.model.Author;

@Component
public class AuthorServiceImpl implements AuthorService {

	@Value("${service.author.uri}")
	private String authorServiceUri;
	
	@Override
	public Author findByName(String name) {
		RestTemplate rt = new RestTemplate(); 
		URI apiUrl = UriComponentsBuilder.fromUriString(authorServiceUri)
				.path("/api/author/byname")
				.queryParam("query", name)
				.build()
				.toUri();
		Author author = rt.getForObject(apiUrl, Author.class);
		
		return author;
	}

	@Override
	public Author findById(Long id) {
		RestTemplate rt = new RestTemplate(); 
		String uri = authorServiceUri + "/api/author/" + id;
		Author author = rt.getForObject(uri, Author.class);
		return author;
	}

	@Override
	public Author save(Author author) {
		RestTemplate rt = new RestTemplate();
		String uri = authorServiceUri + "/api/author";
		
		ResponseEntity<Author> re = rt.postForEntity(uri, author, Author.class);
		Author response = re.getBody();
		
		return response;
	}

	
	
}