package Task.manager.service;

import Task.manager.dto.LoginDto;
import Task.manager.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    void register(UserDto userDto);
    void login(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response);
    boolean isAuthenticated(HttpServletRequest request);
}
