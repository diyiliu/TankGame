package com.diyiliu.panel;

import com.diyiliu.model.Bomb;
import com.diyiliu.model.Bullet;
import com.diyiliu.model.EnemyTank;
import com.diyiliu.model.HeroTank;
import com.diyiliu.model.base.Tank;
import com.diyiliu.thread.ProductEnemyTank;
import com.diyiliu.util.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: DrawPanel
 * Author: DIYILIU
 * Update: 2017-07-04 14:03
 */
public class DrawPanel extends JPanel implements KeyListener, Runnable {

    private HeroTank heroTank;

    private AtomicInteger enemyCount = new AtomicInteger(20);

    private Vector<EnemyTank> enemyTanks = new Vector<>();

    private Vector<HeroTank> heroTanks = new Vector<>();

    private Vector<Bomb> bombs = new Vector<>();

    private ImageIcon starImage;

    public DrawPanel() {

        heroTank = new HeroTank();
        heroTank.setSpeed(5);
        heroTank.getLives().set(3);
        heroTanks.add(heroTank);

        new ProductEnemyTank(enemyCount, enemyTanks).start();

        starImage = new ImageIcon(ClassLoader.getSystemResource("star3.png"));
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (enemyCount.get() == 20){

            drawStar(g);
        }

        if (heroTank.getLives().get() > 0) {

            drawTank(heroTank, g);
            drawBullet(heroTank, g, enemyTanks);
        }


        for (int i = 0; i < enemyTanks.size(); i++) {

            EnemyTank enemyTank = enemyTanks.get(i);

            drawTank(enemyTank, g);
            drawBullet(enemyTank, g, heroTanks);
        }

        for (int i = 0; i < bombs.size(); i++) {

            Bomb bomb = bombs.get(i);

            if (bomb.getQueue().isEmpty()) {
                bombs.remove(bomb);
            } else {
                ImageIcon imageIcon = (ImageIcon) bomb.getQueue().poll();
                g.drawImage(imageIcon.getImage(), bomb.getX(), bomb.getY(), 30, 30, Color.RED, this);
            }
        }
    }


    public void drawTank(Tank tank, Graphics g) {

        g.setColor(tank.getColor());

        int x = tank.getX();
        int y = tank.getY();
        switch (tank.getDirect()) {

            case Constant.Derict.DERICT_UP:

                g.fill3DRect(x, y, 8, 30, false);
                g.fill3DRect(x + 8, y + 10, 14, 15, false);
                g.fill3DRect(x + 22, y, 8, 30, false);
                g.fillOval(x + 9, y + 11, 12, 12);

                g.drawLine(x + 15, y + 17, x + 15, y);

                // 履带效果
                for (int i = 0; i < 4; i++) {

                    g.fill3DRect(x + 1, y + (i + 1) * 6, 5, 1, false);
                    g.fill3DRect(x + 22, y + (i + 1) * 6, 5, 1, false);
                }

                break;
            case Constant.Derict.DERICT_LEFT:

                g.fill3DRect(x, y, 30, 8, false);
                g.fill3DRect(x + 10, y + 8, 15, 14, false);
                g.fill3DRect(x, y + 22, 30, 8, false);
                g.fillOval(x + 11, y + 9, 12, 12);

                g.drawLine(x + 17, y + 15, x, y + 15);

                for (int i = 0; i < 4; i++) {

                    g.fill3DRect(x + (i + 1) * 6, y + 2, 1, 5, false);
                    g.fill3DRect(x + (i + 1) * 6, y + 23, 1, 5, false);
                }

                break;
            case Constant.Derict.DERICT_DOWN:

                g.fill3DRect(x, y, 8, 30, false);
                g.fill3DRect(x + 8, y + 5, 14, 15, false);
                g.fill3DRect(x + 22, y, 8, 30, false);
                g.fillOval(x + 9, y + 6, 12, 12);

                g.drawLine(x + 15, y + 12, x + 15, y + 30);

                for (int i = 0; i < 4; i++) {

                    g.fill3DRect(x + 1, y + (i + 1) * 6, 5, 1, false);
                    g.fill3DRect(x + 22, y + (i + 1) * 6, 5, 1, false);
                }

                break;
            case Constant.Derict.DERICT_RIGHT:

                g.fill3DRect(x, y, 30, 8, false);
                g.fill3DRect(x + 5, y + 8, 15, 14, false);
                g.fill3DRect(x, y + 22, 30, 8, false);
                g.fillOval(x + 6, y + 9, 12, 12);

                g.drawLine(x + 12, y + 15, x + 30, y + 15);

                for (int i = 0; i < 4; i++) {

                    g.fill3DRect(x + (i + 1) * 6, y + 2, 1, 5, false);
                    g.fill3DRect(x + (i + 1) * 6, y + 23, 1, 5, false);
                }
                break;
            default:
                break;

        }
    }

    public void drawBullet(Tank tank, Graphics g, Vector tanks) {

        if (tank.getBullet() != null && tank.getBullet().isAlive()) {
            Bullet bullet = tank.getBullet();

            boolean hit = false;
            if (tanks != null && tanks.size() > 0) {
                for (int i = 0; i < tanks.size(); i++) {

                    Tank t = (Tank) tanks.get(i);
                    if (hitTank(bullet, t)) {
                        hit = true;

                        // 子弹终结
                        bullet.setLive(false);
                        // 坦克生命减一
                        int life = t.getLives().decrementAndGet();

                        if (life < 1) {

                            Bomb bomb = new Bomb(t.getX(), t.getY());
                            bombs.add(bomb);

                            tanks.remove(t);
                        }

                        break;
                    }
                }
            }

            if (!hit) {
                g.setColor(bullet.getColor());
                g.draw3DRect(bullet.getX(), bullet.getY(), 1, 1, true);
            }
        }
    }


    public void drawStar(Graphics g){

        g.drawImage(starImage.getImage(), 10, 10, 30, 30, this);
        if (starImage.getDescription().contains("star3")){

            starImage = new ImageIcon(ClassLoader.getSystemResource("star4.png"));
        }else if (starImage.getDescription().contains("star4")){

            starImage = new ImageIcon(ClassLoader.getSystemResource("star3.png"));
        }
    }

    /**
     * 子弹是否击中坦克
     *
     * @param bullet
     * @param tank
     * @return
     */
    public boolean hitTank(Bullet bullet, Tank tank) {

        if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 30
                && bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 30) {

            return true;
        }

        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:

                heroTank.moveUP();
                break;
            case KeyEvent.VK_LEFT:

                heroTank.moveLeft();
                break;
            case KeyEvent.VK_DOWN:

                heroTank.moveDown();
                break;
            case KeyEvent.VK_RIGHT:

                heroTank.moveRight();
                break;

            case KeyEvent.VK_SPACE:

                if (heroTank.getBullet() != null && heroTank.getBullet().isAlive()) {
                    break;
                }

                Bullet bullet = heroTank.shootBullet();
                heroTank.setBullet(bullet);
                bullet.start();

                break;
            default:
                break;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
