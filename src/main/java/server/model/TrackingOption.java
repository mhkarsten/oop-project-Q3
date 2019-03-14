package server.model;

import javax.persistence.*;

@Entity
@Table(name = "tracking_options")
public class TrackingOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private int score;

    protected TrackingOption(){};

    public TrackingOption(String name, String description, int score) {
        this.name = name;
        this.description = description;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
