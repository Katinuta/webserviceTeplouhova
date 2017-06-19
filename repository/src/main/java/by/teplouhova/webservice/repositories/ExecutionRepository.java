package by.teplouhova.webservice.repositories;

import by.teplouhova.webservice.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository work with Execution
 */

public interface ExecutionRepository extends JpaRepository<Execution,Long> {

}

