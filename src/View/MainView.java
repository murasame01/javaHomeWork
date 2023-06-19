package View;

import DAO.dao.UserDAO;
import DAO.domain.User;
import View.utils.PictureView;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    int cnt = 1;

    private UserDAO userDAO = new UserDAO();
    private int width = 980;
    private int height = 720 ;
    private boolean pwdVisible = false;
    private PictureView pwdView = null;

    LoginView loginView = null;  //登录界面
    RegisterView registerView  = null;  //注册界面
    ContentView contentView = null; //内容界面
    AddView addView = null; //添加界面
    InfoView infoView = null; //信息界面
    PictureView pwdOn = new PictureView();
    PictureView pwdOff = new PictureView();
    PictureView root = new PictureView();

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
    public void render(){
        int loginViewWidth = 500;
        int loginViewHeight = 300;


        loginView = new LoginView();
        registerView = new RegisterView();
        contentView = new ContentView();
        addView = new AddView();
        infoView = new InfoView();


        loginView.setVisible(false);
        registerView.setVisible(false);
        contentView.setVisible(false);
        addView.setVisible(false);
        infoView.setVisible(true);

        loginView.setBounds((width - loginViewWidth) / 2, (height - loginViewHeight) / 2,loginViewWidth, loginViewHeight);
        registerView.setBounds((width - loginViewWidth) / 2, (height - loginViewHeight) / 2,loginViewWidth, loginViewHeight);
        contentView.setBounds(0, 0, this.width, this.height);
        addView.setBounds(0, 0, this.width, this.height);
        infoView.setBounds(0, 0, this.width, this.height);

        root.add(loginView);
        root.add(registerView);
        root.add(contentView);
        root.add(addView);
        root.add(infoView);
    }
    public void init(){

//        registerToLogin();
//        loginToContentView();
        loginView.loginButton.addActionListener((e)->{
            onLogin();
        });
        loginView.registerButton.addActionListener((e)->{
            loginToRegister();
        });

        registerView.registerButton.addActionListener((e)->{
            onRegister();
        });
        registerView.backButton.addActionListener((e)->{
            registerToLogin();
        });

        contentView.addButton.addActionListener((e)->{
            contentToAdd();
        });
        addView.backButton.addActionListener((e)->{
            addToContent();
        });

        contentView.quitButton.addActionListener((e)->{
            contentToLogin();
        });

    }

    private void flush(){
        this.root.repaint();
        this.root.updateUI();

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
            System.out.println("success");
        }
    }

    private void onRegister(){
        String account = registerView.getName();
        String pwd  = registerView.getPwd();
        String email = registerView.getEmail();
        if(!isLegalAccount(account) || !isLegalPwd(pwd) || !isLegalEmail(email)) return;
        int update = userDAO.update("insert into userinfo values(?, ?, ?)", account, pwd, email);
        if(update > 0) {
            JOptionPane.showMessageDialog(null, "注册成功", "succeed", JOptionPane.INFORMATION_MESSAGE);
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
        loginView.setVisible(false);
        contentView.setVisible(true);
        loginView.clear();
    }

    private  void contentToAdd(){       //内容界面跳转添加界面
        contentView.setVisible(false);
        addView.setVisible(true);
    }
    private void addToContent(){        //添加界面跳转内容界面
        addView.clear();
        contentView.setVisible(true);
        addView.setVisible(false);
    }

    private void contentToLogin(){      //内容界面跳转登录界面
        contentView.setVisible(false);
        loginView.setVisible(true);
        loginView.userField.setText(contentView.userField.getText());
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
