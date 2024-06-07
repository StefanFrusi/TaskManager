package Task.manager.controller;

import Task.manager.Entity.Task;
import Task.manager.dto.ResponseDto;
import Task.manager.dto.TaskCategoryDto;
import Task.manager.dto.TaskDto;
import Task.manager.repository.TaskRepository;
import Task.manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Autowired
    TaskController(TaskService taskService, TaskRepository taskRepository)
    {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }
    @PostMapping("/addCategory")
    public ResponseEntity<TaskCategoryDto> addCategory(@RequestParam(name="taskCategoryName") String name)
    {
TaskCategoryDto taskCategoryDto=taskService.addCategory(name);
return new ResponseEntity<>(taskCategoryDto, HttpStatus.OK);
    }
    @DeleteMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestParam(name="taskId")Long taskId)
    {   taskService.deleteTask(taskId);
        return new ResponseEntity<>("The task has been deleted", HttpStatus.OK);
    }
    @GetMapping ("/getCategories")
        public ResponseEntity<List<TaskCategoryDto>> getCategories()
    {
        List<TaskCategoryDto> taskCategoriesDto= taskService.getTaskCategories();
        return new ResponseEntity<>(taskCategoriesDto, HttpStatus.OK);
    }
    @PostMapping("/addTask")
    public ResponseEntity<TaskDto> addTask(@RequestBody @Valid TaskDto taskDto, @RequestParam(name = "taskCategoryId") Long taskCategoryId)
    {
        TaskDto addedTask=taskService.addTask(taskDto,taskCategoryId);
        return new ResponseEntity<>(addedTask,HttpStatus.OK);
    }
    @GetMapping("/getTasks")
    public ResponseEntity<List<TaskDto>> getTasks(@RequestParam(name = "taskCategoryId") Long id)
    {System.out.println(id);
        List<TaskDto> tasks=taskService.getTasks(id);
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }
    @PatchMapping("/updateTask")
    public ResponseEntity<TaskDto> updateTask(@RequestBody @Valid  TaskDto taskDto)
    {
        TaskDto updatedTask=taskService.updateTask(taskDto);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
    @PatchMapping("/updateCategory")
    public ResponseEntity<TaskCategoryDto> updateCategory(@RequestBody @Valid  TaskCategoryDto taskCategoryDto)
    {
        TaskCategoryDto updatedTaskCategory=taskService.updateCategory(taskCategoryDto);
        return new ResponseEntity<>(updatedTaskCategory,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam Long taskCategoryId)
    {
        taskService.deleteCategory(taskCategoryId);
        return new ResponseEntity<>("The Category has been deleted",HttpStatus.OK);
    }
}
