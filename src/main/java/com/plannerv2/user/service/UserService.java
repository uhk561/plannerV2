package com.plannerv2.user.service;

import com.plannerv2.config.PasswordEncoder;
import com.plannerv2.todo.dto.GetTodoResponse;
import com.plannerv2.todo.dto.UpdateTodoRequest;
import com.plannerv2.todo.entity.Todo;
import com.plannerv2.user.dto.*;
import com.plannerv2.user.entity.User;
import com.plannerv2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 추가하기
    @Transactional
    public AddUserResponse addUser(AddUserRequest request) {
        String EncodePassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getName(),
                request.getEmail(),
                EncodePassword
        );
        User addUser = userRepository.save(user);

        return new AddUserResponse(
                addUser.getId(),
                addUser.getName(),
                addUser.getEmail(),
                addUser.getPassword(),
                addUser.getCreatedAt(),
                addUser.getModifiedAt()
        );
    }

    // 로그인
    @Transactional(readOnly = true)
    public User signIn(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);

        if(user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 및 비밀번호가 일치하지않습니다");
        }
        return user;
    }


    // 유저 조회하기 (한 명)
    @Transactional(readOnly = true)
    public GetuserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.")
        );
        return new GetuserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 모든 유저 조회하기
    @Transactional(readOnly = true)
    public List<GetuserResponse> getAllUser() {
        List<User> users = userRepository.findAll();

        List<GetuserResponse> dtos = new ArrayList<>();
        for (User user : users) {
            GetuserResponse dto = new GetuserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 유저 정보 수정하기
    @Transactional
    public UpdateUserResponse updateUser (Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.")
        );
        user.updateUser(request.getName());
        return new UpdateUserResponse(
                user.getName(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.")
        );
        userRepository.deleteById(id);
    }
}
