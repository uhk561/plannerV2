package com.plannerv2.todo.service;

import com.plannerv2.todo.dto.CreateTodoRequest;
import com.plannerv2.todo.dto.CreateTodoResponse;
import com.plannerv2.todo.dto.GetTodoResponse;
import com.plannerv2.todo.entity.TodoEntity;
import com.plannerv2.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public CreateTodoResponse save(CreateTodoRequest request) {
        TodoEntity todo = new TodoEntity(
                request.getTitle(),
                request.getContent(),
                request.getUserName()
        );
        TodoEntity saveTodo = todoRepository.save(todo);

        return new CreateTodoResponse(
                saveTodo.getId(),
                saveTodo.getTitle(),
                saveTodo.getContent(),
                saveTodo.getUserName(),
                saveTodo.getCreatedAt(),
                saveTodo.getModifiedAt()
        );
    }

    // 일정 조회(단 건)
    @Transactional(readOnly = true)
    public GetTodoResponse getTodo(Long id) {
        TodoEntity todo = todoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
        );
        return new GetTodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getUserName(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetTodoResponse> getAll() {
        List<TodoEntity> todos = todoRepository.findAll();

        List<GetTodoResponse> dtos = new ArrayList<>();
        for (TodoEntity todo : todos) {
            GetTodoResponse dto = new GetTodoResponse(
                    todo.getId(),
                    todo.getTitle(),
                    todo.getContent(),
                    todo.getUserName(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public
}
