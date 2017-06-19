package by.teplouhova.webservice.controllers;

import by.teplouhova.webservice.Execution;
import by.teplouhova.webservice.Pipeline;
import by.teplouhova.webservice.Task;
import by.teplouhova.webservice.emuns.StatusEnum;
import by.teplouhova.webservice.impl.ExecutionServiceImpl;
import by.teplouhova.webservice.impl.PipelineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for execution.Class contains methods for execution pipeline,
 * get status execution
 */

@RestController
public class ExecutionController {

    @Autowired
    private PipelineServiceImpl pipelineServiceImpl;

    @Autowired
    private ExecutionServiceImpl executionServiceImpl;

    //create and execute new exection
    @RequestMapping(value = "/execution", method = RequestMethod.POST,
            produces = "application/yaml")
    public ResponseEntity<Execution> createExecution(@RequestParam("pipeline name") String name,
                                                     @RequestParam("context name") String description) {
        Pipeline pipeline = pipelineServiceImpl.findByNameAndDescription(name, description);
        Execution execution = new Execution();
        execution.setPipeline(pipeline);
        execution = executionServiceImpl.addExecution(execution);
        for (Task task : execution.getPipeline().getTasks()) {
            task.setStatus(StatusEnum.PENDING);
        }
        try {
            //begin execution
            executionServiceImpl.executionStart(execution);
        } catch (InterruptedException e) {
        } finally {
            //set status execution
            execution.setStatus(executionServiceImpl.getStatusExecution(execution));
            executionServiceImpl.editExecution(execution);
            return new ResponseEntity<Execution>(execution, HttpStatus.CREATED);
        }
    }

    //get status execution by id
    @RequestMapping(value = "/execution/{id}", method = RequestMethod.GET,
            produces = "application/yaml")
    public ResponseEntity<Execution> getExecution(@PathVariable int id) {
        Execution execution = executionServiceImpl.getExecution(id);
        if (execution == null) {
            return new ResponseEntity<Execution>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Execution>(execution, HttpStatus.OK);
    }
}
