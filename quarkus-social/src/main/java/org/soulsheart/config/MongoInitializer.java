package org.soulsheart.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.soulsheart.entities.User;


@ApplicationScoped
public class MongoInitializer {

    @Inject
    MongoClient mongoClient;

    @PostConstruct
    void init() {
        MongoCollection<User> collection = mongoClient.getDatabase("quarkus-social").getCollection("User", User.class);
        collection.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
    }
}