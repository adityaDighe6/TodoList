package com.todolist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolist.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
