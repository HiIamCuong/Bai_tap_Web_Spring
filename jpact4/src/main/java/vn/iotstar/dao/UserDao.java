package vn.iotstar.dao;

import vn.iotstar.entity.User;

public interface UserDao {

	void delete(int userid) throws Exception;

	void update(User user);

	void insert(User user);

}
