package com.diyiliu.model;

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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct) {

                case Constant.Derict.DERICT_UP:

                    y -= speed;
                    break;
                case Constant.Derict.DERICT_LEFT:

                    x -= speed;
                    break;
                case Constant.Derict.DERICT_DOWN:

                    y += speed;
                    break;
                case Constant.Derict.DERICT_RIGHT:

                    x += speed;
                    break;
                default:
                    break;
            }

            if (x < 0 || y < 0 || x > Constant.Draw.PANEL_WIDTH || y > Constant.Draw.PANEL_HEIGHT){

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

    public void setLive(boolean live) {
        this.live = live;
    }
}
