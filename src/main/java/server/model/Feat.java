package server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feats")
@SequenceGenerator(name = "feat_seq", initialValue = 1, allocationSize = 1)
public class Feat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feat_seq")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_completed")
    Date timeCompleted;

    private int points;
    @Column(name = "action_id")
    private int actionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Feat() {
    }

    public Feat(long id, int points, int actionId, Date timeCompleted, User user) {
        this.id = id;
        this.points = points;
        this.actionId = actionId;
        this.timeCompleted = timeCompleted;
        this.user = user;
    }

    public Feat(int points, int actionId, User user) {
        this.points = points;
        this.actionId = actionId;
        this.user = user;
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

    public void setUser(User user) {
        this.user = user;
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
