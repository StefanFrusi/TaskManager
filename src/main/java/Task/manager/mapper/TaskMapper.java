package Task.manager.mapper;

import Task.manager.Entity.Task;
import Task.manager.Entity.TaskCategory;
import Task.manager.dto.TaskCategoryDto;
import Task.manager.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {
    public static Task toTask(TaskDto taskDto)
    {Task task=new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueTo(taskDto.getDueTo());
        return task;


    }
    public static TaskDto toTaskDto(Task task)
    { TaskDto taskDto=new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDueTo(task.getDueTo());

return taskDto;
    }
    public static List<TaskDto> toTaskDtoList(List<Task> tasks)
    { List<TaskDto> tasksDto= new ArrayList<>();
        for(Task task:tasks)
        {
            tasksDto.add(toTaskDto(task));
        }
        return tasksDto;
    }
    public static TaskCategoryDto toTaskCategoryDto(TaskCategory taskCategory)
    {  TaskCategoryDto taskCategoryDto= new TaskCategoryDto();
        taskCategoryDto.setId(taskCategory.getId());
        taskCategoryDto.setName(taskCategory.getName());
        return taskCategoryDto;

    }
    public static List<TaskCategoryDto> toTaskCategoryDtoList(List<TaskCategory> taskCategories)
    { List<TaskCategoryDto> taskCategoriesDto= new ArrayList<>();
        for(TaskCategory taskCategory:taskCategories)
        {
            taskCategoriesDto.add(toTaskCategoryDto(taskCategory));
        }
        return  taskCategoriesDto;
    }

}
