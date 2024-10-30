package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;

public interface UserService {
	void delete(int vidid) throws Exception;

	void update(User user);

	void insert(User user);

	public User findById(int id);

	List<User> findAll(int page, int pagesize);

	List<User> findAll();
}
