package by.teplouhova.webservice.typeaction.impl;

import by.teplouhova.webservice.Action;
import by.teplouhova.webservice.emuns.StatusEnum;

/**
 * Class for type action Delayed
 */

public class DelayedTypeAction extends Action  {

    @Override
    public void doAction() throws InterruptedException {

        System.out.println(super.getTask().getName());
        Thread.currentThread().sleep(10*1000);
        super.getTask().setStatus(StatusEnum.COMPLETED);
    }
}
