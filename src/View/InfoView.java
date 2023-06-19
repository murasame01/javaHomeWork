package View;

import DAO.dao.MarineOrganismDAO;
import DAO.domain.MarineOrganism;
import View.utils.PictureView;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InfoView extends JPanel {
    private int curIndex = 0;
    private MarineOrganismDAO MODao = new MarineOrganismDAO();
    ArrayList<MarineOrganism> animals = new ArrayList<>();
    JButton backButton = new JButton("返回主界面");
    JLabel nameLabel = new JLabel("中文名:");
    JLabel scnameLabel = new JLabel("学名:");
    JLabel typeLabel = new JLabel("类型:");
    JLabel infoLabel= new JLabel("基本信息:");
    JLabel pathLabel = new JLabel("图片:");
    JLabel nameField = new JLabel("ss");
    JLabel scnameField = new JLabel("dfs");
    JLabel pathField = new JLabel("fdsa");
    JTextArea infoField = new JTextArea(20, 20);
    JLabel typeField = new JLabel("fas");
    JScrollPane scrollPane = new JScrollPane();
    JButton preButton =  new JButton(new ImageIcon("src\\View\\static\\iconImages\\pre.png"));
    JButton nextButton = new JButton(new ImageIcon("src\\View\\static\\iconImages\\next.png"));
    PictureView image = new PictureView("src\\View\\static\\iconImages\\bk01.jpeg");

//    JLabel image = new JLabel(new ImageIcon("src\\View\\static\\iconImages\\bk01.jpeg"));
    public InfoView(){
        super();
        this.setLayout(null);
        int width = 980, height = 720;
        infoField.setLineWrap(true);
        scrollPane.setViewportView(infoField);
        backButton.setBackground(Color.WHITE);
        preButton.setBackground(Color.WHITE);
        nextButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        preButton.setFocusPainted(false);
        nextButton.setFocusPainted(false);
//        MatteBorder border = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.CYAN);
//        image.setBorder(border);



        nameLabel.setFont(new Font(null, Font.BOLD, 20));
        scnameLabel.setFont(new Font(null, Font.BOLD, 20));
        typeLabel.setFont(new Font(null, Font.BOLD, 20));
        pathLabel.setFont(new Font(null, Font.BOLD, 20));
        infoLabel.setFont(new Font(null, Font.BOLD, 20));
        nameField.setFont(new Font(null, Font.BOLD, 15));
        scnameField.setFont(new Font(null, Font.BOLD, 15));
        typeField.setFont(new Font(null, Font.BOLD, 15));
        infoField.setFont(new Font(null, Font.BOLD, 15));
        backButton.setFont(new Font(null, Font.BOLD, 15));


        this.add(nameLabel);
        this.add(scnameLabel);
        this.add(typeLabel);
        this.add(infoLabel);
        this.add(nameField);
        this.add(scnameField);
        this.add(typeField);
        this.add(pathField);
        this.add(scrollPane);
        this.add(backButton);
        this.add(preButton);
        this.add(nextButton);
        this.add(image);


        int deltaY = 60;
        int startX = 100, startY = 100;
        int labelWidth = 80, labelHeight = 30;

        nameLabel.setBounds(startX, startY, 80, 30);
        scnameLabel.setBounds(startX, startY + deltaY, labelWidth,labelHeight);
        typeLabel.setBounds(startX,startY + deltaY * 2, labelWidth,labelHeight);
        pathLabel.setBounds(startX,startY + deltaY * 3, labelWidth,labelHeight);
        infoLabel.setBounds(startX,startY + deltaY * 4,labelWidth + 20,labelHeight);
        nameField.setBounds(startX + labelWidth + 10,startY, 200, labelHeight);
        scnameField.setBounds(startX + labelWidth + 10,startY + deltaY, 200, labelHeight);
        typeField.setBounds(startX + labelWidth + 10,startY + deltaY * 2, 200, labelHeight);
        scrollPane.setBounds(startX + labelWidth + 10,startY + deltaY * 4, 200 * 3, labelHeight * 6);
        backButton.setBounds(10, 20, 150, 50);
        preButton.setBounds(startX + labelWidth + 180,startY + deltaY * 4 + 200,80, 50);
        nextButton.setBounds(startX + labelWidth + 260,startY + deltaY * 4 + 200,80, 50);
        image.setBounds(400, 50, 550, 270);

        animals = (ArrayList<MarineOrganism>) MODao.multiQuery("select * from animal", MarineOrganism.class);
        loadRes(animals);
        nextButton.addActionListener((e)->{
            toNext();
        });
        preButton.addActionListener((e)->{
            toPre();
        });
    }

    private void checkPre(){
        if(curIndex == 0) preButton.setEnabled(false);
        else preButton.setEnabled(true);
    }
    private void checkNext(){
        if(curIndex == animals.size() - 1) nextButton.setEnabled(false);
        else nextButton.setEnabled(true);
    }
    private void loadRes(ArrayList<MarineOrganism> res){
        checkPre();
        checkNext();
        this.animals = res;
        loadInfo();
    }
    private void loadInfo(){
        MarineOrganism animal = animals.get(curIndex);
        System.out.println(animal);
        nameField.setText(animal.getName());
        scnameField.setText(animal.getScientificName());
        typeField.setText(animal.getType());
        infoField.setText(animal.getInfomation());
        image.setImage(animal.getIconPath());
        infoField.setEnabled(false);
    }
    private void toNext(){
        curIndex++;
        loadInfo();
        checkNext();
        checkPre();
    }
    private void toPre(){
        curIndex--;
        loadInfo();
        checkNext();
        checkPre();
    }
}
