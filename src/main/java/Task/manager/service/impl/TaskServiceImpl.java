package Task.manager.service.impl;

import Task.manager.Entity.Task;
import Task.manager.Entity.TaskCategory;
import Task.manager.dto.TaskCategoryDto;
import Task.manager.dto.TaskDto;
import Task.manager.exception.GlobalException;
import Task.manager.mapper.TaskMapper;
import Task.manager.repository.TaskCategoryRepository;
import Task.manager.repository.TaskRepository;
import Task.manager.repository.UserRepository;
import Task.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    TaskCategoryRepository taskCategoryRepository;
    TaskRepository taskRepository;
    UserRepository userRepository;
    @Autowired
    TaskServiceImpl(TaskCategoryRepository taskCategoryRepository, UserRepository userRepository, TaskRepository taskRepository)
    {
        this.taskCategoryRepository=taskCategoryRepository;
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
    }
    @Override
    public TaskCategoryDto addCategory(String name) {
Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
if(!(authentication instanceof UsernamePasswordAuthenticationToken))
    throw new GlobalException("You are not authenticated", HttpStatus.BAD_REQUEST);
String email =authentication.getName();
if(taskCategoryRepository.findByNameAndEmail(name,email).isPresent())
    throw new GlobalException("This category already exists",HttpStatus.BAD_REQUEST);
TaskCategory taskCategory=new TaskCategory();
taskCategory.setName(name);
taskCategory.setEmail(email);
TaskCategory addedTaskCategory=taskCategoryRepository.save(taskCategory);
return TaskMapper.toTaskCategoryDto(addedTaskCategory);


    }
    public void addTask(TaskDto taskDto,Long taskCategoryId)
    {Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken))
            throw new GlobalException("You are not authenticated", HttpStatus.BAD_REQUEST);
        String email =authentication.getName();
        Optional<TaskCategory> taskCategory=taskCategoryRepository.findById(taskCategoryId);
        if(taskCategory.isEmpty()|| !taskCategory.get().getEmail().equals(email))
            throw new GlobalException("The category with the id: "+taskCategoryId+" does not exist",HttpStatus.BAD_REQUEST);
        Task task= TaskMapper.toTask(taskDto);
        task.setEmail(email);
        task.setTaskCategoryId(taskCategoryId);
        taskRepository.save(task);
    }
    public List<TaskDto> getTasks(Long id)
    { Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken))
            throw new GlobalException("You are not authenticated", HttpStatus.BAD_REQUEST);
        String email =authentication.getName();
        Optional<TaskCategory> taskCategory=taskCategoryRepository.findById(id);
        if(taskCategory.isEmpty()|| !taskCategory.get().getEmail().equals(email))
            throw new GlobalException("The category with the id: "+id+" does not exist",HttpStatus.BAD_REQUEST);
        List<Task> tasks= taskRepository.findAllByTaskCategoryIdOrderByDueTo(id);
        List<TaskDto> tasksDto=TaskMapper.toTaskDtoList(tasks);
        return tasksDto;

    }
    public List<TaskCategoryDto> getTaskCategories()
    {Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken))
            throw new GlobalException("You are not authenticated", HttpStatus.BAD_REQUEST);
        String email =authentication.getName();
        Optional<List<TaskCategory>> taskCategories= taskCategoryRepository.findAllByEmail(email);
        List<TaskCategoryDto> taskCategoriesDto= TaskMapper.toTaskCategoryDtoList(taskCategories.get());
        return taskCategoriesDto;


    }
    public void updateTask(TaskDto taskDto)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken))
            throw new GlobalException("You are not authenticated", HttpStatus.BAD_REQUEST);
        String email =authentication.getName();
        if(taskDto.getId()==null)
            throw new GlobalException("Please provide the id of the task you want to update",HttpStatus.BAD_REQUEST);
        Optional<Task> task=taskRepository.findById(taskDto.getId());
        if(task.isEmpty()||!task.get().getEmail().equals(email))
            throw new GlobalException("The task with the id: "+taskDto.getId()+" does not exist",HttpStatus.BAD_REQUEST);
        Task newTask=task.get();
        newTask.setTitle(taskDto.getTitle());
        newTask.setDescription(taskDto.getDescription());
        newTask.setDueTo(taskDto.getDueTo());
        taskRepository.save(newTask);

    }
    public void updateCategory(TaskCategoryDto taskCategoryDto)
    { if(taskCategoryDto.getId()==null)
        throw  new GlobalException("Please provide the id of the category you want to change",HttpStatus.BAD_REQUEST);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof UsernamePasswordAuthenticationToken))
            throw new GlobalException("You are not authenticated", HttpStatus.BAD_REQUEST);
        String email =authentication.getName();
        Optional<TaskCategory> taskCategory=taskCategoryRepository.findById(taskCategoryDto.getId());
        if(taskCategory.isEmpty()||!taskCategory.get().getEmail().equals(email))
            throw new GlobalException("The category with the id:"+ taskCategoryDto.getId()+ "does not exist",HttpStatus.BAD_REQUEST);
        TaskCategory newTaskCategory=taskCategory.get();
        newTaskCategory.setName(taskCategoryDto.getName());
        taskCategoryRepository.save(newTaskCategory);
    }
    public void deleteTask(Long id)
    {
// IMPLEMENT THE LOGIC OF DELETING A TASK
    }
    public void deleteCategory(Long id)
    {
        //IMPLEMENT THE LOGIC OF DELETING THE CATEGORY AND ITS ASSOCIATED TASKS
    }
}
