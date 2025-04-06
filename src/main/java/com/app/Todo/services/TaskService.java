package com.app.Todo.services;

import com.app.Todo.models.Task;
import com.app.Todo.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }


    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void createTask(String title) {
        Task task =  new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task foundTask = taskRepo.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid Task Id")
                );
        foundTask.setCompleted(!foundTask.isCompleted());
        taskRepo.save(foundTask);
    }
}
