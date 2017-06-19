package by.teplouhova.webservice.typeaction.impl;

import by.teplouhova.webservice.Action;
import by.teplouhova.webservice.emuns.StatusEnum;

/**
 * Class for type action print
 */
public class PrintTypeAction extends Action {

    @Override
    public void doAction() {

        System.out.println(super.getTask().getName());
        super.getTask().setStatus(StatusEnum.COMPLETED);

    }
}
