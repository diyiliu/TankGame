package com.diyiliu.panel;

import com.diyiliu.model.Bomb;
import com.diyiliu.model.Bullet;
import com.diyiliu.model.EnemyTank;
import com.diyiliu.model.HeroTank;
import com.diyiliu.model.base.Tank;
import com.diyiliu.panel.base.BasePanel;
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
public class DrawPanel extends BasePanel implements KeyListener, Runnable {

    // 英雄坦克
    private HeroTank heroTank;

    private boolean friendlyFire = false;

    // 敌人坦克画面数量（不超过5个）
    private AtomicInteger panelEnemy = new AtomicInteger(0);

    private int enCount = 10;
    private AtomicInteger enemyCount = new AtomicInteger(enCount);

    private Vector<Tank> tanks = new Vector<>();

    // 子弹
    private Vector<Bullet> bullets = new Vector<>();
    // 爆炸
    private Vector<Bomb> bombs = new Vector<>();

    // 积分
    private AtomicInteger score = new AtomicInteger(0);

    private ImageIcon starImage;

    private HeroTank heroFlag;
    private EnemyTank enemyFlag;

    public DrawPanel() {

        heroTank = new HeroTank();
        heroTank.setSpeed(3);
        heroTank.getLives().set(3);
        heroTank.getBulletCount().set(1);

        tanks.add(heroTank);

        new ProductEnemyTank(panelEnemy, enemyCount, tanks, bullets).start();

        starImage = new ImageIcon(ClassLoader.getSystemResource("star3.png"));

        heroFlag = new HeroTank(Constant.Config.PANEL_WIDTH + 20, Constant.Config.PANEL_HEIGHT - Constant.Config.DOWN_OFFSET, Constant.Direct.DIRECT_UP);
        heroFlag.setColor(Color.ORANGE);
        heroFlag.getLives().set(0);


        enemyFlag = new EnemyTank(Constant.Config.PANEL_WIDTH + 20, Constant.Config.PANEL_HEIGHT - Constant.Config.DOWN_OFFSET - 50, Constant.Direct.DIRECT_UP);
        enemyFlag.setColor(Color.CYAN);
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
        g.fillRect(0, 0, Constant.Config.PANEL_WIDTH, Constant.Config.PANEL_HEIGHT);
        g.setColor(Color.GRAY);
        g.fillRect(Constant.Config.PANEL_WIDTH, 0,
                Constant.Config.FRAME_WIDTH - Constant.Config.PANEL_HEIGHT, Constant.Config.FRAME_HEIGHT);

        g.drawImage(flagImg.getImage(), Constant.Config.PANEL_WIDTH + 20, 10,
                20, 20, this);
        Font font = new Font("宋体", Font.BOLD, 18);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(String.valueOf(score.get()), Constant.Config.PANEL_WIDTH + 70, 25);


        g.setColor(enemyFlag.getColor());
        g.drawString(String.valueOf(enCount - score.get()), Constant.Config.PANEL_WIDTH + 70,
                Constant.Config.PANEL_HEIGHT - Constant.Config.DOWN_OFFSET - 30);

        g.setColor(heroFlag.getColor());
        g.drawString(String.valueOf(heroTank.getLives().get()), Constant.Config.PANEL_WIDTH + 70,
                Constant.Config.PANEL_HEIGHT - Constant.Config.DOWN_OFFSET + 20);

        drawTank(enemyFlag, g);
        drawTank(heroFlag, g);


        if (enemyCount.get() == enCount) {

            drawStar(g);
        }

        for (int i = 0; i < bullets.size(); i++) {

            Bullet bullet = bullets.get(i);
            if (bullet.isLive()) {
                drawBullet(bullet, g, tanks);
            }
        }

        for (int i = 0; i < tanks.size(); i++) {

            Tank tank = tanks.get(i);

            if (tank.getLives().get() > 0) {

                drawTank(tank, g);
            }
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

    public void drawBullet(Bullet bullet, Graphics g, Vector tanks) {

        if (bullet.isAlive()) {

            boolean hit = false;
            if (tanks != null && tanks.size() > 0) {
                for (int i = 0; i < tanks.size(); i++) {

                    Tank t = (Tank) tanks.get(i);

                    if (hitTank(bullet, t, friendlyFire)) {
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

                        // 得分,画面坦克数量减1
                        if (bullet.getType() == Constant.Army.ARMY_HERO) {

                            score.incrementAndGet();
                            panelEnemy.decrementAndGet();
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


    public void drawStar(Graphics g) {

        g.drawImage(starImage.getImage(), 10, 10, 30, 30, this);
        if (starImage.getDescription().contains("star3")) {

            starImage = new ImageIcon(ClassLoader.getSystemResource("star4.png"));
        } else if (starImage.getDescription().contains("star4")) {

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
    public boolean hitTank(Bullet bullet, Tank tank, Boolean friendlyFire) {

        // 是否队友伤害
        if (!friendlyFire && bullet.getType() == tank.getType()) {

            return false;
        }

        if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 30
                && bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 30) {

            // 打中自己
            if (bullet.getTank() == tank) {
                return false;
            }

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
                if (heroTank.checkTouch(tanks)) {
                    if (heroTank.getDirect() == Constant.Direct.DIRECT_UP) {
                        break;
                    }
                }
                heroTank.moveUP();

                break;
            case KeyEvent.VK_LEFT:
                if (heroTank.checkTouch(tanks)) {
                    if (heroTank.getDirect() == Constant.Direct.DIRECT_LEFT) {
                        break;
                    }
                }
                heroTank.moveLeft();

                break;
            case KeyEvent.VK_DOWN:
                if (heroTank.checkTouch(tanks)) {
                    if (heroTank.getDirect() == Constant.Direct.DIRECT_DOWN) {
                        break;
                    }
                }
                heroTank.moveDown();

                break;
            case KeyEvent.VK_RIGHT:
                if (heroTank.checkTouch(tanks)) {
                    if (heroTank.getDirect() == Constant.Direct.DIRECT_RIGHT) {
                        break;
                    }
                }
                heroTank.moveRight();

                break;

            case KeyEvent.VK_SPACE:

                AtomicInteger bulletCount = heroTank.getBulletCount();
                if (bulletCount.get() < 1 && !heroTank.loadBullets()) {
                    break;
                }

                Bullet bullet = heroTank.shootBullet();
                bullet.setType(Constant.Army.ARMY_HERO);
                bullet.start();

                bulletCount.decrementAndGet();
                heroTank.getBullets().add(bullet);

                bullets.add(bullet);

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
