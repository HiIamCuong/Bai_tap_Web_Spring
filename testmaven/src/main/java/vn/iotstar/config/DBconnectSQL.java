package vn.iotstar.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnectSQL {
	private final static String server="localhost";
	private final static String db="ltwebst4";
	private final static String port="1433";
	//private final static String instance="";
	
	
	String DB_URL ="jdbc:sqlserver://" + server + ":" + port + ";integratedSecurity=true;databaseName=" + db;
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con=DriverManager.getConnection(DB_URL);
		try
		{
			if(con!=null)
			{	
				
				DatabaseMetaData dm = con.getMetaData();
				System.out.println("Driver name: "+dm.getDriverName());
				System.out.println("Driver version: "+dm.getDriverVersion());
				System.out.println("Product name: "+dm.getDatabaseProductName());
				System.out.println("Product version: "+dm.getDatabaseProductVersion());
				
				return con;
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
//			try
//			{
//				if(con!=null && !con.isClosed())
//				{
//					con.close();
//				}
//			}
//			catch(SQLException ex)
//			{
//				ex.printStackTrace();
//			}
		}
		return con;
	}
	public static void main(String args[])
	{
		try
		{
			System.out.println(new DBconnectSQL().getConnection());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
