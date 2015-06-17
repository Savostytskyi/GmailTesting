package core.handlers;

import java.awt.*;
import java.awt.event.KeyEvent;
import static core.property.PropertyReader.getProperty;

/**
 * The type File path handler.
 * @author Anton_Savostytskyi on 16.06.2015.
 *         Class FilePathHandler responsible for inputing class path to not web window.
 *         This class use java Robot for imulation key pressing. File path getting
 *         using System.getProperty("user.dir"). All symbols typing using it's code.
 *         It help type path to file independently of locale.
 */

public class FilePathHandler {

    public static int keyInput[] =
            {
                    KeyEvent.VK_NUMPAD0, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2,
                    KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5,
                    KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8,
                    KeyEvent.VK_NUMPAD9,
            };

    public static void filePathHandling(String letter) {
        try {
            String path = System.getProperty("user.dir");
            Robot robot = new Robot();
            robot.delay(2000);
            String message = (path +"\\"+ getProperty(letter + ".file")).toLowerCase();
            for (int i = 0; i < message.length(); i++) {
                int c = (int)(message.charAt(i));
                robot.delay(20);
                robot.keyPress(KeyEvent.VK_ALT);
                robot.delay(20);
                String symbolsCode = Integer.toString(c);
                for (int j=0; j<symbolsCode.length(); j++) {
                    int number = Character.getNumericValue(symbolsCode.charAt(j));
                    robot.keyPress(keyInput[number]);
                    robot.keyRelease(keyInput[number]);
                    robot.delay(20);
                }
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.delay(20);
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
}
