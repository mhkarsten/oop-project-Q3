package server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "achievements")
public class Achievement {
    @Id
    private long id;
    private String title;
    private String description;
    private String path;
    @ManyToMany(mappedBy = "achievement")
    private Set<User> user;

    public Achievement() {

    }

    /**
     * Constructor for the Achievement class.
     *
     * @param achID       Achievement ID
     * @param title       Title of the model
     * @param description description of the model
     * @param path        path to the badge image for this model
     */
    public Achievement(long achID, String title, String description, String path) {
        this.id = achID;
        this.title = title;
        this.description = description;
        this.path = path;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Achievement that = (Achievement) obj;
        return Objects.equals(id, that.id)
            && Objects.equals(title, that.title)
            && Objects.equals(description, that.description)
            && Objects.equals(path, that.path);
    }

    public long getID() {
        return id;
    }

    public void setID(long achID) {
        this.id = achID;
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
