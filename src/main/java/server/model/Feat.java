package server.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "feats")
public class Feat {
    @Id
    private long id;
    private int points;
    @Column(name="action_id")
    private int actionId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="time_completed")
    Date timeCompleted;
    @ManyToOne
    private User user;

    public Feat() {
    }

    public Feat(long id, int points, int actionId, Date timeCompleted) {
        this.id = id;
        this.points = points;
        this.actionId = actionId;
        this.timeCompleted=timeCompleted;
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
    public void setPoints(int points)
    {
        this.points=points;
    }
    public int getActionId() {
        return actionId;
    }
    public void setActionId(int actionId)
    {
        this.actionId=actionId;
    }
    public void setTimeCompleted(Date timeCompleted) {
        this.timeCompleted=timeCompleted;
    }
    public Date getTimeCompleted() {
        return timeCompleted;
    }
}
