package by.teplouhova.webservice.typeaction.impl;

import by.teplouhova.webservice.Action;
import by.teplouhova.webservice.emuns.StatusEnum;
import by.teplouhova.webservice.emuns.TypeActionEnum;

/**
 * Class for type action Random
 */

public class RandomTypeAction extends Action {

    @Override
    public void doAction() throws InterruptedException {
        System.out.println(super.getTask().getName());
        Thread.currentThread().sleep(1*1000);
        super.getTask().setStatus(StatusEnum.values()[(int) Math.random()* TypeActionEnum.values().length]);
    }
}
