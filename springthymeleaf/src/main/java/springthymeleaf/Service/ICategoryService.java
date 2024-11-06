package springthymeleaf.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import springthymeleaf.entity.CategoryEntity;

public interface ICategoryService {

	Page<CategoryEntity> findByNameContaining(String name, Pageable pagable);

	List<CategoryEntity> findByNameContaining(String name);

	void deleteAll();

	void delete(CategoryEntity entity);

	void deleteById(Long id);

	long count();

	<S extends CategoryEntity> Optional<S> findOne(Example<S>example);

	Optional<CategoryEntity> findById(Long id);

	List<CategoryEntity> findAll(Iterable<Long>ids);

	List<CategoryEntity> findAll(Sort sort);

	Page<CategoryEntity> findAll(Pageable pageable);

	List<CategoryEntity> findAll();

	<S extends CategoryEntity> S save(S entity);
	
}
