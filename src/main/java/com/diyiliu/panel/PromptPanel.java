package com.diyiliu.panel;

import com.diyiliu.panel.base.BasePanel;
import com.diyiliu.util.Constant;

import java.awt.*;

/**
 * Description: PromptPanel
 * Author: DIYILIU
 * Update: 2017-07-07 15:49
 */
public class PromptPanel extends BasePanel implements Runnable {

    private String content;

    private int time = 1;

    public PromptPanel(String content){

        this.content = content;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, Constant.Config.PANEL_WIDTH, Constant.Config.PANEL_HEIGHT);
        g.setColor(Color.GRAY);
        g.fillRect(Constant.Config.PANEL_WIDTH, 0,
                Constant.Config.FRAME_WIDTH - Constant.Config.PANEL_WIDTH, Constant.Config.FRAME_HEIGHT);

        drawTopList(g);

        Font font = new Font("黑体", Font.BOLD, 20);
        g.setFont(font);

        if (time % 2 == 0){
            g.setColor(Color.WHITE);
            g.drawString(content, Constant.Config.PANEL_WIDTH / 2  - 50 , Constant.Config.PANEL_HEIGHT / 2 - 50);
        }
    }

    public void drawTopList(Graphics g){

        g.drawImage(flagImg.getImage(), Constant.Config.PANEL_WIDTH + 20, 10,
                20, 20, this);

        Font font = new Font("宋体", Font.BOLD, 15);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("积分榜", Constant.Config.PANEL_WIDTH + 50, 30);

        font = new Font("微软雅黑", Font.PLAIN, 12);
        g.setFont(font);
        g.drawString("top1: 900", Constant.Config.PANEL_WIDTH + 20, 55);
        g.drawString("top2: 500", Constant.Config.PANEL_WIDTH + 20, 75);
        g.drawString("top3: 200", Constant.Config.PANEL_WIDTH + 20, 95);
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
