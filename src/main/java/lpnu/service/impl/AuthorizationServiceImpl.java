package lpnu.service.impl;

import lpnu.authorization.Authorization;
import lpnu.exception.ServiceException;
import lpnu.service.AuthorizationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Override
    public String logIn(final String password) {
        Authorization.getInstance();
        if (!Authorization.isAutorized()) {
            if (Authorization.getPassword().equals(password)) {
                Authorization.setAutorized(true);
                return "Successful authorization";
            } else
                throw new ServiceException(400, "wrong password");
        } else
            return "Already authorized";

    }

    @Override
    public String logOut() {
        if (Authorization.isAutorized()) {
            Authorization.setAutorized(false);
            return "Logged out";
        } else
            throw new ServiceException(400, "authorize at first", "you are not authorized. Please authorize");
    }
}

