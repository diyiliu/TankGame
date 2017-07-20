package com.diyiliu.model;

import com.diyiliu.model.base.Tank;
import com.diyiliu.util.Constant;

import java.awt.*;

/**
 * Description: Bullet
 * Author: DIYILIU
 * Update: 2017-07-04 14:04
 */
public class Bullet extends Thread {
    private int x;
    private int y;

    private int direct;
    private int speed = 10;
    private Color color = Color.cyan;

    private boolean live = true;

    private int type = Constant.Army.ARMY_ENEMY;

    private Tank tank;


    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (live) {

            try {
                // 子弹延时间隔
                Thread.sleep(50);

                if (Constant.GAME_PAUSE) {
                    continue;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct) {

                case Constant.Direct.DIRECT_UP:

                    y -= speed;
                    break;
                case Constant.Direct.DIRECT_LEFT:

                    x -= speed;
                    break;
                case Constant.Direct.DIRECT_DOWN:

                    y += speed;
                    break;
                case Constant.Direct.DIRECT_RIGHT:

                    x += speed;
                    break;
                default:
                    break;
            }

            if (x < 0 || y < 0 || x > Constant.Config.PANEL_WIDTH || y > Constant.Config.PANEL_HEIGHT) {

                break;
            }
        }
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

    public boolean isLive() {
        return live;
    }

    public void stopRunning() {
        this.live = false;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }
}
