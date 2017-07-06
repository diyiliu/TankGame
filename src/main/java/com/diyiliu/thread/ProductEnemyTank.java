package com.diyiliu.thread;

import com.diyiliu.model.EnemyTank;
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
    private Vector<EnemyTank> enemyTanks;

    public ProductEnemyTank(AtomicInteger count, Vector<EnemyTank> enemyTanks){

        this.count = count;
        this.enemyTanks = enemyTanks;
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

            EnemyTank enemyTank = new EnemyTank(enemyTanks);
            enemyTank.setX(10);
            enemyTank.setY(10);
            enemyTank.setSpeed(2);
            enemyTank.setDirect(Constant.Derict.DERICT_DOWN);
            enemyTank.setColor(Color.CYAN);
            enemyTanks.add(enemyTank);
            new Thread(enemyTank).start();
        }
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
}
