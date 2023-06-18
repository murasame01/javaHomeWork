package View;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class AddView extends JPanel {
    JLabel nameLabel = new JLabel("中文名:");
    JLabel scnameLabel = new JLabel("学名:");
    JLabel typeLabel = new JLabel("类型:");
    JLabel infoLabel= new JLabel("基本信息:");
    JLabel pathLabel = new JLabel("图片:");
JTextField nameField = new JTextField(20);
    JTextField scnameField = new JTextField(20);
    JTextField pathField = new JTextField(20);
    JTextArea infoField = new JTextArea(20, 20);
    JButton savaButton = new JButton("保存");
    JComboBox<String> typeField = new JComboBox<>();
JScrollPane scrollPane = new JScrollPane();
JButton openButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\open.png"));
    public AddView(){

        super();
        this.setLayout(null);
        int width = 980, height = 720;
        infoField.setLineWrap(true);
        scrollPane.setViewportView(infoField);
        openButton.setBackground(Color.WHITE);
        openButton.setFocusPainted(false);
        savaButton.setBackground(Color.WHITE);
        savaButton.setFocusPainted(false);



        nameLabel.setFont(new Font(null, Font.BOLD, 20));
        scnameLabel.setFont(new Font(null, Font.BOLD, 20));
        typeLabel.setFont(new Font(null, Font.BOLD, 20));
        pathLabel.setFont(new Font(null, Font.BOLD, 20));
        infoLabel.setFont(new Font(null, Font.BOLD, 20));
        nameField.setFont(new Font(null, Font.BOLD, 15));
        scnameField.setFont(new Font(null, Font.BOLD, 15));
        typeField.setFont(new Font(null, Font.BOLD, 15));
        pathField.setFont(new Font(null, Font.BOLD, 15));
        infoField.setFont(new Font(null, Font.BOLD, 15));
        savaButton.setFont(new Font(null, Font.BOLD, 15));

        typeField.addItem("哺乳动物");
        typeField.addItem("爬行动物");
        typeField.addItem("海龟");
        typeField.addItem("节肢动物");
        typeField.addItem("软体动物");
        typeField.addItem("腔体动物");
        typeField.addItem("无脊椎动物");
        typeField.addItem("海洋植物");

        this.add(nameLabel);
        this.add(scnameLabel);
        this.add(typeLabel);
        this.add(infoLabel);
        this.add(pathLabel);
        this.add(nameField);
        this.add(scnameField);
        this.add(typeField);
        this.add(pathField);
        this.add(scrollPane);
        this.add(openButton);
        this.add(savaButton);

        int deltaY = 60;
        int startX = 50, startY = 70;
        int labelWidth = 80, labelHeight = 30;

        nameLabel.setBounds(startX, startY, 80, 30);
        scnameLabel.setBounds(startX, startY + deltaY, labelWidth,labelHeight);
        typeLabel.setBounds(startX,startY + deltaY * 2, labelWidth,labelHeight);
        pathLabel.setBounds(startX,startY + deltaY * 3, labelWidth,labelHeight);
        infoLabel.setBounds(startX,startY + deltaY * 4,labelWidth + 20,labelHeight);
        nameField.setBounds(startX + labelWidth + 10,startY, 200, labelHeight);
        scnameField.setBounds(startX + labelWidth + 10,startY + deltaY, 200, labelHeight);
        typeField.setBounds(startX + labelWidth + 10,startY + deltaY * 2, 200, labelHeight);
        pathField.setBounds(startX + labelWidth + 10,startY + deltaY * 3, 200, labelHeight);
        scrollPane.setBounds(startX + labelWidth + 10,startY + deltaY * 4, 200 * 3, labelHeight * 6);
        openButton.setBounds(startX + labelWidth + 10 + 200, startY + deltaY * 3, 40, labelHeight);
        savaButton.setBounds(startX + labelWidth + 10 + 220, startY + deltaY * 7 + 10, 80, labelHeight);

        openButton.addActionListener((e)->{
            onOpenDir();
        });


    }
    private void onOpenDir(){
        JFileChooser chooser = new JFileChooser(); //默认打开jpg文件
        FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int res = chooser.showOpenDialog(this);

        if(res == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            String path;
            try {
                path = file.getCanonicalPath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(checkPhoto(path))
            pathField.setText(path);
            else onOpenDir();
        }
    }
    private void print(){
        String text = infoField.getText();
        System.out.println(text);
    }
    private boolean checkPhoto(String path){
        if(path.toLowerCase().endsWith(".jpg")) return true;
        if(path.toLowerCase().endsWith(".png")) return true;
        if(path.toLowerCase().endsWith(".jpeg")) return true;
        JOptionPane.showMessageDialog(null, "只能选择图片文件!","error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

}
