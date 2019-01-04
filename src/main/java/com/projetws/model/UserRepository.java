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

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for users
 */
public interface UserRepository extends CrudRepository<User, String>
{

    /**
     * @return all users
     */
    public List<User> findAllByOrderByUserName();

    /**
     * @param name
     * @return return the user with the name
     */
    public User findByUserName(String name);

    /**
     * @param mail
     * @return the user with the mail
     */
    public User findByMail(String mail);

}
