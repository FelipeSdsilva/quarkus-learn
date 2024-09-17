package org.soulsheart.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import org.soulsheart.entities.Follower;

@ApplicationScoped
public class FollowerRepository implements PanacheMongoRepository<Follower> {
    public boolean isFollowing(ObjectId userId, ObjectId followerId) {
        return count("userId = ?1 and followerId = ?2", userId, followerId) > 0;
    }
}
