package org.itstep.msk.app.services;

import org.itstep.msk.app.entities.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface EventManagerApi {
    User createUser() throws IOException, InterruptedException, ExecutionException;
    Iterable<User> getAllUsers();
}
