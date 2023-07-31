package com.alperensertoglu.controller;

import static com.alperensertoglu.constant.EndPoints.*;

import com.alperensertoglu.dto.request.UserLoginRequestDto;
import com.alperensertoglu.dto.request.UserSaveRequestDto;
import com.alperensertoglu.dto.response.UserRegisterResponseDto;
import com.alperensertoglu.mapper.IUserMapper;
import com.alperensertoglu.repository.entity.User;
import com.alperensertoglu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping(REGISTER)
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserSaveRequestDto dto) {
        User user = service.register(dto);
        return ResponseEntity.ok(IUserMapper.INSTANCE.toResponseDto(user));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto dto) {
        return ResponseEntity.ok(service.doLogin(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @PostMapping(UPDATE)
    public ResponseEntity<UserRegisterResponseDto> updateDto(@RequestBody UserSaveRequestDto dto){
        return ResponseEntity.ok(IUserMapper.INSTANCE.toResponseDto(service.updateDto(dto)));
    }
}
