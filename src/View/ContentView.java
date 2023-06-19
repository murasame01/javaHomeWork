package View;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ContentView extends JPanel {
    JButton button1 = null;
    JButton button2 = null;
    JButton button3 = null;
    JButton button4 = null;
    JButton button5 = null;
    JButton button6 = null;
    JButton button7 =  null;
    JButton button8 = null;
    JButton userButton = null;
    JButton addButton = null;
    JButton quitButton = null;
    JButton searchButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\search.png"));
    JLabel userField = new JLabel();
    JTextField searchField = new JTextField(5);

    public ContentView(){
        super();
        button1 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\whale.png"));
        button2 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\turtle.png"));
        button3 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\Fish.png"));
        button4 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\lobster.png"));
        button5 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\starFish.png"));
        button6 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\jellyFish.png"));
        button7 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\trepang.png"));
        button8 = new JButton(new ImageIcon("src\\View\\static\\iconImages\\seaGrass.png"));
        userButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\user.png"));
        addButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\add.png"));
        quitButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\quit.png"));


        button1.setBackground(Color.cyan);
        button2.setBackground(Color.cyan);
        button3.setBackground(Color.cyan);
        button4.setBackground(Color.cyan);
        button5.setBackground(Color.cyan);
        button6.setBackground(Color.cyan);
        button7.setBackground(Color.cyan);
        button8.setBackground(Color.cyan);
        userButton.setBackground(Color.WHITE);
        addButton.setBackground(Color.WHITE);
        quitButton.setBackground(Color.WHITE);
        searchButton.setBackground(Color.WHITE);

        button1.setText("哺乳动物");
        button2.setText("爬行动物");
        button3.setText("海鱼");
        button4.setText("节肢动物");
        button5.setText("软体动物");
        button6.setText("腔体动物");
        button7.setText("无脊椎动物");
        button8.setText("海洋植物");


        button1.setFocusPainted(false);
        button2.setFocusPainted(false);
        button3.setFocusPainted(false);
        button4.setFocusPainted(false);
        button5.setFocusPainted(false);
        button6.setFocusPainted(false);
        button7.setFocusPainted(false);
        button8.setFocusPainted(false);
        userButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);
        addButton.setFocusPainted(false);
        quitButton.setFocusPainted(false);


        button1.setFont(new Font(null, Font.BOLD, 20));
        button2.setFont(new Font(null, Font.BOLD, 20));
        button3.setFont(new Font(null, Font.BOLD, 20));
        button4.setFont(new Font(null, Font.BOLD, 20));
        button5.setFont(new Font(null, Font.BOLD, 20));
        button6.setFont(new Font(null, Font.BOLD, 20));
        button7.setFont(new Font(null, Font.BOLD, 20));
        button8.setFont(new Font(null, Font.BOLD, 20));
        searchField.setFont(new Font(null, Font.BOLD, 15));
        userField.setFont(new Font(null, Font.BOLD, 15));


        this.setOpaque(true); //设置背景透明
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        int width = 980;
        int height = 720;
        int labelHeight = 100;
        int labelWidth = 300;

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(userButton);
        this.add(addButton);
        this.add(quitButton);
        this.add(searchField);
        this.add(searchButton);
        this.add(userField);

        int buttonWidth = 180;
        int buttonHeight = 70;
        int deltaX =  (width / 2 - 200 - buttonWidth) * 2 + 150, deltaY = 100;
        int startX = 200, startY = 140;
        int searchWidth = 300, searchHeight = 30;

        button1.setBounds(startX, startY,buttonWidth,buttonHeight);
        button2.setBounds(startX + deltaX,startY,buttonWidth,buttonHeight);
        button3.setBounds(startX, startY+ deltaY,buttonWidth,buttonHeight);
        button4.setBounds(startX + deltaX, startY+ deltaY,buttonWidth,buttonHeight);
        button5.setBounds(startX, startY+ deltaY * 2,buttonWidth,buttonHeight);
        button6.setBounds(startX + deltaX, startY+ deltaY * 2, buttonWidth,buttonHeight);
        button7.setBounds(startX, startY+ deltaY * 3,buttonWidth,buttonHeight);
        button8.setBounds(startX + deltaX, startY+ deltaY * 3,buttonWidth,buttonHeight);
        userButton.setBounds(40, 20, 30, 30);
        searchButton.setBounds((width - searchWidth) / 2 + searchWidth, 20, 50, 30);
        addButton.setBounds(width - 100, 20, 30, 30);
        searchField.setBounds((width - searchWidth) / 2, 20, 300, 30);
        userField.setBounds(80, 20, 100, 30);
        userField.setBounds(80, 20, 100, 30);
        quitButton.setBounds(170, 20, 40, 30);
    }

}