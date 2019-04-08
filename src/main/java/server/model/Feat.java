package server.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "feats")
@SequenceGenerator(name = "feat_seq", initialValue = 1, allocationSize = 1)
public class Feat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feat_seq")
    private long id;

    private int points;
    @Column(name = "action_id")
    private int actionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_completed")
    Date timeCompleted;

    public Feat() {
    }

    /**
     * Constructor for a feat with parameters.
     * @param id id of the feat
     * @param points points for the feat
     * @param actionId id of the associated action with this feat
     * @param timeCompleted the time this feat was completed
     * @param user the user that completed this feat
     */
    public Feat(long id, int points, int actionId, Date timeCompleted, User user) {
        this.id = id;
        this.points = points;
        this.actionId = actionId;
        this.timeCompleted = timeCompleted;
        this.user = user;
    }

    /**
     * Shorter constructor for a feat with parameters.
     * @param points points for the feat
     * @param actionId id of the associated action with this feat
     * @param user the user that completed this feat
     */
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
