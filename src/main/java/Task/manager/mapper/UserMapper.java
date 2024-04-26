package Task.manager.mapper;

import Task.manager.Entity.User;
import Task.manager.dto.UserDto;

public class UserMapper {
    public static User toUser(UserDto userDto)
    { User user= new User();
        user.setEmail(userDto.getEmail());
        user.setPwd(userDto.getPwd());
  return user;
    }
    public static UserDto toUserDto(User user)
    {
        UserDto userDto= new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
