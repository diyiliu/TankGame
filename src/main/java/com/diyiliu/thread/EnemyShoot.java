package com.diyiliu.thread;

import com.diyiliu.model.Bullet;
import com.diyiliu.model.base.Tank;
import com.diyiliu.util.Constant;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: EnemyShoot
 * Author: DIYILIU
 * Update: 2017-07-07 10:55
 */
public class EnemyShoot implements Runnable {

    private Tank tank;

    private Vector<Bullet> bullets;

    public EnemyShoot(Tank tank, Vector<Bullet> bullets){

        this.tank = tank;
        this.bullets = bullets;
    }

    @Override
    public void run() {

        while (tank.getLives().get() > 0){

            try {
                Thread.sleep(100);

                if (Constant.GAME_PAUSE){
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AtomicInteger bulletCount = tank.getBulletCount();
            if (bulletCount.get() < 1) {

                if (tank.loadBullets()){
                    shoot(tank);
                }
            }else {
                shoot(tank);
            }
        }
    }

    public void shoot(Tank tank){
        Bullet bullet = tank.shootBullet();
        bullet.setType(Constant.Army.ARMY_ENEMY);
        bullet.start();

        tank.getBulletCount().decrementAndGet();
        tank.getBullets().add(bullet);

        bullets.add(bullet);
    }
}
