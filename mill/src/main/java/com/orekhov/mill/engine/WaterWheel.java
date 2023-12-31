package com.orekhov.mill.engine;

import com.orekhov.mill.bean.Water;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaterWheel extends Engine{
    private final Logger logger = LoggerFactory.getLogger(WaterWheel.class);

    private final Queue<Water> waterFlow;

    private static final int MAX_POWER = 10;

    private final ExecutorService executor = Executors.newFixedThreadPool(2);


    public WaterWheel(Queue<Water> waterFlow) {
        setName("Thread.WaterWheel");
        this.waterFlow = waterFlow;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            try {
                Water water = waterFlow.poll();
                this.executor.execute(() -> {
                    if(water != null && this.getPower() < MAX_POWER){
                        this.incPower(water.getPower());
                    }
                });
                Thread.sleep(200);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
