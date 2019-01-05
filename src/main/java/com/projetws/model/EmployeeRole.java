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
     * All rights (CEO)
     */
    ALL,
    /**
     * For others
     */
    DEFAULT;

    /**
     * @return the user authority
     */
    @Override
    public String getAuthority()
    {
        return this.name();
    }
}
