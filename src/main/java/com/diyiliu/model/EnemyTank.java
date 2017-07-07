package com.diyiliu.model;

import com.diyiliu.model.base.Tank;
import com.diyiliu.thread.EnemyShoot;
import com.diyiliu.util.Constant;

import java.util.Vector;

/**
 * Description: EnemyTank
 * Author: DIYILIU
 * Update: 2017-07-04 14:05
 */
public class EnemyTank extends Tank implements Runnable {

    private Vector tanks;
    private Vector bulletVector;

    public EnemyTank(Vector tanks, Vector bullets) {

        this.type = Constant.Army.ARMY_ENEMY;
        this.tanks = tanks;
        this.bulletVector = bullets;
    }

    @Override
    public void run() {

        // 发射子弹
        new Thread(new EnemyShoot(this, bulletVector)).start();

        while (lives.get() > 0) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int direct = (int) (Math.random() * 4);
            persistMove(direct);
        }
    }

    public void persistMove(int direct) {

        int step = (int) (Math.random() * 50 + 20);
        switch (direct) {
            case Constant.Direct.DIRECT_UP:

                for (int i = 0; i < step; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!moveUP() || checkTouch(tanks)) {
                        break;
                    }
                }

                break;
            case Constant.Direct.DIRECT_LEFT:

                for (int i = 0; i < step; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!moveLeft() || checkTouch(tanks)) {
                        break;
                    }
                }
                break;
            case Constant.Direct.DIRECT_DOWN:

                for (int i = 0; i < step; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!moveDown() || checkTouch(tanks)) {
                        break;
                    }
                }
                break;
            case Constant.Direct.DIRECT_RIGHT:

                for (int i = 0; i < step; i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!moveRight() || checkTouch(tanks)) {
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }
}
