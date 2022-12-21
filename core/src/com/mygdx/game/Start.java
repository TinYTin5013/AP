package com.mygdx.game;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Start {
    Result result = JUnitCore.runClasses(Testing.class);
    boolean k=result.wasSuccessful();

    public void getResult(){
        Result result= JUnitCore.runClasses(Testing.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

    }

}
