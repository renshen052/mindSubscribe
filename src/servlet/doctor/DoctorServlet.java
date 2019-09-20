package servlet.doctor;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.sun.deploy.uitoolkit.impl.fx.Utils;

import baen.Doctor;
import model.service.DoctorService;
import utils.ResultDate;
import utils.UploadResult;
import utils.Util;

/**
 * Servlet implementation class DoctorServlet
 */
@MultipartConfig
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
			
			request.setAttribute("listSize", list.size());
			
			request.getRequestDispatcher("/admin/doctorList.jsp").forward(request, response);
			
		}else if("updateActive".equals(m)){
			
			//停用和启用切换
			
			String doctorId = request.getParameter("id");
			
			String action = request.getParameter("action");
			
			
			doctorService.toggleDoctorActive(doctorId,action,response);
			
			
		}else if("deletDoctor".equals(m)) {
			
			//删除
			
			//获取要删除的doctor
			String doctorId = request.getParameter("id");
			
			//批量删除的Id
			String checkeds = request.getParameter("checkeds");
			
			//删除单个的
			if(Util.isNotEmpty(doctorId)) {
				doctorService.deleteDoctor(Integer.parseInt(doctorId),response);
			}
			
			//批量删除
			if(Util.isNotEmpty(checkeds)) {
				doctorService.deleteDoctorCheckeds(checkeds,response);
			}
			
			
		}else if("selecteDoctor".equals(m)) {//ajax
			
			//查看详情
			
			//要查看的人
			String doctorId = request.getParameter("id");
			
			//查询，并且将数据返回（JSON格式）
			 doctorService.getDoctorByDoctorIdToResponse(Integer.parseInt(doctorId ),response);
			
			
			
		} else if("updateDoctor".equals(m)) {
			
			//增加或者修改
		
			//id有值就是修改，没值就是添加
			String doctorId = request.getParameter("id");
			
			
			//取得表单里的值
			String  name = request.getParameter("name");
			String  age = request.getParameter("age");
			String  sex = request.getParameter("sex");
			String  email = request.getParameter("email");
			String  phone = request.getParameter("phone");
			String  level = request.getParameter("level");
			String  place = request.getParameter("place");
			String  skill = request.getParameter("skill");
			String  isActive = request.getParameter("isActive");
			
			
			//传上来的个人照片
			
			UploadResult uploadResult = Util.upload("img",request,Util.UPLOAD_TYPE_IMG);
			
			//得到图片存放路径
			String imgPath = uploadResult.getLogicFileName();
			
			
			//将表单对象封装为Doctor对象
			Doctor doctor = new Doctor();
			doctor.setName(name);
			doctor.setAge(Integer.parseInt(age));
			doctor.setSex(Integer.parseInt(sex));
			doctor.setEmail(email);
			doctor.setPhone(phone);
			doctor.setLevel(level);
			doctor.setPlace(place);
			doctor.setSkill(skill);
			doctor.setIsActive(Integer.parseInt(isActive ));
			doctor.setImg(imgPath);
			
			
			int i = 0; //结果
			
			if(Util.isNotEmpty(doctorId)) {
				//id不为空，是修改
				doctor.setDoctorId(Integer.parseInt(doctorId ));
				
				//如果文件域大小为0，那么说明没有上传，
				if(uploadResult.getSize() == 0) {
					
					//图片还是原来的路径
					
					String  img = request.getParameter("imgPath"); //取得隐藏域的图片路径
					
					doctor.setImg(imgPath);
					
				}
				
				//修改
				i = doctorService.updateDoctor(doctor);
				
			}else {
				//增加
				i = doctorService.addDoctor(doctor);
			}
			
			//response.sendRedirect(request.getContextPath() + "/doctor/DoctorServlet?m=listDoctor");
			
			response.setContentType("application/json;charset=utf-8");
			
			ResultDate resultDate = new ResultDate();
			if(i == 1) {//成功
				
				resultDate.setIsSuccess(true);
				resultDate.setMsg("成功!");
				
				
			}else {//失败
				resultDate.setIsSuccess(true);
				resultDate.setMsg("失败，请刷新后重试!");
			}
			
			Writer writer = response.getWriter();
			
			try {
				JSONUtil.serialize(writer,resultDate);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//错误 指向了这里，，重新编译一下子
			writer.close();
			
		}
	
	}

}
