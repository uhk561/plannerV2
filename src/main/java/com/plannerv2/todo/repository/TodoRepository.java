package com.plannerv2.todo.repository;

import com.plannerv2.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

}
