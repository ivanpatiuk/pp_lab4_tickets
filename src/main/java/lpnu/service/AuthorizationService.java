package lpnu.service;

public interface AuthorizationService {
    String logIn(final String password);
    String logOut();
}
