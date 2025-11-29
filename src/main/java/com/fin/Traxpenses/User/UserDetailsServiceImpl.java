package com.fin.Traxpenses.User;

import com.fin.Traxpenses.exceptions.UserOpsException;
import com.fin.Traxpenses.models.User;
import com.fin.Traxpenses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        User user = userRepository.findByNumber(number)
                .orElseThrow(() -> new UserOpsException( "No user found with this number", HttpStatus.NOT_FOUND));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getNumber())
                .password(user.getPassword())
                .disabled(!user.isActive())
                .build();
    }
}
