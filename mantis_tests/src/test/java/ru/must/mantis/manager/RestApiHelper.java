package ru.must.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.must.mantis.models.UserData;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }
    public void signup(UserData userData) {
        UserApi apiInstance = new UserApi();
        User user = new User();
        user.setUsername(userData.username());
        user.email(userData.email());
        try {
            apiInstance.userAdd(user);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }


}
