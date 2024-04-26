package Task.manager.service.impl;

import Task.manager.Entity.User;
import Task.manager.dto.LoginDto;
import Task.manager.dto.UserDto;
import Task.manager.exception.GlobalException;
import Task.manager.mapper.UserMapper;
import Task.manager.repository.UserRepository;
import Task.manager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final SecurityContextRepository repository=new HttpSessionSecurityContextRepository();
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder)
    {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public void register(UserDto userDto)
    {   Optional<User> emailCheck= userRepository.findByEmail(userDto.getEmail());
        if(emailCheck.isPresent())
            throw new GlobalException("The email is already in use",HttpStatus.BAD_REQUEST);
        User user=UserMapper.toUser(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        userRepository.save(user);

    }
    public void login(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response)
    { Optional<User> user= userRepository.findByEmail(loginDto.getEmail());
        if(user.isEmpty())
            throw new GlobalException("The email and/or password are/is incorrect",HttpStatus.BAD_REQUEST);
        if(passwordEncoder.matches(loginDto.getPwd(),user.get().getPwd())) {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(user.get().getEmail(),null,null));
            repository.saveContext(securityContext,request,response);
        }
        else
           throw new GlobalException("The email and/or password are/is incorrect",HttpStatus.BAD_REQUEST);
    }
    public boolean isAuthenticated(HttpServletRequest request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken)
            return true;
        else return false;
    }

}
