package com.team9.motors.interfacemethods;

import com.team9.motors.model.User;
import org.springframework.data.domain.Page;

public interface UserInterface {

    public void createUser(User user);
    public User findById(Integer id);
    public void deleteUser(User user);
    public Page<User> findAll(int pageNumber) ;

}
