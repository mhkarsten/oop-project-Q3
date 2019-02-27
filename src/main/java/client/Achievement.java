package client;

public class Achievement {

    private String achID;
    private String title;
    private String description;
    private String path;

    /**.
     *
     * @param achID Achievement ID
     * @param title Title of the achievement
     * @param description description of the achievement
     * @param path path to the badge image for this achievement
     */
    public Achievement(String achID, String title, String description, String path) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
