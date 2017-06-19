package by.teplouhova.webservice.util;

import by.teplouhova.webservice.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains methods for getting list begins, list ends, point connection and
 * separation tasks
 */

public class TransitionsUtil {

    //get list ends
    public static List<String> getListEnds(List<Transition> transitions) {
        List<String> list = new ArrayList<>();
        for (Transition transition : transitions) {
            list.add(transition.getEnd());
        }
        return list;
    }

    //get list begins
    public static List<String> getListBegins(List<Transition> transitions) {
        List<String> list = new ArrayList<>();
        for (Transition transition : transitions) {
            list.add(transition.getBegin());
        }
        return list;
    }

    //point connection or separation tasks
    public static List<String> getPoints(List<String> beginsOrEndsList) {
        List<String> listPoint = new ArrayList<>();
        for (String s : beginsOrEndsList) {
            if (beginsOrEndsList.indexOf(s) == beginsOrEndsList.lastIndexOf(s)) {
                continue;
            }
            listPoint.add(s);
        }
        return listPoint;
    }
}
