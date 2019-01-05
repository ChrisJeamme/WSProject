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
 * Enumerate the different roles a employee may have
 */
public enum EmployeeRole implements GrantedAuthority
{

    /**
     * Consult data from locations, regions and country
     */
    CONSULT,
    /**
     * Edit access rights for employees and jobs and can consult the rest
     */ 
    EDITOR,
    /**
     * All rights
     */
    ALL;

    /**
     * @return the user authority
     */
    @Override
    public String getAuthority()
    {
        return this.name();
    }
}
