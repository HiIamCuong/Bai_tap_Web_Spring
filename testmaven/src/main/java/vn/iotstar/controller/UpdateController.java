package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.models.Category;
import vn.iotstar.models.User;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.CategoryServiceImpl;
import vn.iotstar.service.UserService;
import vn.iotstar.service.UserServiceImpl;
import vn.iotstar.ultis.Constant;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet(urlPatterns = "/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return Constant.DEFAULT_FILENAME;
	}*/
	public UserService userservice=new UserServiceImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(true);
		User userlist=(User)session.getAttribute("account");
		User user=userservice.findByUserName(userlist.getUsername());
		req.setAttribute("user", user);
		req.getRequestDispatcher("views/update.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session=req.getSession(true);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String fullname=req.getParameter("fullname");
		String phone=req.getParameter("phone");
		String username=req.getParameter("username");
		
		String alertMsg="";
		
		User user=userservice.findByUserName(username);
		if(!fullname.isEmpty())
		{
			userservice.updatefullname(username, fullname);
			user.setFullname(fullname);
			req.setAttribute("alert", "Cập nhật thông tin thành công");
		}
		if(!phone.isEmpty())
		{
			userservice.updatephone(username, phone);
			user.setPhone(phone);
			req.setAttribute("alert", "Cập nhật thông tin thành công");
		}
		//String uploadPath = File.separator + Constant.DIR;
		
		//File uploadDir = new File(uploadPath);
		System.out.print(username+" day ");
		User userold=userservice.findByUserName(username);
		String fileold=userold.getAvatar();
		String fname="";
		String uploadpath=Constant.DIR;
		File uploadDir=new File(uploadpath);
		
		if (!uploadDir.exists())
			uploadDir.mkdir();
		
		try {
			/*String fileName = "";
			for (Part part : req.getParts()) {
				fileName = getFileName(part);
				if(!fileName.isEmpty())
					part.write(uploadPath + File.separator + fileName);
			}
			if(fileName.isEmpty())
			{
				if(fullname.isEmpty() && phone.isEmpty())
				{
					req.setAttribute("alert", "Vui lòng nhập ít nhất 1 ô");
				}
			}
			else
			{
				service.updateavatar(username, fileName);
				req.setAttribute("alert", "Cập nhật thông tin thành công");	
			}*/
			Part part=req.getPart("avatar");
			if(part.getSize()>0)
			{
				String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
				int index=filename.lastIndexOf(".");
				String ext=filename.substring(index+1);
				fname=System.currentTimeMillis()+"."+ext;
				System.out.print(uploadpath+"/"+fname);
				part.write(uploadpath+"/"+fname);
				userservice.updateavatar(username, fname);
				user.setAvatar(fname);
			}
			else
			{
				userservice.updateavatar(username, fileold);
				user.setFullname(fname);
			}
		} 
		
		catch (FileNotFoundException fne) {
			req.setAttribute("alert", "Có lỗi xảy ra" + fne.getMessage());
		}
		session.setAttribute("account", user);
		resp.sendRedirect(req.getContextPath() +"/update");
	}

}
