package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;

public interface UserDao {

	void delete(int userid) throws Exception;

	void update(User user);

	void insert(User user);

	public List<User> findAll(int page, int pagesize);
	
	public List<User> findAll();
	
	public User findById(int userid);
}
