package vn.iotstar.service;

import java.util.List;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public interface VideoService {
	void delete(int vidid) throws Exception;

	void update(Video video);

	void insert(Video video);

	public Video findById(int id);

	List<Video> findAll(int page, int pagesize);

	List<Video> findAll();
}
