package client.model;

import server.model.User;

import java.util.Date;

public class Feat {
    private long id;
    private Date timeCompleted;

    private int points;
    private int actionId;
    private User user;

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
