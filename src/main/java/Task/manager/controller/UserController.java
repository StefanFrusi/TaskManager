package Task.manager.controller;

import Task.manager.dto.LoginDto;
import Task.manager.dto.ResponseDto;
import Task.manager.dto.UserDto;
import Task.manager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/authentication")
public class UserController {
UserService userService;
@Autowired
    UserController(UserService userService)
    {
        this.userService=userService;
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestBody @Valid  UserDto userDto)
    {
        userService.register(userDto);
        return new ResponseEntity<>(new ResponseDto("The account has been successfully created"),HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginDto loginDto, HttpServletRequest request, HttpServletResponse response)
    { userService.login(loginDto,request,response);
        return new ResponseEntity<>(new ResponseDto("you have been successfully logged in"),HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logout(HttpServletRequest request, HttpServletResponse response)
    {
        userService.logout(request,response);
        return new ResponseEntity<>(new ResponseDto("you have been successfully logged out"),HttpStatus.OK);
    }
    @GetMapping("/csrf/token")
    public CsrfToken csrf(CsrfToken token)
    {
        return token;
    }


    @GetMapping("/checkAuthentication")
    public ResponseEntity<Boolean> checkAuthentication(HttpServletRequest request) {
       if(userService.isAuthenticated(request))
           return new ResponseEntity<>(true,HttpStatus.OK);
       else return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);

   }


  }
