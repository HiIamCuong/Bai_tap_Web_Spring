package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.config.DBconnectSQL;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;

public class UserDaoImpl implements UserDao{
	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(user);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(user);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public void delete(int userid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			Category category = enma.find(Category.class, userid);

			if (category != null) {

				enma.remove(category);

			} else {

				throw new Exception("Không tìm thấy");

			}

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}
}
