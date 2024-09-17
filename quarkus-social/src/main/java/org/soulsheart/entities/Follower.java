package org.soulsheart.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

import java.util.Objects;

public class Follower extends PanacheMongoEntity {

    private ObjectId userId;
    private ObjectId followerId;

    public Follower() {
    }

    public Follower(ObjectId userId, ObjectId followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getFollowerId() {
        return followerId;
    }

    public void setFollowerId(ObjectId followerId) {
        this.followerId = followerId;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Follower follower)) return false;

        return Objects.equals(getUserId(), follower.getUserId()) && Objects.equals(followerId, follower.followerId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getUserId());
        result = 31 * result + Objects.hashCode(followerId);
        return result;
    }
}
