package by.teplouhova.webservice.typeaction.impl;

import by.teplouhova.webservice.Action;
import by.teplouhova.webservice.emuns.StatusEnum;

/**
 * Class for type action Completed
 */

public class CompletedTypeAction extends Action {

    @Override
    public void doAction() throws InterruptedException {

        System.out.println(super.getTask().getName());
        Thread.currentThread().sleep(1 * 1000);
        super.getTask().setStatus(StatusEnum.COMPLETED);

    }
}
