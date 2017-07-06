package com.diyiliu;

import com.diyiliu.panel.DrawPanel;
import com.diyiliu.util.Constant;

import javax.swing.*;
import java.awt.*;

/**
 * Description: MainFrame
 * Author: DIYILIU
 * Update: 2017-07-04 14:01
 */
public class MainFrame extends JFrame {

    private DrawPanel drawPanel;

    public MainFrame(){

        drawPanel = new DrawPanel();
        this.add(drawPanel);
        this.addKeyListener(drawPanel);

        this.setSize(Constant.Draw.PANEL_WIDTH, Constant.Draw.PANEL_HEIGHT);

        //设置窗口居中
        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screamSize = kit.getScreenSize();
        this.setBounds((screamSize.width - WIDTH) / 2, (screamSize.height - HEIGHT) / 2, WIDTH, HEIGHT);

        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("tank1.png")).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        Thread drawThread = new Thread(drawPanel);
        drawThread.start();
    }

    public static void main(String[] args) {

        new MainFrame();
    }
}
