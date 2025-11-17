package com.plannerv2.todo;

import com.plannerv2.todo.dto.CreateTodoRequest;
import com.plannerv2.todo.dto.CreateTodoResponse;
import com.plannerv2.todo.dto.GetTodoResponse;
import com.plannerv2.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoRestController {

    private TodoService todoService;

    // 일정 생성(작성)
    @PostMapping
    public ResponseEntity<CreateTodoResponse> createTodo(@RequestBody CreateTodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }

    // 일정 조회 (단 건)
    @GetMapping("/{id}")
    public ResponseEntity<GetTodoResponse> getTodo(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodo(id));
    }

    // 전체 일정 조회(다 건 조회)
    @GetMapping
    public ResponseEntity<List<GetTodoResponse>> getAllTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll());
    }
}
