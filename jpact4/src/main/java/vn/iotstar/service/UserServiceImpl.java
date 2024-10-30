package vn.iotstar.service;

import java.util.List;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;

public class UserServiceImpl implements UserService{
	public UserDao userDao = new UserDaoImpl();
	@Override
	public void delete(int vidid) throws Exception {
		try {

			userDao.delete(vidid);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}

	@Override
	public void update(User user) {
		if (user != null) {

			userDao.update(user);

		}
		
	}

	@Override
	public void insert(User user) {
		User userinsert = this.findById(user.getUserid());

		if (userinsert == null) {

			userDao.insert(user);

		}
		
	}

	@Override
	public User findById(int id) {
		try {

			return userDao.findById(id);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page,pagesize);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	
}
