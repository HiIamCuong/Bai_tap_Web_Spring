package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Video;

public interface VideoDao {

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findAll();

	Video findById(int vidid);

	void delete(int vidid) throws Exception;

	void update(Video vid);

	void insert(Video vid);

}
