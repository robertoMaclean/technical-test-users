package cl.sentra.user.service;

import cl.sentra.user.dto.UserRequest;
import cl.sentra.user.exception.UserExistException;
import cl.sentra.user.model.Phone;
import cl.sentra.user.model.User;
import cl.sentra.user.repository.PhoneRepository;
import cl.sentra.user.repository.UserRepository;
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
    private final PhoneRepository phoneRepository;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.phoneRepository = phoneRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(UserRequest userRequest) throws UserExistException {
        if(this.getUserByEmail(userRequest.getEmail()).isPresent()) {
            throw new UserExistException(String.format("User with email %s already exist", userRequest.getEmail()));
        }
        User user = userRepository.save(this.createUser(userRequest));
        user.getPhones().forEach(phone -> phone.setUser(user));
        phoneRepository.saveAll(user.getPhones());
        return user;
    }

    private User createUser(UserRequest userRequest) {
        return new User(
                UUID.randomUUID().toString(),
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getPhones()
                        .stream()
                        .map(phoneRequest ->
                                new Phone(phoneRequest.getNumber(), phoneRequest.getCitycode(), phoneRequest.getCountrycode()))
                        .collect(Collectors.toList()),
                this.tokenService.getToken(userRequest.getEmail())
        );
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
