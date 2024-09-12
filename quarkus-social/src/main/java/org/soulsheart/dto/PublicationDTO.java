package org.soulsheart.dto;

import org.bson.types.ObjectId;
import org.soulsheart.entities.Publication;

public class PublicationDTO {

    private String title;
    private String content;
    private ObjectId userId;

    public PublicationDTO() {
    }

    public PublicationDTO(String title, String content, ObjectId userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public PublicationDTO(Publication publication) {
        this.title = title;
        this.content = content;
        this.userId = userId;
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

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }
}
