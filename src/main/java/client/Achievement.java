package client;

public class Achievement {

    private String achID;
    private String title;
    private String description;


    public Achievement(String achID, String title, String description) {
        this.achID = achID;
        this.title = title;
        this.description = description;
    }

    public String getAchID() {
        return achID;
    }

    public void setAchID(String achID) {
        this.achID = achID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
