package kr.co.multiadmin.service;

import kr.co.multiadmin.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account createAccount(String username, String purePassword, String role){
        return Account.with(username, passwordEncoder.encode(purePassword), role);
    }
}
