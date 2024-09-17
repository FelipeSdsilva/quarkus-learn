package org.soulsheart.dto;

import org.soulsheart.entities.Publication;
import org.soulsheart.entities.User;

public class PublicationDTO {

    private String id;
    private String title;
    private String content;
    private UserDTO user;

    public PublicationDTO() {
    }

    public PublicationDTO(String id, String title, String content, UserDTO user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public PublicationDTO(Publication publication, User user) {
        id = publication.getId().toString();
        title = publication.getTitle();
        content = publication.getContent();
        this.user = new UserDTO(user);
    }

    public PublicationDTO(Publication publication) {
        id = publication.getId().toString();
        title = publication.getTitle();
        content = publication.getContent();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
