package Task.manager.service;

import Task.manager.dto.TaskCategoryDto;
import Task.manager.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskCategoryDto addCategory(String name);
    List<TaskCategoryDto> getTaskCategories();
    TaskDto addTask(TaskDto taskDto,Long taskCategoryId);
    List<TaskDto> getTasks(Long id);
    TaskDto updateTask(TaskDto taskDto);
    TaskCategoryDto updateCategory(TaskCategoryDto taskCategoryDto);
}
