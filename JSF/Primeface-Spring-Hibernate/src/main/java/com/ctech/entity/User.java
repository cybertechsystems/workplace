package com.ctech.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "User.findByUserName", 
				query = " SELECT u FROM User u WHERE u.login = ? "),
	@NamedQuery(name = "User.findByUserDetails", 
				query = " SELECT u FROM User u WHERE u.login = ? and u.password = ? "),
	@NamedQuery(name = "User.findByEmail", 
				query = " SELECT u FROM User u WHERE u.email = ? "),
	@NamedQuery(name = "User.findAllUsers", 
				query = " SELECT u FROM User u "),
	@NamedQuery(name = "User.searchResults", 
				query = " SELECT u FROM User u WHERE u.login like ? or u.firstname " +
						"like ? or u.lastname like ? ")
})

public class User implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
	@Column(name = "login", unique = true, nullable = true, length = 10)
	private String login;
    
	@Column(name = "email", unique = true, nullable = false, length = 100)
	private String email;
	
	@Column(name = "password", nullable = false, length = 100)
    private String password;
	
	@Column(name = "firstname", nullable = true, length = 100)
    private String firstname;
	
	@Column(name = "lastname", nullable = true, length = 100)
    private String lastname;
    
    @Transient
	private Address address;
    
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "userrole", joinColumns = {
			@JoinColumn(name = "id" , referencedColumnName = "id"),
			
		},inverseJoinColumns = {
			@JoinColumn(name = "roleId" , referencedColumnName = "roleId"),
	})
    private List<Role> roleList;
    
    public List<Role> getRoleList() {
		if(roleList == null) {
			roleList = new ArrayList<Role>(); 
		} 
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
    
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Address> addressList;
	
	public List<Address> getAddressList() {
		if(addressList == null) {
			addressList = new ArrayList<Address>(); 
		} 
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public User() {
        super();
        address = new Address();
    }

    /**
     * @param login
     * @param email
     * @param password
     */
    public User(String login, String email, String password, String firstname, String lastname, Address address) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
