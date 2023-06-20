package View;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        MainView view = new MainView();
        view.setResizable(false);
        view.setVisible(true);
        centerScreen(view);

    }

    private static void centerScreen(Window win){       //固定窗口位置为屏幕中央
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - win.getWidth()) / 2;
        int y = (screenSize.height - win.getHeight()) / 2;
        win.setLocation(x, y);
    }
}
