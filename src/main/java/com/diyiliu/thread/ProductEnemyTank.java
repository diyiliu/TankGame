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
    private Vector<Tank> tanks;

    private Vector<Bullet> bullets;

    private int speed = 2;
    private int bulletCount = 1;

    public ProductEnemyTank(AtomicInteger panelCount, AtomicInteger count, Vector<Tank> tanks, Vector<Bullet> bullets){

        this.panelCount = panelCount;
        this.count = count;
        this.tanks = tanks;
        this.bullets = bullets;

    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(2000);

                if (Constant.GAME_PAUSE){
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count.get() < 1){

                break;
            }

            if (panelCount.get() < 5){
                EnemyTank enemyTank = new EnemyTank(tanks, bullets);
                enemyTank.setX(10);
                enemyTank.setY(10);
                enemyTank.setSpeed(speed);
                enemyTank.getBulletCount().set(bulletCount);
                enemyTank.setDirect(Constant.Direct.DIRECT_DOWN);
                enemyTank.setColor(Color.CYAN);
                tanks.add(enemyTank);
                new Thread(enemyTank).start();

                panelCount.incrementAndGet();
                count.decrementAndGet();
            }
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }
}
