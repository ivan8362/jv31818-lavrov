package org.itstep.msk.app.controllers;

/*
@RestController
@RequestMapping("/vk")
public class VkAuthController {

    @Value("${org.itstep.msk.vk.clientId}")
    private String clientId;
    @Value("${org.itstep.msk.vk.clientSecret}")
    private String clientSecret;
    @Value("${org.itstep.msk.vk.callbackUri}")
    private String redirectCallBackUri;
    @Value("${org.itstep.msk.vk.scope}")
    private String scope;
    @Value("${org.itstep.msk.vk.userProfileUri}")
    private String userProfileUri;

    private static final Token EMPTY_TOKEN = null;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public void vkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String secretState = "secret" + new Random().nextInt(999_999);
        request.getSession().setAttribute("SECRET_STATE", secretState);

        final OAuth20Service services = new ServiceBuilder(clientId)
//                .provider(VkontakteApi.class)
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(redirectCallBackUri)
                .defaultScope(scope)
                .state(secretState)
                .grantType("code")
                .connectTimeout(10)
                .build();

        final String redirectURL = services.getAuthorizationUrl(EMPTY_TOKEN);
        response.sendRedirect(redirectURL);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "state", required = false) String state,
                           HttpServletRequest request, ModelMap model) throws IOException {

        final OAuthService services = new ServiceBuilder()
                .provider(VkontakteApi.class)
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(redirectCallBackUri)
                .build();

        final Verifier verifier = new Verifier(code);
        final Token accessToken = services.getAccessToken(EMPTY_TOKEN, verifier);

        final OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, userProfileUri, services);
        services.signRequest(accessToken, oauthRequest);

        final TopLevelResponse resourceResponse = oauthRequest.send();

        final JSONObject obj = new JSONObject(resourceResponse.getBody());

        final String userId = obj.getString("uid");
        final String first_name = obj.getString("first_name");
        final String last_name = obj.getString("last_name");

        if (userService.findOne(Long.parseLong(userId)) != null) {
            request.getSession().setAttribute("VK_ACCESS_TOKEN", accessToken);
            model.addAttribute("user", userService.findOne(Long.parseLong(userId)));
            final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user instanceof User) {
                return "account";
            } else {
                return "/personalarea";
            }
        } else {
            final User user = new User();
            user.setFirst_name(first_name);
            user.setLast_name(last_name);
            model.addAttribute("user", user);
            return "/registration";
        }
    }
}*/
