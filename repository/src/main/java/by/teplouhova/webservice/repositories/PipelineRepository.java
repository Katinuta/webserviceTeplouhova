package by.teplouhova.webservice.repositories;

import by.teplouhova.webservice.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * CRepository work with Pipeline
 */

public interface PipelineRepository extends JpaRepository<Pipeline,Long>{

    @Query("select p from Pipeline p where p.name =:name and p.description=:description")
    Pipeline findPipelineByNameAndDescription(String name, String description);

}
