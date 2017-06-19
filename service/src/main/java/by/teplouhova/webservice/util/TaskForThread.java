package by.teplouhova.webservice.util;

import by.teplouhova.webservice.Task;
import by.teplouhova.webservice.emuns.StatusEnum;
import by.teplouhova.webservice.impl.ExecutionServiceImpl;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Class contains list task for one thread 
 */

public class TaskForThread implements Callable<Thread.State> {

    List<Task> tasksList;

    @Override
    public Thread.State call() throws Exception {
        for (Task task:tasksList) {
            //execute task
          new ExecutionServiceImpl().taskStart(task);
            if(task.getStatus().equals(StatusEnum.FAILED)){
                Thread.currentThread().interrupt();
            }
        }
        return Thread.currentThread().getState();
    }

    public TaskForThread(List<Task> tasksList) {
        this.tasksList = tasksList;
    }
}
