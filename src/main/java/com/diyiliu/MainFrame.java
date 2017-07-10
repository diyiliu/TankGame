package com.diyiliu;

import com.diyiliu.panel.DrawPanel;
import com.diyiliu.panel.PromptPanel;
import com.diyiliu.util.Constant;
import com.diyiliu.util.SoundMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Description: MainFrame
 * Author: DIYILIU
 * Update: 2017-07-04 14:01
 */
public class MainFrame extends JFrame implements ActionListener {

    private DrawPanel drawPanel;

    private PromptPanel promptPanel;

    public MainFrame() {
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        JMenuBar jmb = new JMenuBar();

        JMenu jm = new JMenu("游戏 (G)");
        jm.setMnemonic(KeyEvent.VK_G);

        JMenuItem jmi1 = new JMenuItem("开始 (S)");
        jmi1.setMnemonic(KeyEvent.VK_S);
        jmi1.setActionCommand(Constant.Command.START);
        jmi1.addActionListener(this);

        JMenuItem jmi2 = new JMenuItem("暂停 (P)");
        jmi2.setMnemonic(KeyEvent.VK_P);
        jmi2.setActionCommand(Constant.Command.PAUSE);
        jmi2.addActionListener(this);

        JMenuItem jmi3 = new JMenuItem("退出 (E)");
        jmi3.setMnemonic(KeyEvent.VK_E);
        jmi3.setActionCommand(Constant.Command.EXIT);
        jmi3.addActionListener(this);

        jmb.add(jm);
        jm.add(jmi1);
        jm.add(jmi2);
        jm.add(jmi3);

        promptPanel = new PromptPanel("stage: 1");
        this.add(promptPanel);
        new Thread(promptPanel).start();

        this.setJMenuBar(jmb);
        this.setSize(Constant.Config.FRAME_WIDTH, Constant.Config.FRAME_HEIGHT);

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(Constant.Command.START)) {

            promptPanel.setTime(-1);
            this.remove(promptPanel);

            if (drawPanel == null){
                drawPanel = new DrawPanel();
                this.add(drawPanel);
                this.addKeyListener(drawPanel);
                new Thread(drawPanel).start();

                this.setVisible(true);

                SoundMusic.buildPreludeMusic();
                SoundMusic.buildBackgroundMusic();
            }
        }
    }
}
