package com.diyiliu.thread;

import com.diyiliu.model.Bullet;
import com.diyiliu.model.EnemyTank;
import com.diyiliu.model.base.Tank;
import com.diyiliu.util.Constant;

import java.awt.*;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: ProductEnemyTank
 * Author: DIYILIU
 * Update: 2017-07-06 15:58
 */
public class ProductEnemyTank extends Thread {

    private AtomicInteger count;

    private AtomicInteger panelCount;
    private Vector<Tank> enemyTanks;

    private Vector<Bullet> bullets;

    public ProductEnemyTank(AtomicInteger panelCount, AtomicInteger count, Vector<Tank> enemyTanks, Vector<Bullet> bullets){

        this.panelCount = panelCount;
        this.count = count;
        this.enemyTanks = enemyTanks;
        this.bullets = bullets;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count.get() < 1){

                break;
            }

            if (panelCount.get() < 5){
                EnemyTank enemyTank = new EnemyTank(enemyTanks, bullets);
                enemyTank.setX(10);
                enemyTank.setY(10);
                enemyTank.setSpeed(2);
                enemyTank.getBulletCount().set(1);
                enemyTank.setDirect(Constant.Direct.DIRECT_DOWN);
                enemyTank.setColor(Color.CYAN);
                enemyTanks.add(enemyTank);
                new Thread(enemyTank).start();

                panelCount.incrementAndGet();
                count.decrementAndGet();
            }
        }
    }
}
