package View;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class RegisterView extends JPanel {
    private JTextField userField = new JTextField(10);
    private JTextField pwdField = new JPasswordField(10);
    private JTextField emailField = new JTextField(10);
    JButton registerButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\register.png"));
    JButton backButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\back.png"));


    public RegisterView(){
        super();
        backButton.setBackground(Color.WHITE);
        JLabel label_user = new JLabel("用户名:");
        JLabel label_pwd = new JLabel("密码:");
        JLabel label_email = new JLabel("邮箱:");
        int labelWidth = 100;
        int labelHeight = 50;

        this.setOpaque(true); //设置背景透明
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);

        label_pwd.setForeground(Color.RED);
        label_user.setForeground(Color.RED);
        label_email.setForeground(Color.RED);

        label_user.setBounds(0 ,0, labelWidth,labelHeight);
        label_pwd.setBounds(0, labelHeight, labelWidth, labelHeight);
        label_email.setBounds(0, labelHeight * 2, labelWidth, labelHeight);
        userField.setBounds(labelWidth,10, 300, 30);
        pwdField.setBounds(labelWidth,60, 300, 30);
        emailField.setBounds(labelWidth, 110, 300, 30);
        backButton.setBounds(140, 150,80, 30);
        registerButton.setBounds(270, 150, 80, 30);

        label_user.setFont(new Font("宋体", Font.BOLD, 25));
        label_pwd.setFont(new Font("宋体", Font.BOLD, 25));
        label_email.setFont(new Font("宋体", Font.BOLD, 25));
        userField.setFont(new Font(null, Font.PLAIN, 15));
        pwdField.setFont(new Font(null, Font.PLAIN, 20));
        emailField.setFont(new Font(null, Font.PLAIN, 15));

        this.add(label_user);
        this.add(label_pwd);
        this.add(label_email);
        this.add(userField);
        this.add(pwdField);
        this.add(emailField);
        this.add(backButton);
        this.add(registerButton);
    }
    public String getName(){
        return userField.getText();
    }
    public String getPwd(){
        return pwdField.getText();
    }
    public String getEmail(){
        return emailField.getText();
    }

    public void clear(){
        userField.setText("");
        pwdField.setText("");
        emailField.setText("");
    }
}
