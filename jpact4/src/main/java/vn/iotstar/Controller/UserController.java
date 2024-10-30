package vn.iotstar.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.Data;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.CategoryServiceImpl;
import vn.iotstar.service.UserService;
import vn.iotstar.service.UserServiceImpl;
import vn.iotstar.ultis.Constant;

@MultipartConfig()

@WebServlet(urlPatterns = { "/admin/users", "/admin/user/add", "/admin/user/insert",

		"/admin/user/edit", "/admin/user/update", "/admin/user/delete" })
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/users")) {

			List<User> list = userService.findAll();

			req.setAttribute("usercate", list);

			req.getRequestDispatcher("/views/user/category-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/user/add")) {

			req.getRequestDispatcher("/views/user/category-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/user/edit")) {

			int id = Integer.parseInt(req.getParameter("id"));

			User user = userService.findById(id);

			req.setAttribute("user", user);

			req.getRequestDispatcher("/views/user/category-edit.jsp").forward(req, resp);

		} else if(url.contains("/admin/user/delete")){

			int id = Integer.parseInt(req.getParameter("id"));

			try {

				userService.delete(id);

			} catch (Exception e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/user");

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/user/update")) {

			// lấy dữ liệu từ form

			int userid = Integer.parseInt(req.getParameter("id"));

			String email=req.getParameter("email");
			
			String username = req.getParameter("username");
			
			String password=req.getParameter("password");
			
			String avatar = req.getParameter("avatar");
			
			int roleid=Integer.parseInt(req.getParameter("role"));
			
			String phone=req.getParameter("phone");
			
			String createdDate=req.getParameter("createdate"));
			

			// đưa dữ liệu vào model

			User user = userService.findById(userid);

			String fileold = category.getImages();

			category.setCategoryname(categoryname);

			category.setStatus(status);

			String fname = "";

			String uploadPath = Constant.DIR; // upload vào thư mục bất kỳ

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists())

				uploadDir.mkdir();

			try {

				Part part = req.getPart("images1");

				if (part.getSize() > 0) {

					// xóa file cũ trên thư mục

					if (!category.getImages().substring(0, 5).equals("https")) {

						deleteFile(uploadPath + "\\" + fileold);

					}

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					int index = filename.lastIndexOf(".");

					String ext = filename.substring(index + 1);

					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					category.setImages(fname);

				} else if (images != null) {

					category.setImages(images);

				} else {

					category.setImages(fileold);

				}

			} catch (FileNotFoundException fne) {

				fne.printStackTrace();

			}

			// đưa model vào phương thức insert

			cateService.update(category);

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}
	}

	public static void deleteFile(String filePath) throws IOException {

		Path path = Paths.get(filePath);

		Files.delete(path);

	}

}
