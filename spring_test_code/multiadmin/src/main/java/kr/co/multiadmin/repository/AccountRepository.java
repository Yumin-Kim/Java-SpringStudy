package kr.co.multiadmin.repository;

import kr.co.multiadmin.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    
    Account findByUsername(String username);
}
