package vn.iotstar.service;

import java.util.List;

import vn.iotstar.dao.VideoDao;
import vn.iotstar.dao.VideoDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public class VideoServiceImpl implements VideoService{

	public VideoDao vidDao = new VideoDaoImpl();
	@Override
	public void delete(int vidid) throws Exception {
		try {

			vidDao.delete(vidid);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}

	@Override
	public void update(Video video) {
		if (video != null) {

			vidDao.update(video);

		}
		
	}

	@Override
	public void insert(Video video) {
		Video videoinsert = this.findById(video.getVideoId());

		if (videoinsert == null) {

			vidDao.insert(video);

		}
	}
	public Video findById(int id) {
		try {

			return vidDao.findById(id);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return vidDao.findAll(page,pagesize);
	}

	@Override
	public List<Video> findAll() {
		return vidDao.findAll();
	}

}
