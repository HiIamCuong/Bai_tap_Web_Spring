package vn.iotstar.dao;

import vn.iotstar.models.User;

public interface UserDao {
	User get(String username);
	void insert(User user);
	void modified(String username,String password);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void updatefullname(String username,String fullname);
	void updatephone(String username,String phone);
	void updateavatar(String username, String avatar);
}
