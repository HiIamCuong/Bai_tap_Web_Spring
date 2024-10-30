package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@AllArgsConstructor

@NoArgsConstructor

@Data

@Table(name = "Users")

@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "userId")

	private int userid;
	
	@Column(name = "email")
	
	private String email;
	
	@Column(name = "username")
	
	private String username;
	
	@Column(name = "password")
	
	private String password;
	
	@Column(name = "avatar")
	
	private String avatar;
	
	@Column(name = "roleid")
	private int roleid;
	
	@Column(name = "phone")
	
	private String phone;
	
	@Column(name = "createdDate")
	
	private Date createdDate;
	
}
