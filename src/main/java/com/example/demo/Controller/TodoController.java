package com.example.demo.Controller;


import com.example.demo.Model.Task;
import com.example.demo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TodoController {

    @Autowired

    private TodoRepository todoRepository;

    @GetMapping(value = "/")
    public String hola(){
        return "hpta mundo";
    }

    @GetMapping(value= "/tasks")
    public List<Task> getTask(){
        return todoRepository.findAll();
    }
    @PostMapping(value ="/savetask")
    public String saveTask(@RequestBody Task task){
        todoRepository.save(task);
        return "Saved Task";
    }
    @PutMapping(value="/update/{id}")
    public String updateTask(@PathVariable long id, @RequestBody Task task){

        Task updatedTask = todoRepository.findById(id).get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());

        todoRepository.save(updatedTask);
        return "Updated Task";
    }
    @DeleteMapping(value="delete/{id}")
    public String deleteTask(@PathVariable long id){
        Task delelteTask= todoRepository.findById(id).get();
        todoRepository.delete(delelteTask);
        return "deleted task";
    }

}
