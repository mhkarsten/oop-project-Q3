package server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feats")
public class Feat {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_completed")
    Date timeCompleted;
    @Id
    private long id;
    private int points;
    @Column(name = "action_id")
    private int actionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Feat() {
    }

    public Feat(long id, int points, int actionId, Date timeCompleted) {
        this.id = id;
        this.points = points;
        this.actionId = actionId;
        this.timeCompleted = timeCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public Date getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(Date timeCompleted) {
        this.timeCompleted = timeCompleted;
    }
}
