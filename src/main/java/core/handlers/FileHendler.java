package core.handlers;

import core.property.PropertyReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;

/**
 * @author Anton_Savostytskyi on 16.06.2015.
 *         Class FileHendler responsible for input class path to not web window.
 *         This class use java Robot for imulation key pressing. File path getting
 *         using System.getProperty("user.dir"). Method languageSwaper will change
 *         input language to english
 */

public class FileHendler {

    public static void filePathHandling(String letter) {
        languageSwapper();
        try {
            String path = System.getProperty("user.dir");
            Robot robot = new Robot();
            robot.delay(2000);
            String message = (path + PropertyReader.getProperty(letter + ".file")).toUpperCase();
            for (int i = 0; i < message.length(); i++) {
                char c = message.charAt(i);
                if (c == ':') {
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD5);
                    robot.keyRelease(KeyEvent.VK_NUMPAD5);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD8);
                    robot.keyRelease(KeyEvent.VK_NUMPAD8);
                    robot.delay(20);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.delay(20);
                } else if (c == '/' || c == '\\') {
                    robot.keyPress(KeyEvent.VK_BACK_SLASH);
                } else if (c == '.') {
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_ALT);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD4);
                    robot.keyRelease(KeyEvent.VK_NUMPAD4);
                    robot.delay(20);
                    robot.keyPress(KeyEvent.VK_NUMPAD6);
                    robot.keyRelease(KeyEvent.VK_NUMPAD6);
                    robot.delay(20);
                    robot.keyRelease(KeyEvent.VK_ALT);
                    robot.delay(20);
                } else
                    robot.keyPress(c);
            }
            actionExecutor();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void actionExecutor() {
        try {
            Robot okRobot = new Robot();
            okRobot.keyPress(KeyEvent.VK_ENTER);
            okRobot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void languageSwapper() {
        Locale defaultLocale = Locale.getDefault();
        if (!defaultLocale.toString().equals("en_EN")) {

            Robot shiftkey = null;
            Robot altkey = null;
            Robot ctrlkey = null;
            try {
                shiftkey = new Robot();
                altkey = new Robot();
                ctrlkey = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            altkey.keyPress(KeyEvent.VK_ALT);
            shiftkey.keyPress(KeyEvent.VK_SHIFT);
            altkey.keyRelease(KeyEvent.VK_ALT);
            shiftkey.keyRelease(KeyEvent.VK_SHIFT);

            ctrlkey.keyPress(KeyEvent.VK_CONTROL);
            shiftkey.keyPress(KeyEvent.VK_SHIFT);
            ctrlkey.keyRelease(KeyEvent.VK_CONTROL);
            shiftkey.keyRelease(KeyEvent.VK_SHIFT);
        }
    }
}
