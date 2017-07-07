package com.diyiliu.panel.base;

import com.diyiliu.model.base.Tank;
import com.diyiliu.util.Constant;

import javax.swing.*;
import java.awt.*;

/**
 * Description: BasePanel
 * Author: DIYILIU
 * Update: 2017-07-07 17:07
 */
public class BasePanel extends JPanel {

    protected ImageIcon flagImg = new ImageIcon(ClassLoader.getSystemResource("flag-red.png"));

    public void drawTank(Tank tank, Graphics g) {

        g.setColor(tank.getColor());

        int x = tank.getX();
        int y = tank.getY();
        switch (tank.getDirect()) {

            case Constant.Direct.DIRECT_UP:

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
            case Constant.Direct.DIRECT_LEFT:

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
            case Constant.Direct.DIRECT_DOWN:

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
            case Constant.Direct.DIRECT_RIGHT:

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
}
