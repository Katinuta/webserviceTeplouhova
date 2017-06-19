package by.teplouhova.webservice.repositories;

import by.teplouhova.webservice.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository work with Task
 */

public interface TaskRepository extends JpaRepository<Task,Long> {
}
