package utils.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 数据库连接池
 */
public class Pool {

    /** 
   * 在java中，编写数据库连接池需实现java.sql.DataSource接口，每一种数据库连接池都是DataSource接口的实现 
   * DBCP连接池就是java.sql.DataSource接口的一个具体实现 
   */  

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
	 * @return
	 */
    public static Connection getConnection()throws SQLException{  

        return ds.getConnection();  

    }  



    /**
     * //释放连接 
     * @param conn
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
