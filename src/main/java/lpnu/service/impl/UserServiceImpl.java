package lpnu.service.impl;

import lpnu.dto.UserDTO;
import lpnu.entity.Ticket;
import lpnu.entity.User;
import lpnu.entity.mapper.DTOConvertor;
import lpnu.repository.UserRepository;
import lpnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final DTOConvertor dtoConvertor;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, DTOConvertor dtoConvertor) {
        this.userRepository = userRepository;
        this.dtoConvertor = dtoConvertor;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> userDTOList.add(dtoConvertor.convertToDto(user, UserDTO.class)));
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(final Long id) {
        return dtoConvertor.convertToDto(userRepository.findById(id).get(), UserDTO.class);
    }

    @Override
    public void deleteUserById(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO saveUser(final UserDTO userDTO) {
        User user = new User();
        user.setAge(userDTO.getAge());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());

        List<Ticket> ticketList = new ArrayList<>();
        userDTO.getTicketList().forEach(ticketDTO -> {
            ticketList.add(dtoConvertor.convertToEntity(ticketDTO, new Ticket()));
        });

        userRepository.save(user);
        return dtoConvertor.convertToDto(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(final UserDTO userDTO) {
        User userFromDB = userRepository.findById(userDTO.getUserId()).get();
        userFromDB.setUserId(userDTO.getUserId());
        userFromDB.setAge(userDTO.getAge());
        userFromDB.setName(userDTO.getName());
        userFromDB.setSurname(userDTO.getSurname());

        List<Ticket> ticketList = new ArrayList<>();
        userDTO.getTicketList().forEach(ticketDTO -> {
            ticketList.add(dtoConvertor.convertToEntity(ticketDTO, new Ticket()));
        });

        userFromDB.setTicketList(ticketList);
        userRepository.save(userFromDB);
        return dtoConvertor.convertToDto(userFromDB, UserDTO.class);
    }
}

