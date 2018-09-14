package com.fmx.async;

import javafx.application.Platform;

import java.util.concurrent.Semaphore;

public class Advancer {
    private boolean able;
    private final Updatable worker;

    public Advancer(Updatable worker) {
        this.worker = worker;
        able = false;
    }

    public final synchronized void advance(Updatable renderer) {
        if (able) return;
        able = true;
        new Thread(() -> {
            long timer = System.nanoTime();
            Semaphore semaphore = new Semaphore(1);
            while (able) try {
                long now = System.nanoTime();
                double moment = (now - timer) * 0.000001;
                timer = now;
                worker.update(moment);
                semaphore.acquire();
                Platform.runLater(() -> {
                    renderer.update(moment);
                    semaphore.release();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public final void shutdown() {
        able = false;
    }

}
