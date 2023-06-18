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
    ContentView contentView = null; //搜索界面
    AddView addView = null; //添加界面
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


        loginView.setVisible(true);
        registerView.setVisible(false);
        contentView.setVisible(false);
        addView.setVisible(false);

        loginView.setBounds((width - loginViewWidth) / 2, (height - loginViewHeight) / 2,loginViewWidth, loginViewHeight);
        registerView.setBounds((width - loginViewWidth) / 2, (height - loginViewHeight) / 2,loginViewWidth, loginViewHeight);
        contentView.setBounds(0, 0, this.width, this.height);
        addView.setBounds(0, 0, this.width, this.height);

        root.add(loginView);
        root.add(registerView);
        root.add(contentView);
        root.add(addView);

    }
    public void init(){
//        enterLogin();
        enterContentView();
        loginView.loginButton.addActionListener((e)->{
            onLogin();
        });
        loginView.registerButton.addActionListener((e)->{
            enterRegister();
        });

        registerView.registerButton.addActionListener((e)->{
            onRegister();
        });
        registerView.backButton.addActionListener((e)->{
            enterLogin();
        });

        contentView.addButton.addActionListener((e)->{
            enterAdd();
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
            enterContentView();
            System.out.println("success");
        }
//        this.revalidate();
    }

    private void onRegister(){
        String account = registerView.getName();
        String pwd  = registerView.getPwd();
        String email = registerView.getEmail();
        if(!isLegalAccount(account) || !isLegalPwd(pwd) || !isLegalEmail(email)) return;
        int update = userDAO.update("insert into userinfo values(?, ?, ?)", account, pwd, email);
        if(update > 0) {
            JOptionPane.showMessageDialog(null, "注册成功", "succeed", JOptionPane.INFORMATION_MESSAGE);
            enterLogin();
        }else{
            JOptionPane.showMessageDialog(null, "系统错误", "failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void enterRegister(){
        System.out.println(cnt++);
        root.setImage("src\\View\\static\\iconImages\\bk02.jpeg");
        loginView.setVisible(false);
        registerView.setVisible(true);
        loginView.clear();
//        this.revalidate();
        this.repaint();
    }
    private void enterLogin(){
        root.setImage("src\\View\\static\\iconImages\\bk01.jpeg");
        loginView.setVisible(true);
        registerView.setVisible(false);
        registerView.clear();
        this.repaint();
    }

    private void enterContentView(){
        loginView.setVisible(false);
        contentView.setVisible(true);
    }

    private  void enterAdd(){
        contentView.setVisible(false);
        addView.setVisible(true);
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
