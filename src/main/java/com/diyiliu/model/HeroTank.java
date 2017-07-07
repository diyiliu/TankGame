package com.diyiliu.model;

import com.diyiliu.model.base.Tank;
import com.diyiliu.util.Constant;

import java.awt.*;

/**
 * Description: HeroTank
 * Author: DIYILIU
 * Update: 2017-07-04 14:05
 */
public class HeroTank extends Tank {

    public HeroTank() {

        color = Color.YELLOW;
        type = Constant.Army.ARMY_HERO;
        x = Constant.Draw.PANEL_WIDTH / 2 - 15;
        y = Constant.Draw.PANEL_HEIGHT - 80;
        direct = Constant.Derict.DIRECT_UP;
    }

    @Override
    public Color getColor() {

        if (getLives().get() == 2) {

            setColor(Color.ORANGE);
        } else if (getLives().get() == 1) {
            if (color.equals(Color.ORANGE)) {

                setColor(Color.YELLOW);
            } else {

                setColor(Color.ORANGE);
            }
        }

        return color;
    }
}
