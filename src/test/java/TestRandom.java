import org.junit.Test;

import java.util.Random;

/**
 * Description: TestRandom
 * Author: DIYILIU
 * Update: 2017-07-05 10:48
 */
public class TestRandom {


    @Test
    public void test1() {

        Random random = new Random();

        int i = 0;
        for (; ; ) {
            System.out.println(random.nextInt(4));
            i++;
            if (i > 10) {
                break;
            }
        }
    }


    @Test
    public void test2() {

        int i = 0;
        for (; ; ) {
            System.out.println((int) (Math.random() * 4));
            i++;
            if (i > 10) {
                break;
            }
        }
    }
}
