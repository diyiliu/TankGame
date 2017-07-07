package com.diyiliu.model.base;

import com.diyiliu.model.Bullet;
import com.diyiliu.util.Constant;

import java.awt.*;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: Tank
 * Author: DIYILIU
 * Update: 2017-07-04 14:01
 */
public class Tank {

    protected int x;
    protected int y;

    protected int direct;
    protected int speed = 1;

    protected Color color;

    protected int type;

    protected Vector<Bullet> bullets = new Vector<>();

    protected AtomicInteger bulletCount = new AtomicInteger(1);

    // 可以移动（direct 方向）
    protected boolean movable = true;

    // 生命值
    protected AtomicInteger lives = new AtomicInteger(1);

    public Tank() {

    }

    /**
     * 装弹
     */
    public boolean loadBullets(){

        for (int i = 0; i < bullets.size(); i++){

            Bullet bullet = bullets.get(i);
            if (!bullet.isAlive()){

                bullets.remove(bullet);
                bulletCount.incrementAndGet();
            }
        }

        if (bulletCount.get() < 1){

            return false;
        }

        return true;
    }

    public boolean moveUP() {
        setDirect(Constant.Direct.DIRECT_UP);
        y = getY() - getSpeed();

        if (y < 0) {
            setY(0);
            movable = false;
        } else {
            setY(y);
            movable = true;
        }

        return movable;
    }

    public boolean moveLeft() {
        setDirect(Constant.Direct.DIRECT_LEFT);
        x = getX() - getSpeed();

        if (x < 0) {
            setX(0);
            movable = false;
        } else {
            setX(x);
            movable = true;
        }


        return movable;
    }

    public boolean moveDown() {
        setDirect(Constant.Direct.DIRECT_DOWN);
        y = getY() + getSpeed();

        if (y + Constant.Config.DOWN_OFFSET > Constant.Config.PANEL_HEIGHT) {
            setY(Constant.Config.PANEL_HEIGHT - Constant.Config.DOWN_OFFSET);
            movable = false;
        } else {
            setY(y);
            movable = true;
        }

        return movable;
    }

    public boolean moveRight() {
        setDirect(Constant.Direct.DIRECT_RIGHT);
        x = getX() + getSpeed();

        if (x + Constant.Config.RIGHT_OFFSET > Constant.Config.PANEL_WIDTH) {
            setX(Constant.Config.PANEL_WIDTH - Constant.Config.RIGHT_OFFSET);
            movable = false;
        } else {
            setX(x);
            movable = true;
        }

        return movable;
    }

    public boolean checkTouch(Vector tanks) {
        boolean isTouch = false;
        int size = tanks.size();

        Tank tank;
        switch (direct) {
            case Constant.Direct.DIRECT_UP:
                for (int i = 0; i < size; i++) {
                    tank = (Tank) tanks.get(i);

                    if (tank != this) {
                        if (Math.abs(x - tank.getX()) < 30 && y < tank.getY() + 30 && y > tank.getY()) {
                            isTouch = true;
                            break;
                        }
                    }
                }
                break;
            case Constant.Direct.DIRECT_LEFT:

                for (int i = 0; i < size; i++) {
                    tank = (Tank) tanks.get(i);

                    if (tank != this) {
                        if (Math.abs(y - tank.getY()) < 30 && x < tank.getX() + 30 && x > tank.getX()) {
                            isTouch = true;
                            break;
                        }
                    }
                }
                break;
            case Constant.Direct.DIRECT_DOWN:
                for (int i = 0; i < size; i++) {
                    tank = (Tank) tanks.get(i);

                    if (tank != this) {
                        if (Math.abs(x - tank.getX()) < 30 && y + 30 > tank.getY() && y < tank.getY()) {
                            isTouch = true;
                            break;
                        }
                    }
                }
                break;
            case Constant.Direct.DIRECT_RIGHT:

                for (int i = 0; i < size; i++) {
                    tank = (Tank) tanks.get(i);

                    if (tank != this) {
                        if (Math.abs(y - tank.getY()) < 30 && x + 30 > tank.getX() && x < tank.getX()) {
                            isTouch = true;
                            break;
                        }
                    }
                }
                break;
            default:
                break;
        }

        return isTouch;
    }

    public Bullet shootBullet() {
        int direct = getDirect();
        int i, j;
        switch (direct) {
            case Constant.Direct.DIRECT_UP:
                i = getX() + 15;
                j = getY();
                break;
            case Constant.Direct.DIRECT_LEFT:
                i = getX();
                j = getY() + 15;
                break;
            case Constant.Direct.DIRECT_DOWN:
                i = getX() + 15;
                j = getY() + 30;
                break;
            case Constant.Direct.DIRECT_RIGHT:
                i = getX() + 30;
                j = getY() + 15;
                break;
            default:
                i = 0;
                j = 0;
                break;
        }

        Bullet bullet = new Bullet(i, j, direct);
        bullet.setColor(this.getColor());
        bullet.setTank(this);

        return bullet;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AtomicInteger getLives() {
        return lives;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public AtomicInteger getBulletCount() {
        return bulletCount;
    }
}
