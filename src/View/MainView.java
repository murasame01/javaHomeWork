package View;

import DAO.dao.MarineOrganismDAO;
import DAO.dao.UserDAO;
import DAO.domain.MarineOrganism;
import DAO.domain.User;
import View.utils.PictureView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainView extends JFrame {

    private final UserDAO userDAO = new UserDAO();
    private final MarineOrganismDAO MODao = new MarineOrganismDAO();
    private final int width = 980;
    private final int height = 720 ;
    private final boolean pwdVisible = false;
    private final PictureView pwdView = null;

    LoginView loginView = null;  //登录界面
    RegisterView registerView  = null;  //注册界面
    ContentView contentView = null; //内容界面
    AddView addView = null; //添加界面
    ModifyView modifyView = null; //修改界面
    InfoView infoView = null; //信息界面
    PictureView pwdOn = new PictureView();
    PictureView pwdOff = new PictureView();
    PictureView root = new PictureView();
    HashMap<String, Integer> map = new HashMap<>();

    public MainView(){
        super();
        this.setContentPane(root);
        this.setLayout(null);
        this.setSize(width, height);
        root.isCover = true;
        pwdOff.setBackground(Color.WHITE);
        pwdOff.setImage("src\\View\\static\\iconImages\\close_eye.png");
        pwdOn.setBackground(Color.WHITE);
        pwdOn.setImage("src\\View\\static\\iconImages\\open_eyes.png");
        render();
        init();
    }
    private void render(){
        int loginViewWidth = 500;
        int loginViewHeight = 300;


        loginView = new LoginView();
        registerView = new RegisterView();
        contentView = new ContentView();
        addView = new AddView();
        infoView = new InfoView();
        modifyView = new ModifyView();


        loginView.setVisible(false);
        registerView.setVisible(false);
        contentView.setVisible(false);
        addView.setVisible(false);
        infoView.setVisible(false);
        modifyView.setVisible(false);

        loginView.setBounds((width - loginViewWidth) / 2, (height - loginViewHeight) / 2,loginViewWidth, loginViewHeight);
        registerView.setBounds((width - loginViewWidth) / 2, (height - loginViewHeight) / 2,loginViewWidth, loginViewHeight);
        contentView.setBounds(0, 0, this.width, this.height);
        addView.setBounds(0, 0, this.width, this.height);
        modifyView.setBounds(0, 0, this.width, this.height);
        infoView.setBounds(0, 0, this.width, this.height);

        root.add(loginView);
        root.add(registerView);
        root.add(contentView);
        root.add(addView);
        root.add(modifyView);
        root.add(infoView);
    }
    private void init(){
        registerToLogin();
        map.put("哺乳动物", 0);
        map.put("爬行动物", 1);
        map.put("海鱼", 2);
        map.put("节肢动物", 3);
        map.put("软体动物", 4);
        map.put("腔体动物", 5);
        map.put("无脊椎动物", 6);
        map.put("海洋植物", 7);

        addView.backButton.setText("返回主界面");

        loginView.loginButton.addActionListener((e)-> onLogin());
        loginView.registerButton.addActionListener((e)-> loginToRegister());

        registerView.registerButton.addActionListener((e)-> onRegister());
        registerView.backButton.addActionListener((e)-> registerToLogin());

        addView.backButton.addActionListener((e)-> addToContent());
        addView.saveButton.addActionListener((e)->{
            int n = JOptionPane.showConfirmDialog(null, "是否提交?", "提示", JOptionPane.OK_CANCEL_OPTION);
            if(n == JOptionPane.YES_OPTION){
                addView.onSave_add();
                addView.flush();
            }
        });


        contentView.addButton.addActionListener((e)-> contentToAdd());
        contentView.quitButton.addActionListener((e)-> contentToLogin());
        contentView.button1.addActionListener((e)-> selectInfo("哺乳动物"));
        contentView.button2.addActionListener((e)-> selectInfo("爬行动物"));
        contentView.button3.addActionListener((e)-> selectInfo("海鱼"));
        contentView.button4.addActionListener((e)-> selectInfo("节肢动物"));
        contentView.button5.addActionListener((e)-> selectInfo("软体动物"));
        contentView.button6.addActionListener((e)-> selectInfo("腔体动物"));
        contentView.button7.addActionListener((e)-> selectInfo("无脊椎动物"));
        contentView.button8.addActionListener((e)-> selectInfo("海洋植物"));
        contentView.searchButton.addActionListener((e)-> searchInfo(contentView.searchField.getText()));

        infoView.backButton.addActionListener((e)-> infoToContent());
        infoView.deleteButton.addActionListener((e)->{
            int n = JOptionPane.showConfirmDialog(null, "是否删除该生物信息?", "提示", JOptionPane.OK_CANCEL_OPTION);
            if(n == JOptionPane.YES_OPTION){
                infoView.flush();
                onDelete();
                infoView.flush();
            }
        });
        infoView.modifyButton.addActionListener((e)->{
            onModify();
            infoToModify();
        });

        modifyView.backButton.addActionListener((e)-> modifyToInfo());
        modifyView.saveButton.addActionListener((e)->{
            if(!modifyView.onSave_modify()) modifyView.rollback();
            modifyToInfo();
        });
    }

    private void onLogin(){
        String account = loginView.getName();
        String pwd = loginView.getPwd();
        if(!isLegalAccount(account) || !isLegalPwd(pwd)) return;
        User user = userDAO.singleQuery("select * from userinfo where account = ? and password = ?", User.class, account, pwd); //
        if(user == null){ //登录失败
            JOptionPane.showMessageDialog(null, "用户名或密码错误", "error", JOptionPane.ERROR_MESSAGE);
        }else{
            contentView.userField.setText(account);     //登录成功
            loginToContentView();
        }
    }

    private void onRegister(){
        String account = registerView.getName();
        String pwd  = registerView.getPwd();
        String email = registerView.getEmail();
        if(!isLegalAccount(account) || !isLegalPwd(pwd) || !isLegalEmail(email)) return;
        User user = userDAO.singleQuery("select * from userInfo where account = ?", User.class, account);
        if(user != null){
            JOptionPane.showMessageDialog(null, "用户名已存在!", "failed", JOptionPane.INFORMATION_MESSAGE);
            registerView.userField.setText("");
            return;
        }
        int update = userDAO.update("insert into userinfo values(?, ?, ?)", account, pwd, email);
        if(update > 0) {
            JOptionPane.showMessageDialog(null, "注册成功", "succeed", JOptionPane.INFORMATION_MESSAGE);
            loginView.userField.setText(registerView.userField.getText());
            registerToLogin();
        }else{
            JOptionPane.showMessageDialog(null, "系统错误", "failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loginToRegister(){     //登录界面跳转注册界面
        root.setImage("src\\View\\static\\iconImages\\bk02.jpeg");
        loginView.setVisible(false);
        registerView.setVisible(true);
        loginView.clear();
    }
    private void registerToLogin(){         //注册界面跳转登录界面
        root.setImage("src\\View\\static\\iconImages\\bk01.jpeg");
        loginView.setVisible(true);
        registerView.setVisible(false);
        registerView.clear();
    }

    private void loginToContentView(){      //登录界面跳转内容界面
        root.setImage("src\\View\\static\\iconImages\\bk03.jpg");
        loginView.setVisible(false);
        contentView.setVisible(true);
        loginView.clear();
    }

    private  void contentToAdd(){       //内容界面跳转添加界面
        root.setImage("src\\View\\static\\iconImages\\bk05.jpeg");
        contentView.setVisible(false);
        addView.setVisible(true);
    }
    private void addToContent(){        //添加界面跳转内容界面
        root.setImage("src\\View\\static\\iconImages\\bk03.jpg");
        addView.clear();
        contentView.setVisible(true);
        addView.setVisible(false);
    }

    private void contentToLogin(){      //内容界面跳转登录界面
        root.setImage("src\\View\\static\\iconImages\\bk01.jpeg");
        contentView.setVisible(false);
        loginView.setVisible(true);
        loginView.userField.setText(contentView.userField.getText());
    }

    private void infoToContent(){   //信息界面跳转内容界面
        root.setImage("src\\View\\static\\iconImages\\bk03.jpg");
        contentView.setVisible(true);
        infoView.setVisible(false);
        infoView.clear();
    }
    private void contentToInfo(){   //内容界面跳转信息界面
        root.setImage("src\\View\\static\\iconImages\\bk04.jpg");
        contentView.setVisible(false);
        infoView.setVisible(true);
    }
    private void infoToModify(){ //信息界面跳转到修改界面
        infoView.setVisible(false);
        modifyView.setVisible(true);
        infoView.clear();
    }
    private void modifyToInfo(){
        reCover(modifyView.mark);
        modifyView.setVisible(false);
        infoView.setVisible(true);
        modifyView.clear();
    }

    private void selectInfo(String type){   //根据动物类型检索

        List<MarineOrganism> animals= MODao.multiQuery("select * from animal where type = ?", MarineOrganism.class, type);
        if(animals.size() == 0) {
            JOptionPane.showMessageDialog(null, "无此类型生物信息!", "empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        infoView.loadRes((ArrayList<MarineOrganism>)animals);
        contentToInfo();
    }
    private void reCover(String mark){
        modifyView.clear();
        List<MarineOrganism> data = MODao.multiQuery("select * from animal where name = ?", MarineOrganism.class, mark);
        infoView.loadRes((ArrayList<MarineOrganism>) data);
    }

    private void searchInfo(String mark){
        if(mark.length() == 0){
            JOptionPane.showMessageDialog(null, "输入不能为空!", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<MarineOrganism> res = MODao.multiQuery("select * from animal where name = ?", MarineOrganism.class, mark);
        if(res.size() != 0){
            infoView.loadRes((ArrayList<MarineOrganism>) res);
            contentToInfo();
            return;
        }
        res = MODao.multiQuery("select * from animal where scientificName= ?", MarineOrganism.class, mark);
        if(res.size() != 0){
            infoView.loadRes((ArrayList<MarineOrganism>) res);
            contentToInfo();
            return;
        }
        JOptionPane.showMessageDialog(null, "未找到相关结果", "null", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onDelete(){
        int update = MODao.update("delete from animal where name = ?", infoView.nameField.getText());
        if(update == 0){
            JOptionPane.showMessageDialog(null, "系统出错!", "failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String path = infoView.animals.get(infoView.curIndex).getIconPath();
        if(infoView.animals.size() == 1){
            infoToContent();
            JOptionPane.showMessageDialog(null, "此生物信息已空!", "empty", JOptionPane.INFORMATION_MESSAGE);
        }else{
            ArrayList<MarineOrganism> temp = new ArrayList<>();
            for(int i = 0; i < infoView.animals.size(); i++)
                if(i != infoView.curIndex) temp.add(infoView.animals.get(i));
            infoView.clear();
            infoView.loadRes(temp);
            JOptionPane.showMessageDialog(null, "删除成功!", "succeed", JOptionPane.INFORMATION_MESSAGE);
        }
        File file = new File(path);
        file.delete();
    }

    private void onModify(){
        int idx = infoView.curIndex;
        MarineOrganism animal = new MarineOrganism(infoView.animals.get(idx));
        modifyView.mark = animal.getName();
        modifyView.nameField.setText(animal.getName());
        modifyView.scnameField.setText(animal.getScientificName());
        modifyView.typeField.setSelectedIndex(map.get(animal.getType()));
        modifyView.infoField.setText(animal.getInfomation());
        modifyView.pathField.setText(animal.getIconPath());
        modifyView.preData = new MarineOrganism(animal);
    }

    private boolean isLegalAccount(String account){
        //帐号(字母开头，允许5-12字节，允许字母数字下划线)
        boolean res = account.matches("^[a-zA-Z][a-zA-Z0-9_]{4,11}$");
        if(!res) JOptionPane.showMessageDialog(null, "帐号格式(字母开头，长度5-12，允许字母数字下划线!", "error", JOptionPane.ERROR_MESSAGE);
        return res;
    }
    private boolean isLegalPwd(String pwd){
        //密码中文、英文、数字及下划线  7 - 12
        boolean res = pwd.matches("[a-zA-Z0-9_]{6,12}$");
        if(!res) JOptionPane.showMessageDialog(null, "密码格式(英文、数字及下划线, 长度7-12)", "error", JOptionPane.ERROR_MESSAGE);
        return res;
    }

    private boolean isLegalEmail(String email){
        boolean res = email.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
        if(!res) JOptionPane.showMessageDialog(null,"邮箱格式错误", "error", JOptionPane.ERROR_MESSAGE);
        return res;
    }
}
