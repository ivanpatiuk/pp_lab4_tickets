package service_tests;

import lpnu.dto.UserDTO;
import lpnu.service.UserService;
import lpnu.service.impl.UserServiceImpl;
import lpnu.mapper.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Tests {

    @Test
    public void numbers() {
        UserDTO user1 = new UserDTO(null, "Ivan", "Ivanenko", 35, null);

        UserService userService = new UserServiceImpl();

            Assert.assertEquals(35, userService.saveUser(user1).getAge());

    }
}
