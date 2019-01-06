/*
 *      _____   _                   _            _____                      _   
 *     |  __ \ (_) Website Project | |          / ____|                    | |  
 *     | |  | | _  _ __  ___   ___ | |_    ___ | (___   _ __    ___   _ __ | |_ 
 *     | |  | || || '__|/ _ \ / __|| __|  / _ \ \___ \ | '_ \  / _ \ | '__|| __|
 *     | |__| || || |  |  __/| (__ | |_  |  __/ ____) || |_) || (_) || |   | |_ 
 *     |_____/ |_||_|   \___| \___| \__|  \___||_____/ | .__/  \___/ |_|    \__|
 *                                                     | |                                                                                              
 *  ----Authors----                                    |_| 
 * Dimitri BRUYERE
 * Clément COLIN
 * Christopher JEAMME
 *  ---------------
 */
package com.projetws.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class to communicate with the table User in the database
 */
@Component
public class EmployeeService implements UserDetailsService
{

    @Autowired
    EmployeeRepository repo;

    /**
     * encoder for passwords 
     */
    public final PasswordEncoder encoder = new BCryptPasswordEncoder();
 
    /** 
     * Get the user by username and set the session
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Employee u = repo.findByUserName(username);
        if (u == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(u.getUserName(), u.getPassword(), u.getRoles());
    }

    /**
     * Add user in the database
     * @param u
     * @return 0 (good), 1 (username already exists), 2 (mail already exists)
     */
    public int addUser(Employee u)
    {
        if (repo.findByUserName(u.getUserName()) != null)
        {
            return 1;	//A user with this userName already exists
        }
        else
        {
            u.setPassword(encoder.encode(u.getPassword()));
            repo.save(u);
            return 0;	//The user is added in the database
        }
    }

    /**
     * change the role of a user to admin
     * @param username
     */
    public void makeUserAdmin(String username)
    {
    	Employee u = repo.findByUserName(username);
        u.getRoles().add(EmployeeRole.ALL);
        repo.save(u);
    }

    /**
     * change the role of a user to editor
     * @param username
     */
    public void makeUserEditor(String username)
    {
    	Employee u = repo.findByUserName(username);
        u.getRoles().add(EmployeeRole.EDITOR);
        repo.save(u);
    }

    /**
     * change the password of a user 
     * @param userName
     * @param newPassword
     * @return 1 (good) or -1 if the user doesn't exist
     */
    public int changeUserPassword(String userName, String newPassword)
    {
    	Employee u = repo.findByUserName(userName);
        if (u != null)
        {
            u.setPassword(encoder.encode(newPassword));
            repo.save(u);
            return 1; //Success
        }
        return -1; //Never
    }

}
