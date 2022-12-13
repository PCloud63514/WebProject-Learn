package com.pcloud.userservice;

import com.pcloud.userservice.dto.JoinUserDto;
import com.pcloud.userservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 이름 검색
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
    // id 검색
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    // 전체 검색
    public List<User> findAll() {
        return userRepository.findAll();
    }
    // 추가
    public void joinUser(JoinUserDto userDto) {
        userRepository.add(userDto.getName());
    }
    // 삭제
    public void deleteUser(UserDto userDto) {
        userRepository.remove(userDto.getId());
    }
}
