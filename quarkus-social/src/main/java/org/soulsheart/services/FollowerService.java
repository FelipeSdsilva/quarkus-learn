package org.soulsheart.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.soulsheart.dto.FollowMinDTO;
import org.soulsheart.dto.FollowerDTO;
import org.soulsheart.entities.Follower;
import org.soulsheart.entities.User;
import org.soulsheart.repositories.FollowerRepository;
import org.soulsheart.repositories.UserRepository;
import org.soulsheart.requests.FollowRequest;

import java.util.List;
import java.util.Objects;


@ApplicationScoped
public class FollowerService {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;

    @Inject
    public FollowerService(UserRepository userRepository, FollowerRepository followerRepository) {
        this.userRepository = userRepository;
        this.followerRepository = followerRepository;
    }

    public Response retrieverFollowers(String userId) {
        ObjectId id = new ObjectId(userId);

        List<Follower> followers = followerRepository.find("userId", id).list();

        List<FollowMinDTO> followMinDTOList = followers.stream().map(follower -> {
                    User followerUser = userRepository.findById(follower.getFollowerId());
                    if (followerUser != null) {
                        return new FollowMinDTO(followerUser.getId(), followerUser.getFullName());
                    }
                    return null;
                }).filter(Objects::nonNull)
                .toList();

        FollowerDTO followerDTO = new FollowerDTO(followMinDTOList.size(), followMinDTOList);

        return Response.ok(followerDTO).build();
    }


    public Response updateFollowUser(String userIdStr, FollowRequest request) {
        ObjectId userId = new ObjectId(userIdStr);
        ObjectId followerId = new ObjectId(request.followerId());

        var user = userRepository.findById(userId);
        var follower = userRepository.findById(followerId);

        if (user == null || follower == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        boolean alreadyFollowing = followerRepository.isFollowing(userId, followerId);
        if (alreadyFollowing) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("User is already following this person.")
                    .build();
        }

        var entity = new Follower();
        entity.setUserId(userId);
        entity.setFollowerId(followerId);

        followerRepository.persist(entity);

        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
