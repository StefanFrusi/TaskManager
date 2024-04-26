package Task.manager.controller;

import Task.manager.Entity.Task;
import Task.manager.dto.ResponseDto;
import Task.manager.dto.TaskCategoryDto;
import Task.manager.dto.TaskDto;
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
    @Autowired
    TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }
    @PostMapping("/addCategory")
    public ResponseEntity<TaskCategoryDto> addCategory(@RequestParam(name="taskCategoryName") String name)
    {
TaskCategoryDto taskCategoryDto=taskService.addCategory(name);
return new ResponseEntity<>(taskCategoryDto, HttpStatus.OK);
    }
    @GetMapping ("/getCategories")
        public ResponseEntity<List<TaskCategoryDto>> getCategories()
    {
        List<TaskCategoryDto> taskCategoriesDto= taskService.getTaskCategories();
        return new ResponseEntity<>(taskCategoriesDto, HttpStatus.OK);
    }
    @PostMapping("/addTask")
    public ResponseEntity<ResponseDto> addTask(@RequestBody @Valid TaskDto taskDto, @RequestParam(name = "taskCategoryId") Long taskCategoryId)
    {
        taskService.addTask(taskDto,taskCategoryId);
        return new ResponseEntity<>(new ResponseDto("The task has been successfully added"),HttpStatus.OK);
    }
    @GetMapping("/getTasks")
    public ResponseEntity<List<TaskDto>> getTasks(@RequestParam(name = "taskCategoryId") Long id)
    {System.out.println(id);
        List<TaskDto> tasks=taskService.getTasks(id);
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }
    @PatchMapping("/updateTask")
    public ResponseEntity<ResponseDto> updateTask(@RequestBody @Valid  TaskDto taskDto)
    {
        taskService.updateTask(taskDto);
        return new ResponseEntity<>(new ResponseDto("The task has been updated"),HttpStatus.OK);
    }
    @PatchMapping("/updateCategory")
    public ResponseEntity<ResponseDto> updateCategory(@RequestBody @Valid  TaskCategoryDto taskCategoryDto)
    {
        taskService.updateCategory(taskCategoryDto);
        return new ResponseEntity<>(new ResponseDto("The category has been successfully updated"),HttpStatus.OK);
    }

}
