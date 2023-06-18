package DAO.test;

import DAO.dao.BasicDAO;
import DAO.dao.MarineOrganismDAO;
import DAO.dao.UserDAO;
import DAO.domain.User;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
//        List<User> users = userDAO.multiQuery("SELECT * FROM userinfo", User.class);
//        for(User user : users) System.out.println(user);


        User user = userDAO.singleQuery("select * from userinfo where account = ? and password = ?", User.class, "kkk", "222");
        System.out.println(user);


//        Object o = userDAO.scalarQuery("select name from userinfo where name = ?", "2020");
//        System.out.println(o);


//        int update = userDAO.update("insert into userinfo values(?, ?, ?)", "yuki", "123456", "222");
//        if(update > 0) System.out.println("succeed");


    }
}
