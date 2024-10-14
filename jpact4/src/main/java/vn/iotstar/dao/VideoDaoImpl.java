package vn.iotstar.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public class VideoDaoImpl implements VideoDao {
	@Override
	public void insert(Video vid) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(vid);

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
	public void update(Video vid) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(vid);

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
	public void delete(int vidid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			Video video = enma.find(Video.class, vidid);

			if (video != null) {

				enma.remove(video);

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

	@Override
	public Video findById(int vidid) {

		EntityManager enma = JPAConfig.getEntityManager();

		Video vid = enma.find(Video.class, vidid);

		return vid;

	}

	@Override
	public List<Video> findAll() {

		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		return query.getResultList();

	}

	@Override
	public List<Video> findAll(int page, int pagesize) {

		EntityManager enma = JPAConfig.getEntityManager();

		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		query.setFirstResult(page * pagesize);

		query.setMaxResults(pagesize);

		return query.getResultList();

	}

	@Override
	public int count() {

		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT count(c) FROM Videos c";

		Query query = enma.createQuery(jpql);

		return ((Long) query.getSingleResult()).intValue();

	}
}
