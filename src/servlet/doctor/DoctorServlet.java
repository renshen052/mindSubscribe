package servlet.doctor;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONUtil;

import baen.Doctor;
import model.service.DoctorService;
import utils.ResultDate;

/**
 * Servlet implementation class DoctorServlet
 */
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DoctorService doctorService = new DoctorService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");
		
		if("listDoctor".equals(m)) {
			
			//接受查询条件
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String startAge = request.getParameter("startAge");
			String endAge = request.getParameter("endAge");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			//将条件封装到search中
			Map<String, String> search = new HashMap<String,String>();
			search.put("name", name);
			search.put("startAge", startAge);
			search.put("endAge", endAge);
			search.put("phone", phone);
			search.put("email", email);
			search.put("sex", sex);
			
			//查询符合条件的咨询师
			List<Doctor> list =  doctorService.listSearch(search);
			
			
			
			request.setAttribute("search", search);
			
			request.setAttribute("doctorList", list);
			
			request.getRequestDispatcher("/admin/doctorList.jsp").forward(request, response);
			
		}else if("updateActive".equals(m)){
			
			//停用和启用切换
			
			String doctorId = request.getParameter("id");
			
			String action = request.getParameter("action");
			
			
			doctorService.toggleDoctorActive(doctorId,action,response);
			
			
		}else if("deletDoctor".equals(m)) {
			
			//删除用户
			
			//获取要删除的doctor
			String doctorId = request.getParameter("id");
			
			//删除
			doctorService.deleteDoctor(doctorId,response);
			
		}
	
	
	}

}
