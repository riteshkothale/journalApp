package com.journal.journalApp.service;

import com.journal.journalApp.entity.User;
import com.journal.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder password = new BCryptPasswordEncoder();
    //save
    public void saveEntry(User user){
        user.setPassword(password.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    //getAll
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //get User
    public Optional<User> findById(ObjectId objectId){
       return userRepository.findById(objectId);
    }

    //delete by id
    public void deleteById(ObjectId objectId){
        userRepository.deleteById(objectId);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
