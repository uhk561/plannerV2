package com.plannerv2.todo.service;

import com.plannerv2.todo.dto.*;
import com.plannerv2.todo.entity.Todo;
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
    public CreateTodoResponse crateTodo(CreateTodoRequest request) {
        Todo todo = new Todo(
                request.getTitle(),
                request.getContent(),
                request.getUserName()
        );
        Todo crateTodo = todoRepository.save(todo);

        return new CreateTodoResponse(
                crateTodo.getId(),
                crateTodo.getTitle(),
                crateTodo.getContent(),
                crateTodo.getUserName(),
                crateTodo.getCreatedAt(),
                crateTodo.getModifiedAt()
        );
    }

    // 일정 조회(단 건)
    @Transactional(readOnly = true)
    public GetTodoResponse getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
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
    public List<GetTodoResponse> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();

        List<GetTodoResponse> dtos = new ArrayList<>();
        for (Todo todo : todos) {
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

    // 일정 수정
    @Transactional
    public UpdateTodoResponse updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
        );

        todo.update(request.getTitle(), request.getContent(), request.getUserName());
        return new UpdateTodoResponse(
                todo.getTitle(),
                todo.getContent(),
                todo.getUserName(),
                todo.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
        );
        todoRepository.deleteById(id);
    }
}
