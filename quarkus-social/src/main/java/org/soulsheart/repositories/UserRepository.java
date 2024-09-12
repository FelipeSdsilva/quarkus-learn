package org.soulsheart.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.soulsheart.entities.User;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
}
