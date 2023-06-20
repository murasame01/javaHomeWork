package View;

import DAO.dao.MarineOrganismDAO;
import DAO.domain.MarineOrganism;
import View.utils.CopyFile;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static View.utils.CopyFile.copyFile;
import static View.utils.CopyFile.getFileNameExtension;


public class AddView extends JPanel {
    private int delta = -1;
    boolean isModify = false;
    MarineOrganism preData;
    private MarineOrganismDAO MODao = new MarineOrganismDAO();
    JButton flushButton = new JButton();
    JButton backButton = new JButton("返回主界面");
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
//        this.setOpaque(true); //设置背景透明
//        this.setBackground(new Color(0, 0, 0, 0));
        int width = 980, height = 720;
        infoField.setLineWrap(true);
        scrollPane.setViewportView(infoField);
        openButton.setBackground(Color.WHITE);
        openButton.setFocusPainted(false);
        savaButton.setBackground(Color.WHITE);
        savaButton.setFocusPainted(false);
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);



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
        backButton.setFont(new Font(null, Font.BOLD, 15));

        typeField.addItem("哺乳动物");
        typeField.addItem("爬行动物");
        typeField.addItem("海鱼");
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
        this.add(backButton);
        this.add(flushButton);

        int deltaY = 60;
        int startX = 200, startY = 100;
        int labelWidth = 80, labelHeight = 30;

        nameLabel.setBounds(startX, startY, 80, 30);
        scnameLabel.setBounds(startX, startY + deltaY, labelWidth,labelHeight);
        typeLabel.setBounds(startX,startY + deltaY * 2, labelWidth,labelHeight);
        pathLabel.setBounds(startX,startY + deltaY * 3, labelWidth,labelHeight);
        infoLabel.setBounds(startX,startY + deltaY * 4,labelWidth + 20,labelHeight);
        nameField.setBounds(startX + labelWidth + 10,startY, 200, labelHeight);
        scnameField.setBounds(startX + labelWidth + 10,startY + deltaY, 200, labelHeight);
        typeField.setBounds(startX + labelWidth + 10,startY + deltaY * 2, 200, labelHeight);
        flushButton.setBounds(startX + labelWidth + 10,startY + deltaY * 2, 200, labelHeight);
        pathField.setBounds(startX + labelWidth + 10,startY + deltaY * 3, 200, labelHeight);
        scrollPane.setBounds(startX + labelWidth + 10,startY + deltaY * 4, 200 * 3, labelHeight * 6);
        openButton.setBounds(startX + labelWidth + 10 + 200, startY + deltaY * 3, 40, labelHeight);
        savaButton.setBounds(startX + labelWidth + 10 + 220, startY + deltaY * 7 + 10, 80, labelHeight);
        backButton.setBounds(10, 20, 150, 50);

        openButton.addActionListener((e)->{
            onOpenDir();
        });
        savaButton.addActionListener((e)->{
            int n = JOptionPane.showConfirmDialog(null, "是否提交?", "提示", JOptionPane.OK_CANCEL_OPTION);
            if(n == JOptionPane.YES_OPTION){
                onSave();
                flush();
            }
        });
    }
    private void onOpenDir(){
        JFileChooser chooser = new JFileChooser("F:\\src_images"); //默认打开jpg文件
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
    private boolean isExist (String name, String scname){
        List<MarineOrganism> animals = MODao.multiQuery("select * from animal where name = ? or scientificName = ?", MarineOrganism.class, name, scname);
        return animals.size() == 0;
    }

    private boolean onSave(){
        String name = nameField.getText();
        String scName = scnameField.getText();
        String type = typeField.getItemAt(typeField.getSelectedIndex());
        String info = infoField.getText();
        String path = pathField.getText();
        if (name.length() == 0 || scName.length() == 0 || info.length() == 0 || path.length() == 0) {
            JOptionPane.showMessageDialog(null, "所填信息不能为空", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "文件路径有误!", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!checkPhoto(file.getPath())) return false;
        if(isModify){
            System.out.println("modify");
            File preFile = new File(preData.getIconPath());
            String savePath;
            String buffPath = copyFile(preFile, "buff_file");
            File buff_file = new File(buffPath);
            MODao.update("delete from animal where name = ?", preData.getName());
            if(preData.getIconPath().equals(path) && preData.getScientificName().equals(scName)){
                savePath = path;
            }else if(!preData.getIconPath().equals(path) && preData.getScientificName().equals(scName)){
                preFile.delete();
                savePath = copyFile(file,scName);
           }else if(preData.getIconPath().equals(path) && !preData.getScientificName().equals(scName)){
                savePath = "src\\View\\static\\resImages\\";
                savePath += scName + ".";
                savePath += getFileNameExtension(preFile.getPath());
                File newFile = new File(savePath);
                preFile.renameTo(newFile);
            } else{
                preFile.delete();
                savePath = copyFile(file,scName);
            }
            int update = MODao.update("insert into animal values(?, ?, ?, ?, ?)", name, scName, type, info, savePath);
            this.isModify = false;
            if (update > 0){
                JOptionPane.showMessageDialog(null, "修改成功", "succeed", JOptionPane.PLAIN_MESSAGE);
                buff_file.delete(); //删除备份图片
                clear();
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "系统故障, 修改失败!", "failed", JOptionPane.ERROR_MESSAGE);
                //恢复数据
                File file1 = new File(preData.getIconPath());
                buff_file.renameTo(file1);
                MODao.update("insert into animal values(?, ?, ?, ?, ?)",preData.getName(),preData.getScientificName(),preData.getType(),preData.getIconPath(), preData.getIconPath());
                return false;
            }
        }else{
            if (!isExist(name, scName)) {
                JOptionPane.showMessageDialog(null, "该生物信息以存在", "error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String savePath = copyFile(file, scName);
            int update = MODao.update("insert into animal values(?, ?, ?, ?, ?)", name, scName, type, info, savePath);
            if (update > 0){
                JOptionPane.showMessageDialog(null, "保存成功", "succeed", JOptionPane.PLAIN_MESSAGE);
                clear();
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "系统故障, 保存失败!", "failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    public void clear(){
        nameField.setText("");
        scnameField.setText("");
        typeField.setSelectedIndex(0);
        infoField.setText("");
        pathField.setText("");
    }
    private void flush(){       //强制刷新界面
        this.setSize(this.getWidth() + delta, this.getHeight());
        delta *= -1;
    }
}
