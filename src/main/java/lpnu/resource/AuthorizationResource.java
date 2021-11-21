package lpnu.resource;

import lpnu.service.AuthorizationService;
import lpnu.service.impl.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class AuthorizationResource {
    @Autowired
    public AuthorizationService authorizationService;

    @PutMapping("/logIn")
    public String authorize(@RequestBody final String password) {
        return authorizationService.logIn(password);
    }
    @PutMapping("/logOut")
    public String logout(){
        return authorizationService.logOut();
    }

}