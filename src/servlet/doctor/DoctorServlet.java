package servlet.doctor;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

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
			
			//查询符合条件的咨询师
			
			List<Doctor> list =  doctorService.listAll();
			
			request.setAttribute("doctorList", list);
			
			request.getRequestDispatcher("/admin/doctorList.jsp").forward(request, response);
			
		}else if("updateActive".equals(m)){
			
			//停用和启用切换
			
			String doctorId = request.getParameter("id");
			
			String action = request.getParameter("action");
			
			
			doctorService.toggleDoctorActive(doctorId,action,response);
			
			
		}
	
	
	}

}
