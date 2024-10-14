package vn.iotstar.Controller;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.VideoDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.CategoryServiceImpl;
import vn.iotstar.service.VideoService;
import vn.iotstar.service.VideoServiceImpl;
import vn.iotstar.ultis.Constant;
@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert",

		"/admin/video/edit", "/admin/video/update", "/admin/video/delete" })
public class VideoController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VideoService vidService = new VideoServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if(url.contains("/admin/videos"))
		{
			List<Video>list=vidService.findAll();
			System.out.print(list);
			req.setAttribute("listvid", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/add")) {

			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

		}
		else if (url.contains("/admin/video/edit")) {

			int id = Integer.parseInt(req.getParameter("id"));

			Video video = vidService.findById(id);

			req.setAttribute("vid", video);

			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);

		}
		else if(url.contains("/admin/video/delete")){

			int id = Integer.parseInt(req.getParameter("id"));

			try {

				vidService.delete(id);

			} catch (Exception e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/videos");

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/video/insert")) {

			// lấy dữ liệu từ form
			
			String vidtitle=req.getParameter("vidtitle");

			int active = Integer.parseInt(req.getParameter("active"));

			String poster = req.getParameter("poster");
			
			String decription=req.getParameter("Decription");
			
			int views=Integer.parseInt(req.getParameter("views"));

			// đưa dữ liệu vào model

			Video video = new Video();
			
			

			video.setTitle(vidtitle);

			video.setActive(active);
			
			video.setDescription(decription);
			
			video.setViews(views);

			String fname = "";

			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists())

				uploadDir.mkdir();

			try {

				Part part = req.getPart("images1");

				if (part.getSize() > 0) {

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					int index = filename.lastIndexOf(".");

					String ext = filename.substring(index + 1);

					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					video.setPoster(fname);

				} else if (poster != null) {

					video.setPoster(poster);

				} else {

					video.setPoster("default.png");

				}

			} catch (FileNotFoundException fne) {

				fne.printStackTrace();

			}

			// đưa model vào phương thức insert

			vidService.insert(video);

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/videos");

		}

		if (url.contains("/admin/video/update")) {

			// lấy dữ liệu từ form

			int vidid =Integer.parseInt(req.getParameter("vidid"));

			String vidtitle = req.getParameter("vidtitle");

			int active = Integer.parseInt(req.getParameter("active"));

			String poster = req.getParameter("poster");
			
			String description=req.getParameter("viddescription");
			
			int views=Integer.parseInt(req.getParameter("vidviews"));

			// đưa dữ liệu vào model

			Video video = vidService.findById(vidid);

			String fileold = video.getPoster();

			video.setTitle(vidtitle);

			video.setActive(active);
			
			video.setDescription(description);
			
			video.setViews(views);

			String fname = "";

			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists())

				uploadDir.mkdir();

			try {

				Part part = req.getPart("poster1");

				if (part.getSize() > 0) {

					// xóa file cũ trên thư mục

					if (!video.getPoster().substring(0, 5).equals("https")) {

						deleteFile(uploadPath + "\\" + fileold);

					}

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					int index = filename.lastIndexOf(".");

					String ext = filename.substring(index + 1);

					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					video.setPoster(fname);

				} else if (poster != null) {

					video.setPoster(poster);

				} else {

					video.setPoster(fileold);

				}

			} catch (FileNotFoundException fne) {

				fne.printStackTrace();

			}

			// đưa model vào phương thức insert
			vidService.update(video);

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/videos");

		}
	}
	public static void deleteFile(String filePath) throws IOException {

		Path path = Paths.get(filePath);

		Files.delete(path);

	}
}
