package utils.jdbc;
/**
 * @Description:    jdbc工具
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	private Connection conn;
	
	private PreparedStatement ps;
	
	private ResultSet rs;
	
	
	/**
	 * @description:  打开连接 
	 */
	public Connection connect(){
		
		try {
			//连接池的
			conn = Pool.getConnection();
			
			//普通jdbc
			//conn = DBUtils.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	/**
	 * @description: 创建Statement
	 * sql: select * from xx  where id = ? or name = ? ......
	 * 
	 */
	public PreparedStatement createStatement(String sql,Object...paramters){
		
		connect();
		
		//System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			
			
			if(paramters != null){
				
				for(int i = 0; i < paramters.length; i++){
					
					ps.setObject(i + 1, paramters[i]);
					
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	
	/**
	 * @description: 创建Statement，同时还会返回创建的记录主键
	 * sql: select * from xx  where id = ? or name = ? ......
	 * 
	 */
	public PreparedStatement createStatementGeneratedKeys(String sql,Object...paramters){
		
		connect();
		
		//System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			if(paramters != null){
				
				for(int i = 0; i < paramters.length; i++){
					
					ps.setObject(i + 1, paramters[i]);
					
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	
	
	
	
	
	
	/**
	 * @description:   执行dml(insert,update,delete)
	 */
	public int executeUpdate(String sql,Object...paramters){
		
		int num = 0;
		
		createStatement(sql,paramters);
		
		try {
			
			num = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			close();
			
		}
		
		return num;
	}
	
	
	/**
	 * 执行查询
	 */
	public ResultSet executeQuery(String sql,Object...paramters){
		
		createStatement(sql, paramters);
		
		try {
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	/**
	 * 关闭资源(rs,ps,conn)
	 */
	
	public void close(){
		
		try {
			
			if(rs != null){
				
				rs.close();
			}

			if(ps != null){
				
				ps.close();
			}
			
			if(conn != null){
				
				/*//普通数据库的关闭
				conn.close();*/
				
				//连接池连接释放
				Pool.release(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public Connection getConnection(){
		
		return this.conn;
	}
	

}
