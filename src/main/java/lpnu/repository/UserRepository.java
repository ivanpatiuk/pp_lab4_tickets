package lpnu.repository;

import lpnu.entity.City;
import lpnu.entity.User;
import lpnu.exception.ServiceException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;
    private long id = 1;

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void deleteUserById(final Long id) {
        for (final User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                break;
            }
        }
    }

    public User updateCity(final User user) {
        final User savedUser = getUserById(user.getId());

        savedUser.setName(user.getName());
        savedUser.setSurname(user.getSurname());
        savedUser.setAge(user.getAge());
        savedUser.setTicketDTOList(user.getTicketDTOList());

        return savedUser;
    }

    public User saveUser(final User user) {
        user.setId(id);
        ++id;
        users.add(user);
        return user;
    }

    public User getUserById(final Long id) {
        return users.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(400, "user with id '"+id+"' not found"));
    }

}
