package by.teplouhova.webservice.util;

import by.teplouhova.webservice.Task;
import by.teplouhova.webservice.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains method
 */
public class TaskUtil {

    //method get begin task
    public static String getBeginTaskName(List<Transition> transitions) {
        String taskName = null;
        List<String> ends= TransitionsUtil.getListEnds(transitions);
        List<String> begins= TransitionsUtil.getListBegins(transitions);
        for (String name : begins) {
            if (ends.contains(name)) {
                continue;
            }
            taskName = name;
        }
        return taskName;
    }

    //get task by name
    public static Task getTaskByName(String name, List<Task> tasks) {
        Task task = null;
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(name)) {
                task = t;
            }
        }
        return task;
    }

    //get list next tasks
    public static List<String> getNextListTask(String currentTask, List<Transition> transitions) {
        List<String> nextTasks = new ArrayList<>();
        for (Transition t : transitions) {
            if (!t.getBegin().equals(currentTask)) {
                continue;
            }
            nextTasks.add(t.getEnd());
        }
        return nextTasks;
    }
}
