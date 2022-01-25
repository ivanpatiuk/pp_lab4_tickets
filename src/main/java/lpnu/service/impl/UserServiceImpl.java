//package lpnu.service.impl;
//
//import lpnu.dto.TicketDTO;
//import lpnu.dto.UserDTO;
//import lpnu.entity.User;
//import lpnu.exception.ServiceException;
//import lpnu.mapper.UserToUserDTOMapper;
//import lpnu.repository.UserRepository;
//import lpnu.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserToUserDTOMapper userMapper;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public List<UserDTO> getAllUsers() {
//        return userRepository.getAllUsers().stream()
//                .map(userMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDTO getUserById(final Long id) {
//        return userMapper.toDTO(userRepository.getUserById(id));
//    }
//
//    @Override
//    public void deleteUserById(final Long id) {
//        userRepository.getUserById(id);
//        userRepository.deleteUserById(id);
//    }
//
//    @Override
//    public UserDTO updateUser(final UserDTO userDTO) {
//        userRepository.getUserById(userDTO.getId());
//        return userMapper.toDTO(
//                userRepository.updateUser(
//                        userMapper.toEntity(
//                                userDTO)));
//    }
//
//    @Override
//    public UserDTO saveUser(final UserDTO userDTO) {
//        final User user = userMapper.toEntity(userDTO);
//        userRepository.saveUser(user);
//        return userMapper.toDTO(user);
//    }
//}
//
