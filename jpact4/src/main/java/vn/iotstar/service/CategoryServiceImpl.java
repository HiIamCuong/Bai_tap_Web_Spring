package vn.iotstar.service;

import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.CategoryDaoImpl;
import vn.iotstar.entity.Category;

public class CategoryServiceImpl implements CategoryService {

	public CategoryDao cateDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		Category cate = this.findByCategoryname(category.getCategoryname());

		if (cate == null) {

			cateDao.insert(category);

		}

	}

	@Override
	public int count() {
		return cateDao.count();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		return cateDao.findAll(page, pagesize);
	}

	@Override
	public List<Category> searchByName(String catname) {
		return cateDao.searchByName(catname);
	}

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		return cateDao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {

		try {

			cateDao.delete(cateid);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override
	public void update(Category category) {

		if (category != null) {

			cateDao.update(category);

		}

	}

	@Override
	public Category findByCategoryname(String name) {
		try {

			return cateDao.findByCategoryname(name);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

}
