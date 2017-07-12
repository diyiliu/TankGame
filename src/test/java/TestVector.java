import com.diyiliu.model.base.Tank;
import org.junit.Test;

import java.awt.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Description: TestVector
 * Author: DIYILIU
 * Update: 2017-07-12 09:12
 */
public class TestVector {


    /**
     * 丢失遍历数据
     */
    @Test
    public void test1() {

        Vector v = new Vector();

        for (int i = 1; i < 11; i++) {

            Tank tank = new Tank();
            tank.setType(i);
            if (i < 3){
                tank.setColor(Color.ORANGE);
            }else {
                tank.setColor(Color.CYAN);
            }

            v.add(tank);
        }


        for (int i = 0; i < v.size(); i++) {

            Tank t = (Tank) v.get(i);

//            System.out.println("i=" + i);
            System.out.println(t.getType());

            if (t.getColor().equals(Color.CYAN)){
                v.remove(t);
            }

        }

        System.out.println("剩余长度：" + v.size());
        for (int i = 0; i < v.size(); i++) {

            Tank t = (Tank) v.get(i);
            System.out.println(t.getType() + ":" + t.getColor().equals(Color.ORANGE));
        }
    }

    @Test
    public void test2() {

        Vector v = new Vector();

        for (int i = 1; i < 11; i++) {

            Tank tank = new Tank();
            tank.setType(i);
            if (i < 3){
                tank.setColor(Color.ORANGE);
            }else {
                tank.setColor(Color.CYAN);
            }

            v.add(tank);
        }

        for (Iterator iterator = v.iterator(); iterator.hasNext();) {

            Tank t = (Tank) iterator.next();

            System.out.println(t.getType());

            if (t.getColor().equals(Color.CYAN)){
                iterator.remove();
            }

        }

        System.out.println("剩余长度：" + v.size());
        for (int i = 0; i < v.size(); i++) {

            Tank t = (Tank) v.get(i);
            System.out.println(t.getType() + ":" + t.getColor().equals(Color.ORANGE));
        }
    }

    @Test
    public void test3() {

        Vector v = new Vector();

        for (int i = 1; i < 11; i++) {

            Tank tank = new Tank();
            tank.setType(i);
            if (i < 3){
                tank.setColor(Color.ORANGE);
            }else {
                tank.setColor(Color.CYAN);
            }

            v.add(tank);
        }


        for (Enumeration enumeration = v.elements(); enumeration.hasMoreElements();) {

            Tank t = (Tank) enumeration.nextElement();

            System.out.println(t.getType());

            if (t.getColor().equals(Color.CYAN)){
                v.remove(t);
            }

        }

        System.out.println("剩余长度：" + v.size());
        for (int i = 0; i < v.size(); i++) {

            Tank t = (Tank) v.get(i);
            System.out.println(t.getType() + ":" + t.getColor().equals(Color.ORANGE));
        }
    }
}
