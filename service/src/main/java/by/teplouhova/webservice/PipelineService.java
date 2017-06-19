package by.teplouhova.webservice;

import java.util.List;

/**
 * Created by Admin on 02.02.17.
 */

public interface PipelineService {

    Pipeline addPipeline(Pipeline pipeline);

    void delete(long id);

    Pipeline editPipeline(Pipeline pipeline);

    List<Pipeline> getAll();

    Pipeline getPipeline(int id);

    boolean isPipelineExist(Pipeline pipeline);

    Pipeline findByNameAndDescription(String name, String description);

}
