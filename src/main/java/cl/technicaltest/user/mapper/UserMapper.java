package cl.technicaltest.user.mapper;

import cl.technicaltest.user.dto.UserCreatedResponse;
import cl.technicaltest.user.model.User;

public class UserMapper {

    public static UserCreatedResponse userToUserCreatedResponse(User user) {
        return new UserCreatedResponse(
                user.getId(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.isActive());
    }
}
