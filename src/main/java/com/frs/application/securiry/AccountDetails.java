package com.frs.application.securiry;

import com.frs.application.logic.IAccountLogic;
import com.frs.core.helpers.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetails implements UserDetailsService {
    private final IAccountLogic accountLogic;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = accountLogic.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(MessageHelper.getMessage("validation.account.account_not_found"));
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();
    }
}
