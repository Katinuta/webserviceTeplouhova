package by.teplouhova.webservice.impl;

import by.teplouhova.webservice.Execution;
import by.teplouhova.webservice.ExecutionService;
import by.teplouhova.webservice.Pipeline;
import by.teplouhova.webservice.Task;
import by.teplouhova.webservice.emuns.StatusEnum;
import by.teplouhova.webservice.repositories.ExecutionRepository;
import by.teplouhova.webservice.repositories.TaskRepository;
import by.teplouhova.webservice.util.ParallelExecutionUtil;
import by.teplouhova.webservice.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Class implements method work with  Execution pipeline
 */

@Service
@Transactional
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Execution addExecution(Execution execution) {
        Execution saveExecution = executionRepository.saveAndFlush(execution);
        return saveExecution;
    }

    @Override
    public Execution editExecution(Execution execution) {
        return executionRepository.saveAndFlush(execution);
    }

    @Override
    public Execution getExecution(long id) {
        return executionRepository.getOne(id);
    }

    //method begin execution pipeline
    @Override
    public void executionStart(Execution execution) throws InterruptedException {

        //get name task begin
        String currentTaskName = TaskUtil.getBeginTaskName(execution.getPipeline().getTransitions());
        try {
            //cycle for execution all tasks of pipeline
            while (currentTaskName == null) {
                pipelineExecute(currentTaskName, execution);
            }
            if (currentTaskName == null) { //action for end pipeline
                execution.setEndTime(new Timestamp(System.currentTimeMillis()));
            }
        } finally {
            //update execution
            executionRepository.saveAndFlush(execution);
        }

    }

    //execute pipeline
    public void pipelineExecute(String currentTaskName, Execution execution) throws InterruptedException {

        Pipeline pipeline = execution.getPipeline();
        //get current task
        Task task = TaskUtil.getTaskByName(currentTaskName, pipeline.getTasks());
        taskStart(task);
        if (task.getStatus().equals(StatusEnum.COMPLETED) ||
                task.getStatus().equals(StatusEnum.SKIPPED)) {
            //get list next tasks
            List<String> nextTasks =
                    TaskUtil.getNextListTask(currentTaskName, pipeline.getTransitions());
            if (nextTasks == null) {
                currentTaskName = null;
            } else if (nextTasks.size() == 1) {
                currentTaskName = nextTasks.get(0);
            } else {
                //if list next tasks contains 2 and more task execute
                // tasks parallel until point connection
                ParallelExecutionUtil.executeParalelTask(ParallelExecutionUtil.
                        getListParallelTasks(nextTasks, execution, currentTaskName));
            }
        }
        if (task.getStatus().equals(StatusEnum.FAILED)) {
            execution.setStatus(StatusEnum.FAILED);
            Thread.currentThread().interrupt();
        }
    }

    //execute task
    @Override
    public void taskStart(Task task) throws InterruptedException {
        task.setStatus(StatusEnum.IN_PROGRESS);
        task.setStartTime(new Timestamp(System.currentTimeMillis()));
        //act action by type
        task.getAction().getType().getAction().doAction();
        task.setEndTime(new Timestamp(System.currentTimeMillis()));
        taskRepository.saveAndFlush(task);
    }

    //method for get status of execution
    @Override
    public StatusEnum getStatusExecution(Execution execution) {
        StatusEnum status = StatusEnum.COMPLETED;
        for (Task task : execution.getPipeline().getTasks()) {
            if (task.getStatus().equals(StatusEnum.COMPLETED)) {
                continue;
            }
            if (task.getStatus().equals(StatusEnum.FAILED)) {
                status = StatusEnum.FAILED;
                break;
            }
            if (task.getStatus().equals(StatusEnum.IN_PROGRESS)) {
                status = StatusEnum.FAILED;
                break;
            }
        }
        return status;
    }
}