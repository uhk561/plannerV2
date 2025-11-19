package com.plannerv2.todo;

import com.plannerv2.todo.dto.*;
import com.plannerv2.todo.service.TodoService;
import com.plannerv2.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    // 일정 생성(작성)
    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo(@Valid @RequestBody TodoRequest request, HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인이 필요합니다.");
        }

        User signInUser = (User) session.getAttribute("signInUser");
        if (signInUser == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인이 필요합니다.");
        }
        Long userId = signInUser.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.crateTodo(userId, request));
    }

    // 일정 조회 (단 건)
    @GetMapping("/{id}")
    public ResponseEntity<GetTodoResponse> getTodo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodo(id));
    }

    // 전체 일정 조회(다 건 조회)
    @GetMapping
    public ResponseEntity<List<GetTodoResponse>> getAllTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodo());
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateTodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request,
            HttpServletRequest httpRequest
    ) {
        User signInUser = (User) httpRequest.getSession(false).getAttribute("signInUser");

        return ResponseEntity.ok(todoService.updateTodo(id, signInUser.getId(), request));
    }


    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            HttpServletRequest httpRequest
    ) {
        User signInUser = (User) httpRequest.getSession(false).getAttribute("signInUser");

        todoService.deleteTodo(id, signInUser.getId());

        return ResponseEntity.noContent().build();
    }

}
