package springthymeleaf.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	List<CategoryEntity>findByNameContaining(String name);
	Page<CategoryEntity>findByNameContaining(String name, Pageable page);
}
