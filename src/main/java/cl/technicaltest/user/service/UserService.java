package cl.technicaltest.user.service;

import cl.technicaltest.user.dto.UserRequest;
import cl.technicaltest.user.exception.NotFoundException;
import cl.technicaltest.user.exception.UserExistException;
import cl.technicaltest.user.model.Phone;
import cl.technicaltest.user.model.User;
import cl.technicaltest.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException(String.format("User with id %s not found", id));
        }
        return user.get();
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(UserRequest userRequest) throws UserExistException {
        if(this.getUserByEmail(userRequest.email()).isPresent()) {
            throw new UserExistException(String.format("User with email %s already exist", userRequest.email()));
        }
        return userRepository.save(this.createUser(userRequest));
    }

    private User createUser(UserRequest userRequest) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setPhones(userRequest.phones()
                .stream()
                .map(phoneRequest ->  new Phone(phoneRequest.number(),
                                                phoneRequest.citycode(),
                                                phoneRequest.countrycode(), user))
                .collect(Collectors.toList()));
        user.setToken(this.tokenService.getToken(userRequest.email()));
        return user;
    }

    public User deleteUser(String id) {
        User user = this.getUserById(id);
        userRepository.deleteById(id);
        return user;
    }
}
