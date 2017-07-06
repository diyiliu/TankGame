package com.diyiliu.model;

import com.diyiliu.model.base.Tank;
import com.diyiliu.util.Constant;

import java.util.Vector;

/**
 * Description: EnemyTank
 * Author: DIYILIU
 * Update: 2017-07-04 14:05
 */
public class EnemyTank extends Tank implements Runnable {

    private Vector tanks;

    public EnemyTank(Vector tanks) {

        this.tanks = tanks;
    }

    @Override
    public void run() {

        while (lives.get() > 0) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int direct = (int) (Math.random() * 4);
            persistMove(direct);

            if (this.getBullet() == null || !this.getBullet().isAlive()) {

                Bullet bullet = this.shootBullet();
                this.setBullet(bullet);
                bullet.start();
            }
        }
    }

    public void persistMove(int direct) {

        int step = (int) (Math.random() * 50 + 20);
        switch (direct) {
            case Constant.Derict.DERICT_UP:

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
            case Constant.Derict.DERICT_LEFT:

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
            case Constant.Derict.DERICT_DOWN:

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
            case Constant.Derict.DERICT_RIGHT:

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

    public Vector getTanks() {
        return tanks;
    }

    public void setTanks(Vector tanks) {
        this.tanks = tanks;
    }
}
