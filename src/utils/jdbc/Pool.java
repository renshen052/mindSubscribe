package utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * @author h w j
 * @instruction
 * 数据库连接池
 */
public class Pool {

	private static DataSource ds = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ds = BasicDataSourceFactory.createDataSource(PoolProperties.properties);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 从数据源中获取数据库连接  
	 * @return 连接
	 */
    public static Connection getConnection()throws SQLException{  

        return ds.getConnection();  

    }  



    /**
     * 释放连接 
     * @param conn 连接
     */
    public static void release(Connection conn){  


          if(conn!=null){  


            try{  


                //将Connection连接对象还给数据库连接池  

                conn.close();  


            }catch (Exception e) {  

                e.printStackTrace();  

            }  


      }  
	
    }
	
	
}
