package com.pcloud.userservice;

import com.pcloud.userservice.dto.JoinUserDto;
import com.pcloud.userservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    // 이름 검색
    @GetMapping("/name")
    public UserDto findByName(@RequestParam("name") String name) {
        User user = userService.findByName(name);

        return new UserDto(user.getId(), user.getName());
    }
    // id 검색
    @GetMapping("/id")
    public UserDto findById(@RequestParam("id") Long id) {
        User byId = userService.findById(id);

        return new UserDto(byId.getId(), byId.getName());
    }
    // 전체 검색

    @GetMapping("list")
    public List<UserDto> list() {
        List<User> all = userService.findAll();

        return all.stream().map(user ->
                new UserDto(user.getId(), user.getName()))
            .collect(Collectors.toList());
    }
    // 추가
    @PostMapping("join")
    public void join(@RequestBody @Valid JoinUserDto joinUserDto, HttpServletResponse response) throws IOException {
        userService.joinUser(joinUserDto);
        response.sendRedirect("http://localhost:8868/user/list");
    }
    // 삭제
    @DeleteMapping("delete")
    public void delete(@RequestBody @Valid UserDto userDto, HttpServletResponse response) throws IOException {
        userService.deleteUser(userDto);
        response.sendRedirect("http://localhost:8868/user/list");
    }

}
