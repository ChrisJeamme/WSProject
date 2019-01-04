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

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumerate the different roles a user may have
 */
public enum UserRole implements GrantedAuthority
{

    /**
     * User
     */
    USER,
    /**
     * Editor
     */ 
    EDITOR,
    /**
     * Admin
     */
    ADMIN;

    /**
     * @return the user autorithy
     */
    @Override
    public String getAuthority()
    {
        return this.name();
    }
}
