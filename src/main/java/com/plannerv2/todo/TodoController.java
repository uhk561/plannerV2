package com.plannerv2.todo;

import com.plannerv2.todo.dto.*;
import com.plannerv2.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    // 일정 생성(작성)
    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo(@RequestBody CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.crateTodo(request));
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
            @PathVariable long id,
            @RequestBody UpdateTodoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodo(id, request));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
