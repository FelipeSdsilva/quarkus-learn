package org.soulsheart.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;
import org.soulsheart.dto.PublicationDTO;
import org.soulsheart.entities.Publication;
import org.soulsheart.entities.User;
import org.soulsheart.repositories.PublicationRepository;
import org.soulsheart.repositories.UserRepository;
import org.soulsheart.requests.PublicationRequest;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PublicationService {

    private PublicationRepository publicationRepository;
    private UserRepository userRepository;

    @Inject
    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    public List<PublicationDTO> retrieverAllPublicationsListed() {
        return publicationRepository.findAll().stream().map(publication -> new PublicationDTO(publication, userRepository.findById(publication.getUserId()))).toList();
    }

    public PublicationDTO retrieverPublicationPerId(ObjectId id) {
        return new PublicationDTO(publicationRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException()));
    }

    public PublicationDTO insertNewPublication(PublicationRequest request) {
        Publication publication = new Publication();
        converterDtoInEntity(request, publication);
        publicationRepository.persist(publication);
        return new PublicationDTO(publication);
    }

    public PublicationDTO updatePublicationPerId(ObjectId id, PublicationRequest request) {
        Publication publication = publicationRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException());
        converterDtoInEntity(request, publication);
        publicationRepository.update(publication);
        return new PublicationDTO(publication);
    }

    public void deletePublicationPerId(ObjectId id) {
        if (publicationRepository.findByIdOptional(id).isEmpty())
            throw new RuntimeException();
        try {
            publicationRepository.deleteById(id);
        } catch (Exception e) {

        }
    }

    public PublicationDTO createPublicationForUser(String userId, PublicationRequest request) {
        ObjectId newUserId = new ObjectId(userId);
        User user = userRepository.findById(newUserId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Publication publication = new Publication();
        publication.setUserId(user.getId());
        converterDtoInEntity(request, publication);

        publicationRepository.persist(publication);

        return new PublicationDTO(publication,user);
    }

    public List<PublicationDTO> getPublicationsByUser(String userId) {
        User user = userRepository.findById(new ObjectId(userId));
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        List<Publication> publications = publicationRepository.find("userId", user.getId()).list();
        return publications.stream().map(PublicationDTO::new).collect(Collectors.toList());
    }

    private void converterDtoInEntity(PublicationRequest request, Publication publication) {
        publication.setTitle(request.title());
        publication.setContent(request.content());
    }

}
