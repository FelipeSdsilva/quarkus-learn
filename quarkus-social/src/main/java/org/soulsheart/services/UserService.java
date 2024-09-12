package org.soulsheart.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.soulsheart.dto.UserDTO;
import org.soulsheart.entities.User;
import org.soulsheart.repositories.UserRepository;
import org.soulsheart.requests.UserRequest;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<UserDTO> retrieverAllUsersListed() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO retrieverCustomerPerId(ObjectId id) {
        return new UserDTO(userRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException()));
    }

    public UserDTO insertNewUser(UserRequest request) {
        User user = new User();
        converterDtoInEntity(request, user);
        userRepository.persist(user);
        return new UserDTO(user);
    }

    public UserDTO updateUserPerId(ObjectId id, UserRequest request) {
        User user = userRepository.findByIdOptional(id).orElseThrow(() -> new RuntimeException());
        converterDtoInEntity(request, user);
        userRepository.update(user);
        return new UserDTO(user);
    }

    public void deleteUserPerId(ObjectId id) {
        if (userRepository.findByIdOptional(id).isEmpty())
            throw new RuntimeException();
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {

        }
    }

    private void converterDtoInEntity(UserRequest request, User user) {
        user.setFirstName(request.fistName());
        user.setLastName(request.lastName());
        user.setBirthday(request.birthDate());
        user.setEmail(request.email());
        user.setPassword(request.password());
    }

}
