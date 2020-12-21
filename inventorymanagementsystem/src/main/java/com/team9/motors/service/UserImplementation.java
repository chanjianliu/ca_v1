package com.team9.motors.service;

import com.team9.motors.interfacemethods.UserInterface;
import com.team9.motors.model.User;
import com.team9.motors.repository.UserRepository;
import javassist.compiler.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

@Service
public class UserImplementation implements UserInterface {

   @Autowired
   private UserRepository userRepository;

   //Create a user
   @Transactional
   public void createUser(User user){ userRepository.save(user);}


   //Read individual user
   @Transactional
   public User getUser(Integer id){return userRepository.getOne(id); }

   //Update User
   @Transactional
   public void updateUser(User user){userRepository.save(user);};

   //Delete User
   @Transactional
   public void deleteUser(User user){ userRepository.delete(user);}

   //Find by UserID
   @Transactional
   public User findById(Integer id) {
      return userRepository.findById(id).get();
   }

   //Find by UserName
   @Transactional
   public User getUserByUsername(String name) {
       User user = userRepository.findByUsername(name);
       return user;
   }

   //Pagination
   public Page<User> findAll(int pageNumber) {
      Pageable pageable=PageRequest.of(pageNumber - 1, 5);
      return  userRepository.findAll(pageable);
   }
}
