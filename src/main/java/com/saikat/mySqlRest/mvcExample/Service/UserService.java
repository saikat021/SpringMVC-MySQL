package com.saikat.mySqlRest.mvcExample.Service;

import com.saikat.mySqlRest.mvcExample.Dbutils.DbConnection;
import com.saikat.mySqlRest.mvcExample.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    DbConnection Con;

    //This Autowiring takes place after the execution of the constructor
    //so when we are calling the Con object from the constructor it is still null
    //When we have to use such bean in the constructor try passing through arguments of the constructor
    /*
    public UserService(DbConnection Con){
    Con.getConnection()------------>This works fine because it is taking the bean.

    }
     */

    public UserService()throws SQLException{
        //default constructor for the creation of bean
        //Con.getconnection()-------------------->NullPointerException



    }
    private static Logger logger = LoggerFactory.getLogger(UserService.class);



    public User get_user(int id) throws SQLException {
        String query="select * from user where id="+id+";";
        logger.error(query);

        Statement statement=Con.getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        logger.error("resultset found in get_user!!!!!");
//        resultSet.next();
//        int ID =resultSet.getInt("id");
//        String name =resultSet.getString("name");
//        String country=resultSet.getString("country");
//        int age=resultSet.getInt("age");

//            int ID,age;String name,country;
            while(resultSet.next()){
                int ID =resultSet.getInt("id");
                String name =resultSet.getString("name");
                String country=resultSet.getString("country");
                int age=resultSet.getInt("age");
                return new User(ID,name,country,age);

            }
            logger.error("User with the entered ID not found");
            return null;


//        int ID=resultSet.getInt(1);
//        String name=resultSet.getString(2);
//        String country=resultSet.getString(3);
//        int age =resultSet.getInt(4);




    }
    public List<User> get_users() throws SQLException{
        String query="select * from user";
        Statement statement=Con.getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        List<User> list =new ArrayList<>();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name =resultSet.getString("name");
            String country=resultSet.getString("country");
            int age =resultSet.getInt("age");
            list.add(new User(id,name,country,age));

        }
        return list;

    }
    public void insert_user(String name ,String country,String age )throws SQLException{
        String query="insert into user (name,country,age) values("+name+","+country+" ,"+age+");";
        int rows_modified=Con.getConnection().createStatement().executeUpdate(
                query
        );
        logger.error("No of rows modified:{}",rows_modified);



    }
    public void delete_user(int id) throws SQLException {
        Con.getConnection().createStatement().execute("delete from user where id="+id);
    }

}
