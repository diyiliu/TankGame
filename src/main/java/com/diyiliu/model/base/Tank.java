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

    protected int type = Constant.Army.ARMY_ENEMY;

    protected int x;
    protected int y;

    protected int direct;
    protected int speed = 1;

    protected Color color;

    protected Bullet bullet;

    // 可以移动（direct 方向）
    protected boolean movable = true;

    // 生命值
    protected AtomicInteger lives = new AtomicInteger(1);

    public Tank() {

    }

    public Tank(int x, int y, int direct, Color color) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.color = color;
    }

    public boolean moveUP() {
        setDirect(Constant.Derict.DERICT_UP);
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
        setDirect(Constant.Derict.DERICT_LEFT);
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
        setDirect(Constant.Derict.DERICT_DOWN);
        y = getY() + getSpeed();

        if (y + 80 > Constant.Draw.PANEL_HEIGHT) {
            setY(Constant.Draw.PANEL_HEIGHT - 80);
            movable = false;
        } else {
            setY(y);
            movable = true;
        }

        return movable;
    }

    public boolean moveRight() {
        setDirect(Constant.Derict.DERICT_RIGHT);
        x = getX() + getSpeed();

        if (x + 45 > Constant.Draw.PANEL_WIDTH) {
            setX(Constant.Draw.PANEL_WIDTH - 45);
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
            case Constant.Derict.DERICT_UP:
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
            case Constant.Derict.DERICT_LEFT:

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
            case Constant.Derict.DERICT_DOWN:
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
            case Constant.Derict.DERICT_RIGHT:

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
            case Constant.Derict.DERICT_UP:
                i = getX() + 15;
                j = getY();
                break;
            case Constant.Derict.DERICT_LEFT:
                i = getX();
                j = getY() + 15;
                break;
            case Constant.Derict.DERICT_DOWN:
                i = getX() + 15;
                j = getY() + 30;
                break;
            case Constant.Derict.DERICT_RIGHT:
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

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public AtomicInteger getLives() {
        return lives;
    }

    public void setLives(AtomicInteger lives) {
        this.lives = lives;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
