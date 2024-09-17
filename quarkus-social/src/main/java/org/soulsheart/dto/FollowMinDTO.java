package org.soulsheart.dto;

import org.bson.types.ObjectId;
import org.soulsheart.entities.Follower;

public class FollowMinDTO {

    private ObjectId id;
    private String name;

    public FollowMinDTO() {
    }

    public FollowMinDTO(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }


    public FollowMinDTO(Follower follower) {
        this.id = id;
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
