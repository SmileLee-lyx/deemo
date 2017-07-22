package org.dilant.deemo.util;

import static java.lang.Thread.sleep;

/**
 * @author Dilant
 * @date 2017/2/4
 */

public class Sleeper {
    public void delay(long min, long max) {
        try {
            sleep(System.currentTimeMillis() % (max - min + 1) + min);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delay() {
        delay(1, 2);
    }
}
