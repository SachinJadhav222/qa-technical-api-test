package com.patest;

/**
 * Created by SOHAM on 11/03/2018.
 */
public class Utility {
    public void sleepTimer(int sleepTime){
        try {
            Thread.sleep(1000*sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
