package org.itstep.msk.app.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.apis.vk.VKOAuth2AccessToken;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.itstep.msk.app.entities.User;
import org.itstep.msk.app.repositories.UserRepository;
import org.itstep.msk.app.services.EventManagerApi;
import org.itstep.msk.app.tools.TopLevelResponse;
import org.itstep.msk.app.tools.UserDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Service
public class EventManagerService implements EventManagerApi {
    private static final String NETWORK_NAME = "Vkontakte.ru";

    @Value("${org.itstep.msk.vk.clientId}")
    private String clientId;

    @Value("${org.itstep.msk.vk.clientSecret}")
    private String clientSecret;

    @Value ("${org.itstep.msk.vk.callbackUri}")
    private String callbackUri;

    @Autowired
    private UserRepository userRepository;

    private static final String PROTECTED_RESOURCE_URL_PIC = "https://api.vk.com/method/photos.get"
            + "?album_id=profile&rev=0&count=1&v=" + VkontakteApi.VERSION;

    private static final String PROTECTED_RESOURCE_URL = "https://api.vk.com/method/users.get?v="
            + VkontakteApi.VERSION;

    public User createUser(/*OAuth20Service service, OAuth2AccessToken accessToken*/)
            throws IOException, InterruptedException, ExecutionException{
        final OAuth20Service service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope("photos") // replace with desired scope
                .callback(callbackUri)
                .build(VkontakteApi.instance());
        final Scanner in = new Scanner(System.in);

        System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        final String customScope = "photos";
        final String authorizationUrl = service.createAuthorizationUrlBuilder()
                .scope(customScope)
                .build();
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize ScribeJava here:");
        System.out.println(authorizationUrl);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        final String code = in.nextLine();
        System.out.println();

        System.out.println("Trading the Authorization Code for an Access Token...");
        final OAuth2AccessToken accessToken = service.getAccessToken(AccessTokenRequestParams.create(code)
                .scope(customScope));
        System.out.println("Got the Access Token!");
        System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");
        if (accessToken instanceof VKOAuth2AccessToken) {
            /*System.out.println("it's a VKOAuth2AccessToken, it has email field = '"
                    + ((VKOAuth2AccessToken) accessToken).getEmail() + "'.");*/
        }
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");

        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);

        User user = null;

        try (Response response = service.execute(request)) {
            System.out.println("Got it! Lets see what we found...");
            System.out.println(response.getCode());
            System.out.println(response.getBody());

            //crop response
            String cropped = response.getBody();
            cropped = cropped.substring(cropped.indexOf("[") + 1);
            cropped = cropped.substring(0, cropped.indexOf("]"));

            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(User.class, new UserDeserializer());
            mapper.registerModule(module);
            user = mapper.readValue(cropped, User.class);

            userRepository.save(user);
            this.addProfilePicture(service, accessToken, user);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void addProfilePicture(OAuth20Service service, OAuth2AccessToken accessToken, User user) {
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL_PIC);
        service.signRequest(accessToken, request);

        try (Response response = service.execute(request)) {
            System.out.println(response.getCode());
            System.out.println(response.getBody());

            TopLevelResponse topLevelResponse = new ObjectMapper()
                    .readValue(response.getBody(), TopLevelResponse.class);
            String pictureUrl = topLevelResponse.response.items.get(0).sizes.get(0).url;
            user.setProfilepicture(pictureUrl);
            userRepository.save(user);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Iterable<User> getAllUsers(){
        Iterable<User> users = this.userRepository.findAll();

        return users;
    }

    public Optional<User> findById(Integer id){
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }
}
