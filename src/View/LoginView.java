package View;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    public JTextField userField = new JTextField(10);
    private final JTextField pwdField = new JPasswordField(10);
    public JButton registerButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\register.png"));
    public JButton loginButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\login.png"));
    public LoginView(){
        super();
        this.setOpaque(true); //设置背景透明
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        int labelWidth = 100;
        int labelHeight = 50;
        JLabel label_user = new JLabel("用户名:");
        JLabel label_pwd = new JLabel("密码:");
        label_pwd.setForeground(Color.RED);
        label_user.setForeground(Color.RED);

        label_user.setFont(new Font("宋体", Font.BOLD, 25));
        label_pwd.setFont(new Font("宋体", Font.BOLD, 25));
        userField.setFont(new Font(null, Font.PLAIN, 15));
        pwdField.setFont(new Font(null, Font.PLAIN, 20));

        userField.setBounds(labelWidth,10, 300, 30);
        pwdField.setBounds(labelWidth,60, 300, 30);
        label_user.setBounds(0 ,0, labelWidth,labelHeight);
        label_pwd.setBounds(0, labelHeight, labelWidth, labelHeight);
        loginButton.setBounds(140, 110, 80, 30);
        registerButton.setBounds(270, 110, 80, 30);

        this.add(label_user);
        this.add(label_pwd);
        this.add(userField);
        this.add(pwdField);
        this.add(loginButton);
        this.add(registerButton);
    }
    public String getName(){
        return userField.getText();
    }
    public String getPwd(){
        return pwdField.getText();
    }
    public void clear(){        //清空账户和密码
        userField.setText("");
        pwdField.setText("");
    }
}
