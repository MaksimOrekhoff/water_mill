package com.orekhov.mill;

import com.orekhov.mill.bean.Flour;
import com.orekhov.mill.bean.MillState;
import com.orekhov.mill.bean.Millet;
import com.orekhov.mill.bean.Water;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MillService {
    private final Logger logger = LoggerFactory.getLogger(MillService.class);

    private final static BlockingQueue<Water> waterFlow = new ArrayBlockingQueue<>(10);
    private final static BlockingQueue<Millet> milletFlow = new ArrayBlockingQueue<>(10);
    private final static BlockingQueue<Flour> flourFlow = new ArrayBlockingQueue<>(10);

    private final Mill mill;

    private final ExecutorService waterPipeExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService milletPipeExecutor = Executors.newSingleThreadExecutor();

    public MillService() {
        mill = new Mill(waterFlow, milletFlow, flourFlow);
        mill.start();
    }

    public MillState getState() {
        return new MillState(mill, waterFlow, milletFlow, flourFlow);
    }

    public void addWater(int capacity) {
        waterPipeExecutor.execute(() -> {
            try {
                int counter = capacity;
                while (counter-- > 0) {
                    waterFlow.add(new Water());
                }
            } catch (IllegalStateException e) {
                logger.info("Water tank is full: " + e.getMessage());
            }
        });
    }

    public void dropFlour() {
        flourFlow.clear();
    }

    public void addMillet(int capacity) {
        milletPipeExecutor.execute(() -> {
            try {
                int counter = capacity;
                while (counter-- > 0) {
                    milletFlow.add(new Millet());
                }
            } catch (IllegalStateException e) {
                logger.info("Millet tank is full: " + e.getMessage());
            }
        });
    }
}
