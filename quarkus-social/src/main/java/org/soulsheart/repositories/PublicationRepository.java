package org.soulsheart.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.soulsheart.entities.Publication;

@ApplicationScoped
public class PublicationRepository implements PanacheMongoRepository<Publication> {
}
