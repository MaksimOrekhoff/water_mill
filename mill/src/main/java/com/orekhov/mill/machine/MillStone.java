package com.orekhov.mill.machine;

import com.orekhov.mill.bean.Flour;
import com.orekhov.mill.bean.Millet;
import com.orekhov.mill.engine.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MillStone extends Machine {
    private final Logger logger = LoggerFactory.getLogger(MillStone.class);

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    private final Engine engine;
    private final Queue<Millet> mallets;
    private final Queue<Flour> floursQueue;

    public MillStone(Engine engine, Queue<Millet> mallets, Queue<Flour> floursQueue) {
        this.engine = engine;
        this.mallets = mallets;
        this.floursQueue = floursQueue;
    }

    @Override
    public void run() {
        while(!isInterrupted()){
            if(engine.getPower() > 0){
                engine.decPower(1);
                try {
                    this.executor.submit(()->{
                        Millet millet = mallets.poll();
                        if(millet != null){
                            floursQueue.offer(new Flour());
                            logger.info("Flour: " + floursQueue.size());
                        }
                        logger.info("Power left: " + engine.getPower());
                    });
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
