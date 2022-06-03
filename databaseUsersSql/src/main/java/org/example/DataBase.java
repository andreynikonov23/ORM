package org.example;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DataBase implements CRUD {
    private static DataBase instance;


    private DataBase(){}

    public static DataBase getInstance(){
        if (instance == null){
            instance = new DataBase();
        }
        return instance;
    }


    private Connection getConnect()  {
        Connection con = null;
        try {
            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
            if (!(con.isClosed())){
                System.out.println("connection is open");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    @Override
    public void insert(User user) {
        try(PreparedStatement st = getConnect().prepareStatement("INSERT INTO `mydb`.`users` (`name`, `age`) VALUES (?, ?)")) {
            st.setString(1, user.getName());
            st.setInt(2, user.getAge());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try(PreparedStatement st = getConnect().prepareStatement("UPDATE `mydb`.`users` SET name = ?, age = ? WHERE (id = ?)")){
            st.setString(1, user.getName());
            st.setInt(2, user.getAge());
            st.setInt(3, user.getId());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        try(PreparedStatement st = getConnect().prepareStatement("DELETE FROM users WHERE id=?")) {
            st.setInt(1, user.getId());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void read() {
        try(Statement statement = getConnect().createStatement()) {
            ResultSet set = statement.executeQuery("SELECT * FROM users");
            while (set.next()){
                User user = new User(set.getInt("id"), set.getString("name"), set.getInt("age"));
                UsersList.USERS.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
