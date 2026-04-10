/**
 * 
 */
package com.parking.models;

/**
 * 
 */
public class User {
	private String name;
	private String password;
	private String fullName;
	private UserRole role;
	/**
	 * @param name
	 * @param password
	 * @param fullName
	 * @param role
	 */
	public User(String name, String password, String fullName, UserRole role) {
		super();
		this.name = name;
		this.password = password;
		this.fullName = fullName;
		this.role = role;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
