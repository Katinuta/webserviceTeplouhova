package by.teplouhova.webservice.impl;

import by.teplouhova.webservice.Pipeline;
import by.teplouhova.webservice.PipelineService;
import by.teplouhova.webservice.repositories.PipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class implements method work with  pipeline
 */

@Service
@Transactional
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    private PipelineRepository pipelineRepository;

    //create pipeline
    @Override
    public Pipeline addPipeline(Pipeline pipeline) {
        return pipelineRepository.saveAndFlush(pipeline);
    }

    //delete pipeline by id
    @Override
    public void delete(long id) {
        pipelineRepository.delete(id);
    }

    //update pipeline
    @Override
    public Pipeline editPipeline(Pipeline pipeline) {

        return pipelineRepository.saveAndFlush(pipeline);
    }

    //get all pipeline
    @Override
    public List<Pipeline> getAll() {
        return pipelineRepository.findAll();
    }

    //get pipeline by id
    @Override
    public Pipeline getPipeline(int id) {
        return pipelineRepository.findOne((long) id);
    }

    // determine pipeline is exist
    @Override
    public boolean isPipelineExist(Pipeline pipeline) {
        return pipelineRepository.exists((long) pipeline.getPipelineId());
    }

    //find pipeline by name and description
    @Override
    public Pipeline findByNameAndDescription(String name, String description) {
        return pipelineRepository.findPipelineByNameAndDescription(name, description);
    }
}
