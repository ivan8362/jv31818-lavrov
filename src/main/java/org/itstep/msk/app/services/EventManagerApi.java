package org.itstep.msk.app.services;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.itstep.msk.app.entities.User;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface EventManagerApi {
    User createUser() throws IOException, InterruptedException, ExecutionException;
    void addProfilePicture(OAuth20Service service, OAuth2AccessToken accessToken, User user);
    Iterable<User> getAllUsers();
    Optional<User> findById(Integer id);
}
