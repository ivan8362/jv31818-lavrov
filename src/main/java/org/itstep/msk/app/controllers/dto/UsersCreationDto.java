package org.itstep.msk.app.controllers.dto;

import org.itstep.msk.app.entities.User;

import java.util.List;

public class UsersCreationDto {
    private List<User> users;

    public UsersCreationDto(){}

    public UsersCreationDto(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

