package vn.iotstar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.config.DBconnectSQL;
import vn.iotstar.models.Category;

public class CategoryDaoImpl implements CategoryDao {

	public Connection conn = null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	
	@Override
	public List<Category> findall() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM categories";
		List<Category> list=new ArrayList<>();
		try
		{
			conn=new DBconnectSQL().getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Category category = new Category();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category findbyid(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM categories WHERE categoryid = ?";
		try
		{
			conn=new DBconnectSQL().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Category category = new Category();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				return category;
			}
			conn.close();
			ps.close();
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO categories(categoryname,images,status) VALUES(?,?,?)";
		try
		{
			conn=new DBconnectSQL().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();
			conn.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		String sql="UPDATE categories SET categoryname=?,images=?,status=? WHERE categoryid=?";
		try
		{
			conn=new DBconnectSQL().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());
			ps.executeUpdate();
			conn.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM categories WHERE categoryid=?";
		try
		{
			conn=new DBconnectSQL().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			conn.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> findname(String keyword) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM categories WHERE categoryname LIKE ?";
		List<Category> list=new ArrayList<>();
		try
		{
			conn=new DBconnectSQL().getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs=ps.executeQuery();
			while(rs.next())
			{
				Category category = new Category();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
