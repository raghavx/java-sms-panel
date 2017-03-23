package com.raghavx.auth.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * The Class User.
 */
@Entity
@Data
@Table(name = "USER_TABLE", uniqueConstraints={
   @UniqueConstraint(columnNames={"USER_NAME"}),
   @UniqueConstraint(columnNames={"EMAIL"})
})
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements UserDetails {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

    /** The username. */
	@NotNull
    @Column(name = "USER_NAME",length=64)
	private String username;

    /** The password. */
	@Size(min=4 , max=20)
    @Column(name = "PASSWORD")
	private String password;
	
	@Size(min=4 , max=20)
    @Column(name = "CONFIRM_PASSWORD")
	private String confirmPassword;

    /** The email. */
    @Column(name = "EMAIL",length=64)
    private String email;

    private boolean rental;
    

    private boolean apiEnabled;
    
    private long credit;
    
       	/** The created on. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON",length=6)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdOn;

    /** The updated on. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_ON",length=6)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date updatedOn;

    
	/** The roles. */
	private String[] roles;

	/** The account non expired. */
	private boolean accountNonExpired = true;

	/** The account non locked. */
	private boolean accountNonLocked = true;

	/** The enabled. */
	private boolean enabled = true;

	/** The credentials non expired. */
	private boolean credentialsNonExpired = true;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return AuthorityUtils.createAuthorityList(roles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime;
		result = prime * result + ((this.getUsername() == null) ? 0 : this.getUsername().hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + "]";
	}
	
	
}
