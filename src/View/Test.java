package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test {
    public static void main(String[] args) {
        MainView view = new MainView();
        view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        view.setResizable(false);
        view.setVisible(true);
        centerScreen(view);
        view.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(null,"确认退出?","确认", JOptionPane.OK_CANCEL_OPTION);
                if(res == JOptionPane.YES_OPTION){
                    System.exit(0);
                }else{
                    System.out.println("233");
                }
            }
        });
    }
    private static void centerScreen(Window win){       //固定窗口位置为屏幕中央
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - win.getWidth()) / 2;
        int y = (screenSize.height - win.getHeight()) / 2;
        win.setLocation(x, y);
    }
}
