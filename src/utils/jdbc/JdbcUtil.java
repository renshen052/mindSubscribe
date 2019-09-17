package utils.jdbc;
/**
 * @Description:    jdbc工具
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.Pager;

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
			//conn = Pool.getConnection();
			
			//普通jdbc
			conn = DBUtils.getConnection();
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
		
		System.out.println(sql);
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
		
		System.out.println(sql);
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
	 * 执行带翻页的查询
	 * @param sql 要执行的sql语句
	 * @param pageNo 目标页号
	 * @param pageCount 每页条数
	 * @param paramters 0-无数个参数，用于表示sql语句当中每个?对应的参数的值是多少
	 */
	
	public ResultSet executeQueryByPager(String sql,Pager pager,Object...paramters){
		
		//处理sql语句为带翻页的sql
		String selectSql = sql + " limit ?,?";
		
		//select * from t_teacher order by teacher_id where id=? and name =?  limit ?,?  
		
		createStatement(selectSql, paramters);
		
		int skipIndex = 1;
		
		if(paramters != null){
			
			skipIndex = paramters.length + 1;
		}
		
		try {
			
			//求出欲跳过的记录数
			int skip = getSkip(pager.getPageNo(), pager.getPageCount());
			
			//System.out.println(skip);
			//为skip和pageCount赋值
			ps.setInt(skipIndex,skip);
			ps.setInt(skipIndex + 1,pager.getPageCount());
			
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//查询总记录数
		String sqlCount = "select count(1) from (" + sql + ") pager";
		
		//执行sql语句
		JdbcUtil jdbcUtil = new JdbcUtil();
		ResultSet rs = null;
		try {
			rs = jdbcUtil.executeQuery(sqlCount,paramters);
			
			int totalCount = 0;//总记录数
			
			if(rs.next()){
				totalCount = rs.getInt(1);
			}
			
			//为pager对象设置总记录数
			pager.setTotalCount(totalCount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			jdbcUtil.close();
		}
		
		return this.rs;
		
	}
	
	
	/**
	 * 求skip的值
	 * @param pageNo 目标页号
	 * @param pageCount 每页条数
	 */
	public static int getSkip(int pageNo,int pageCount){
		
		
		return  (pageNo - 1) * pageCount;
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
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public Connection getConnection(){
		
		return this.conn;
	}
	

}
