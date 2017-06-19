package by.teplouhova.webservice.controllers;

import by.teplouhova.webservice.Pipeline;
import by.teplouhova.webservice.impl.PipelineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Class contains controller for work with pipeline(CRUD operation)
 */

@RestController
public class PipelineController {

    @Autowired
    private PipelineServiceImpl pipelineServiceImpl;

    //create pipeline
    @RequestMapping(value = "/pipeline", method = RequestMethod.POST, consumes = "application/yaml")
    public ResponseEntity<Void> createPipeline(@RequestBody Pipeline pipeline, UriComponentsBuilder ucBuilder) {
        if (pipelineServiceImpl.isPipelineExist(pipeline)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        pipelineServiceImpl.addPipeline(pipeline);
        HttpHeaders headers = new HttpHeaders();
        //get uri
        headers.setLocation(ucBuilder.path("/pipeline/{id}").buildAndExpand(pipeline.getPipelineId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }

    //get all pipeline
    @RequestMapping(value = "/pipeline", method = RequestMethod.GET, produces = "application/yaml")
    public ResponseEntity<List<Pipeline>> listAllPipeline() {
        List<Pipeline> pipelineList = pipelineServiceImpl.getAll();
        if (pipelineList.isEmpty()) {
            return new ResponseEntity<List<Pipeline>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Pipeline>>(pipelineList, HttpStatus.OK);
    }

    // get pipeline by id
    @RequestMapping(value = "/pipeline/{id}", method = RequestMethod.GET, produces = "application/yaml")
    public ResponseEntity<Pipeline> getPipeline(@PathVariable int id) {
        System.out.println(id);
        Pipeline pipeline = pipelineServiceImpl.getPipeline(id);
        if (pipeline == null) {
            return new ResponseEntity<Pipeline>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pipeline>(pipeline, HttpStatus.OK);
    }

    //update pipeline
    @RequestMapping(value = "/pipeline/{id}", method = RequestMethod.PUT, consumes = "application/yaml")
    public ResponseEntity<Pipeline> updatePipeline(@PathVariable int id,
                                                   @RequestBody Pipeline pipeline) {
        Pipeline currentPipeline = pipelineServiceImpl.getPipeline(id);
        if (currentPipeline == null) {
            return new ResponseEntity<Pipeline>(HttpStatus.NOT_FOUND);
        }
        currentPipeline.setDescription(pipeline.getDescription());
        currentPipeline.setName(pipeline.getName());
        currentPipeline.setExecutionsList(pipeline.getExecutionsList());
        currentPipeline.setTasks(pipeline.getTasks());
        currentPipeline.setTransitions(pipeline.getTransitions());
        pipelineServiceImpl.editPipeline(currentPipeline);
        return new ResponseEntity<Pipeline>(currentPipeline, HttpStatus.OK);
    }

    //delete pipeline by id
    @RequestMapping(value = "/pipeline/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePipeline(@PathVariable long id) {
        pipelineServiceImpl.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
