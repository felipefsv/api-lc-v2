package com.lc.apilc.models.services;

import com.lc.apilc.models.entity.User;
import com.lc.apilc.models.request.UserRequest;
import com.lc.apilc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User createUser(UserRequest userRequest){
        User user = new User(userRequest);
        return this.userRepository.save(user);
    }

    public boolean existsByName(String userName){
        return this.userRepository.existsByName(userName);
    }

    public boolean existsByLogin(String login){
        return this.userRepository.existsByLogin(login);
    }

    public User findByLogin(String login){
        return this.userRepository.findByLogin(login);
    }

    public Optional<User> findById(UUID id){
        return this.userRepository.findById(id);
    }

    public boolean existsByNameAndLogin(String userName, String login){
        return this.userRepository.existsByNameAndLogin(userName,login);
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

}
