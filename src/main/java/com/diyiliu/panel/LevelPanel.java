package com.diyiliu.panel;

import com.diyiliu.util.Constant;

import javax.swing.*;
import java.awt.*;

/**
 * Description: LevelPanel
 * Author: DIYILIU
 * Update: 2017-07-07 15:49
 */
public class LevelPanel extends JPanel implements Runnable {

    private int level;

    private int time = 1;

    public LevelPanel(int level){

        this.level = level;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, Constant.Config.PANEL_WIDTH, Constant.Config.PANEL_HEIGHT);

        Font font = new Font("黑体", Font.BOLD, 20);
        g.setFont(font);

        if (time % 2 == 0){
            g.setColor(Color.WHITE);
            g.drawString("stage: " + level, Constant.Config.PANEL_WIDTH / 2  - 50 , Constant.Config.PANEL_HEIGHT / 2 - 50);
        }
    }

    @Override
    public void run() {

        while (time > 0){
            repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = ++time == 100000? 2: time;
        }
    }

    public void setTime(int time) {
        this.time = time;
    }
}
