package com.orekhov.mill.machine;

public class Machine extends Thread{
    private boolean on;

    public boolean isOn() {
        return on;
    }

    public void startOn(){
        on = true;
    }

    public void stopOn(){
        on = false;
    }
}