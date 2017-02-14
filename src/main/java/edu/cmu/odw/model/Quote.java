package edu.cmu.odw.model;


public class Quote {

    private Long id;
    private String text;
    private String source;
    private Long authorId;
    private Author author;
    

    public Quote() {}

    public Quote(String text, String source, Author author) {
        this.text = text;
        this.source = source;
        this.author = author;
    }
    
    public Quote(String text, String source, Long authorID) {
        this.text = text;
        this.source = source;
        this.id = authorID;
    }
    
    @Override
    public String toString() {
        return String.format("Quote[id=%d, text='%s', by='%s']", this.id, this.text, this.author.getName());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
    
    
}
