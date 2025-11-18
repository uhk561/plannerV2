package com.plannerv2.user;


import com.plannerv2.todo.dto.GetTodoResponse;
import com.plannerv2.todo.dto.UpdateTodoRequest;
import com.plannerv2.todo.dto.UpdateTodoResponse;
import com.plannerv2.user.dto.*;
import com.plannerv2.user.entity.User;
import com.plannerv2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity<AddUserResponse> addUser(@RequestBody AddUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(request));
    }

    // 유저 한 명 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetuserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<GetuserResponse>> getAllTUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    // 유저 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable long id,
            @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, request));
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
