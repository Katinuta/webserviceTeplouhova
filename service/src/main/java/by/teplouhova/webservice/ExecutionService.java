package by.teplouhova.webservice;

import by.teplouhova.webservice.emuns.StatusEnum;

/**
 * Created by Admin on 02.02.17.
 */

public interface ExecutionService {

    Execution addExecution(Execution execution);

    Execution editExecution(Execution execution);

    Execution getExecution(long id);

    void executionStart(Execution execution) throws InterruptedException;

    StatusEnum getStatusExecution(Execution execution);

    void taskStart(Task task) throws InterruptedException;

    void pipelineExecute (String currentTaskName,Execution execution) throws InterruptedException;

}
