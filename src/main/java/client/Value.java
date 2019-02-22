package client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private Long id;
    private String name;

    public Value() {
    }


    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}