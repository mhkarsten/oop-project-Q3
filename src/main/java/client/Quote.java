package client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Quote() {
    }


    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id + '\'' +
                ", content=" + content +
                '}';
    }
}