package kr.co.multiadmin.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String role;

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setRole(String role) {
        this.role = role;
    }

    public static Account with(String username, String purePassword, String role){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(purePassword);
        account.setRole(role);
        return account;
    }

}
