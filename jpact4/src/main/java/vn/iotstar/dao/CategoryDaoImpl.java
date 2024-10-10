package vn.iotstar.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.entity.Category;
import java.util.List;




public class CategoryDaoImpl implements CategoryDao{
	@Override
	public void insert(Category cate) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.persist(cate);

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
	public void update(Category cate) {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.merge(cate);

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
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();

			enma.remove(cateid);

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
	public Category findById(int cateid) {

		EntityManager enma = JPAConfig.getEntityManager();

		Category category = enma.find(Category.class, cateid);

		return category;

	}

	@Override

	public Category findByCategoryname(String name) throws Exception {

		EntityManager enma = JPAConfig.getEntityManager();

		String jpql = "SELECT c FROM categories c WHERE c.categoryname =:name";

		try {

			TypedQuery<Category> query = enma.createQuery(jpql, Category.class);

			query.setParameter("catename", name);

			Category category = query.getSingleResult();

			if (category == null) {

				throw new Exception("Category Name đã tồn tại");

			}

			return category;

		} finally {

			enma.close();

		}

	}
	@Override


	 public List<Category> findAll() {


	 EntityManager enma = JPAConfig.getEntityManager();


	 TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);


	 return query.getResultList();


	 }
	@Override


	 public List<Category> searchByName(String catname) {


	 EntityManager enma = JPAConfig.getEntityManager();


	 String jpql = "SELECT c FROM categories c WHERE c.categoryname like :catname";


	 TypedQuery<Category> query= enma.createQuery(jpql, Category.class);


	 query.setParameter("catename", "%" + catname + "%");


	 


	 return query.getResultList();


	 }
	@Override


	 public List<Category> findAll(int page, int pagesize) {


	 EntityManager enma = JPAConfig.getEntityManager();


	 TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);


	 query.setFirstResult(page*pagesize);


	 query.setMaxResults(pagesize);


	 return query.getResultList();


	 }
	@Override


	 public int count() {


	 EntityManager enma = JPAConfig.getEntityManager();


	 String jpql = "SELECT count(c) FROM category c";


	 Query query = enma.createQuery(jpql);


	 return ((Long)query.getSingleResult()).intValue();


	 }

}
