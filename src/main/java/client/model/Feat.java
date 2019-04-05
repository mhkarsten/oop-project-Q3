package client.model;

import server.model.User;

import java.util.Date;

public class Feat {
    private long id;
    Date timeCompleted;

    private int points;
    private int actionId;
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
