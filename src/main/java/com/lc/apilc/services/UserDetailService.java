package com.lc.apilc.services;

import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.LcException;
import com.lc.apilc.models.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        com.lc.apilc.models.entity.User user = this.userService.findByLogin(login);

        if (user == null) {
            throw new LcException("Login Inválido!", ErrorCodes.LOGIN_INVALIDO);
        }

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserDetails authenticate(LoginRequest loginRequest) {
        UserDetails userDetails = loadUserByUsername(loginRequest.getLogin());
        boolean valid = passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword());
        if (valid) {
            return userDetails;
        }
        throw new LcException("Login Inválido!", ErrorCodes.LOGIN_INVALIDO);
    }

}
