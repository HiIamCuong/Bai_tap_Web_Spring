package springthymeleaf.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import springthymeleaf.entity.CategoryEntity;
import springthymeleaf.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	CategoryRepository categoryRepostory;
	public CategoryServiceImpl(CategoryRepository categoryRepository)
	{
		this.categoryRepostory=categoryRepository;
	}
	@Override
	public<S extends CategoryEntity>S save(S entity)
	{
		if(entity.getCategoryId()==null)
		{
			return categoryRepostory.save(entity);
		}
		else
		{
			Optional<CategoryEntity>opt=categoryRepostory.findById(entity.getCategoryId());
			if(opt.isPresent())
			{
				if(StringUtils.isEmpty(entity.getName()))
				{
					entity.setName(opt.get().getName());
				}
			}
			return categoryRepostory.save(entity);
			
		}
	}
	@Override
	public List<CategoryEntity>findAll()
	{
		return categoryRepostory.findAll();
	}
	@Override
	public Page<CategoryEntity> findAll(Pageable pageable)
	{
		return categoryRepostory.findAll(pageable);
	}
	@Override
	public List<CategoryEntity> findAll(Sort sort)
	{
		return categoryRepostory.findAll(sort);
	}
	@Override
	public List<CategoryEntity> findAll(Iterable<Long>ids)
	{
		return categoryRepostory.findAllById(ids);
	}
	@Override
	public Optional<CategoryEntity>findById(Long id)
	{
		return categoryRepostory.findById(id);
	}
	@Override
	public <S extends CategoryEntity> Optional<S> findOne(Example<S>example)
	{
		return categoryRepostory.findOne(example);
	}
	@Override
	public long count()
	{
		return categoryRepostory.count();
	}
	@Override
	public void deleteById(Long id)
	{
		categoryRepostory.deleteById(id);
	}
	@Override
	public void delete(CategoryEntity entity)
	{
		categoryRepostory.delete(entity);
	}
	@Override
	public void deleteAll()
	{
		categoryRepostory.deleteAll();
	}
	@Override
	public List<CategoryEntity>findByNameContaining(String name)
	{
		return categoryRepostory.findByNameContaining(name);
	}
	@Override
	public Page<CategoryEntity>findByNameContaining(String name,Pageable pagable)
	{
		return categoryRepostory.findByNameContaining(name,pagable);
	}
}
