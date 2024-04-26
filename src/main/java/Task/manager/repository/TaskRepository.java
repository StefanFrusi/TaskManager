package Task.manager.repository;

import Task.manager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
List<Task> findAllByTaskCategoryIdOrderByDueTo(Long id);
}
