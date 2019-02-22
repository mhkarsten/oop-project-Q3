package Client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private Long id;
    private String name;

    public Value() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.quote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}