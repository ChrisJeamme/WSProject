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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;

/**
 * User model class
 */
@Entity
@Data
public class User
{

    @Id
    String userName;

    String pseudo;

    String realName;

    String password;

    String mail;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    /**
     * Constructor
     */
    public User()
    {
    }

    /**
     * Constructor
     * @param userName
     * @param realName
     * @param password
     * @param mail
     */
    public User(String userName, String realName, String password, String mail)
    {
        this.userName = userName;
        this.realName = realName;
        this.roles.add(UserRole.USER);
        this.password = password;
        this.mail = mail;
        this.pseudo = userName;
    }

    /**
     * Constructor
     * @param userName
     * @param displayName
     * @param roles
     * @param derivedPassword
     * @param mail
     */
    public User(String userName, String displayName, List<String> roles, String derivedPassword, String mail)
    {
        this.userName = userName;
        this.realName = displayName;
        roles.forEach((r) ->
        {
            this.roles.add(UserRole.valueOf(r));
        });
        this.password = derivedPassword;
        this.mail = mail;
        this.pseudo = userName;
    }

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPseudo()
	{
		return pseudo;
	}

	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public Set<UserRole> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<UserRole> roles)
	{
		this.roles = roles;
	}
    
    

}
