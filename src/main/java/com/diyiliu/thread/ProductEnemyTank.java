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
    private Vector<Tank> enemyTanks;

    private Vector<Bullet> bullets;

    public ProductEnemyTank(AtomicInteger count, Vector<Tank> enemyTanks, Vector<Bullet> bullets){

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

            if (count.getAndDecrement() < 1){

                break;
            }

            EnemyTank enemyTank = new EnemyTank(enemyTanks, bullets);
            enemyTank.setX(10);
            enemyTank.setY(10);
            enemyTank.setSpeed(2);
            enemyTank.getBulletCount().set(2);
            enemyTank.setDirect(Constant.Derict.DIRECT_DOWN);
            enemyTank.setColor(Color.CYAN);
            enemyTanks.add(enemyTank);
            new Thread(enemyTank).start();
        }
    }
}
