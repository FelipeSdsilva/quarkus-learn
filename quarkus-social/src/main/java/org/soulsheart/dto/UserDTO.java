package org.soulsheart.dto;

import org.bson.types.ObjectId;
import org.soulsheart.entities.User;

import java.time.LocalDate;

public class UserDTO {

    private ObjectId id;
    private String fullName;
    private LocalDate birthday;

    public UserDTO() {
    }

    public UserDTO(ObjectId id, String fullName, LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public UserDTO(User user) {
        id = user.getId();
        fullName = user.getFullName();
        birthday = user.getBirthday();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
