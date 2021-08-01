package kr.co.fastcampus.cli;

import kr.co.fastcampus.model.Member;

import java.sql.*;

class Main {
    public static <statement> void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello Main");
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:mem:jdbc;MODE=MySQL;";
        try (
                Connection connection = DriverManager.getConnection(url, "sa", "");
                Statement statement = connection.createStatement()
        ) {
            connection.setAutoCommit(false);
            statement.execute("create table member (id int auto_increment , username varchar(255) not null , password varchar(255) not null , primary key(id));");
            try {
                statement.executeUpdate("insert into member(username , password ) values ('username1' , 'password' )");
                connection.commit();
            } catch (SQLException ignored) {
                connection.rollback();
            }
            ResultSet resultSet = statement.executeQuery("select * from member");
            while (resultSet.next()) {
                String password = resultSet.getString("password");
                Member member = new Member(resultSet);
                System.out.println("member.toString() = " + member.toString());
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}