package org.soulsheart.dto;

import java.util.ArrayList;
import java.util.List;

public class FollowerDTO {

    private Integer followersCount;
    private List<FollowMinDTO> followers = new ArrayList<>();

    public FollowerDTO() {
    }

    public FollowerDTO(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public FollowerDTO(Integer followersCount, List<FollowMinDTO> followMinDTOList) {
        this.followersCount = followersCount;
        this.followers = followMinDTOList;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public List<FollowMinDTO> getFollowers() {
        return followers;
    }
}
