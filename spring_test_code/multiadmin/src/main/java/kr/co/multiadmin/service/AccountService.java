package kr.co.multiadmin.service;

import kr.co.multiadmin.domain.Account;
import kr.co.multiadmin.AccountDto;
import kr.co.multiadmin.UserAccount;
import kr.co.multiadmin.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final CreateAccountService createAccountService;

    public void createAccount(AccountDto.RegisterRequest request) {
        Account account = createAccountService.createAccount(request.getUsername(), request.getPassword(), request.getRote());
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserAccount(account);
    }


}
