package Task.manager.repository;

import Task.manager.Entity.TaskCategory;
import Task.manager.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory,Long> {
    Optional<TaskCategory> findByNameAndEmail(String name,String email);

    Optional<List<TaskCategory>> findAllByEmail(String email);
}
