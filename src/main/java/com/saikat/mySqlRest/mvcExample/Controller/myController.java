package com.saikat.mySqlRest.mvcExample.Controller;


import com.saikat.mySqlRest.mvcExample.Model.User;
import com.saikat.mySqlRest.mvcExample.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class myController {

    @Autowired
    UserService db;

    @GetMapping("/get_users")
    public List<User> getUsers()throws SQLException {
        return db.get_users();

    }

    @GetMapping("/get_user")
    public User getUser(@RequestParam(value = "id",required = true) int id) throws SQLException {
        return db.get_user(id);
    }

    @PostMapping("/insert_user")
    public void insertUser(
                           @RequestParam("name") String name,
                           @RequestParam("country") String country,
                           @RequestParam("age") int age) throws SQLException {

        db.insert_user("'"+name+"'","'"+country+"'","'"+age+"'");

    }
    @DeleteMapping("/delete_user")
    public void deleteUser(@RequestParam("id") int id) throws SQLException {
        db.delete_user(id);

    }
}
