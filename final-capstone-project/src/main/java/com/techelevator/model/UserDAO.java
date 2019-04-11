package com.techelevator.model;

import java.util.List;

public interface UserDAO {

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public Object getUserByUserName(String userName);

	void saveUser(String userName, String password, String role);

	List<User> getAllUsers();

	void deleteUser(long id);

}
