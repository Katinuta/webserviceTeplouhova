package by.teplouhova.webservice.util;

import by.teplouhova.webservice.Execution;
import by.teplouhova.webservice.Pipeline;
import by.teplouhova.webservice.Task;
import by.teplouhova.webservice.Transition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class contains methods for getting list tasks for  parallel execute until point connection,
 * get all task for thread until connection, method parallel execution tasks
 */

public class ParallelExecutionUtil {

    //get list tasks for  parallel execute until point connection
    public static List<Callable<Thread.State>> getListParallelTasks(List<String> nextTasks,
                                                                   Execution execution, String currentTask) {

        List<Callable<Thread.State>> listParalelTasks = new ArrayList<>();
        List<Task> tasks = execution.getPipeline().getTasks();
        List<Transition> transitions = execution.getPipeline().getTransitions();
        //get list points connection threads
        List<String> pointsConnection = TransitionsUtil.getPoints(TransitionsUtil.getListEnds(transitions));
        Pipeline pipeline = execution.getPipeline();
        for (int i = 0; i < nextTasks.size(); i++) {
            //get task
            String nextTask = nextTasks.get(i);
            String taskAfterNext = null;
            List<String> afterNextTasksList = TaskUtil.getNextListTask(nextTask, transitions);
            if (afterNextTasksList.size() == 1) {
                taskAfterNext = afterNextTasksList.get(0);
            } else {
                //case will be treated
            }
            //determine list task until point connection
            if (pointsConnection.contains(taskAfterNext)) {
                List<Task> listTasksForThread = new ArrayList<>();
                listTasksForThread.add(TaskUtil.getTaskByName(nextTask, tasks));
                //get list tasks for thread
                listParalelTasks.add(new TaskForThread(listTasksForThread));
                currentTask=taskAfterNext;
            } else {
                //get list tasks for thread
                listParalelTasks.add(new TaskForThread(getListTaskForThread(nextTask,pipeline,currentTask)));
            }
        }
        return listParalelTasks;
    }

    //get all task for thread until connection
    public static List<Task> getListTaskForThread(String taskName, Pipeline pipeline,String currentTask) {
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(TaskUtil.getTaskByName(taskName, pipeline.getTasks()));
        List<String> endsTransition =TaskUtil.getNextListTask(taskName, pipeline.getTransitions());
        List<String> endsList = TransitionsUtil.getListEnds(pipeline.getTransitions());
        List<String> pointsConnection = TransitionsUtil.getPoints(endsList);
        if (endsTransition.size() == 1) {
            String taskEnd = endsTransition.get(0);
            while (!pointsConnection.contains(taskEnd)) {
                Task task = TaskUtil.getTaskByName(taskEnd, pipeline.getTasks());
                tasksList.add(task);
                taskEnd =TaskUtil.getNextListTask(taskEnd, pipeline.getTransitions()).get(0);
            }
            //point connection
            currentTask=taskEnd;
        }else{
            //case will be treated
        }
        return tasksList;
    }

    //parallel execution tasks
    public static void executeParalelTask(List<Callable<Thread.State>> listParalelTask) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(listParalelTask.size());
        executor.invokeAll(listParalelTask);
        executor.shutdown();
    }

}
