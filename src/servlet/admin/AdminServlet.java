package servlet.admin;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.ClientService;
import org.apache.struts2.json.JSONUtil;

import bean.Admin;
import bean.Announcement;
import bean.Message;
import bean.MessageBoard;
import model.service.AdminService;
import model.service.AnnouncmentService;
import model.service.ClientArchiveService;
import model.service.DoctorService;
import model.service.MessageBoardService;
import model.service.MessageService;

/**
 * @author h w j
 * @instruction
 * 管理员端，首页，修改基本信息(包括密码)控制器
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminService adminService = new AdminService();
	
	DoctorService doctorService = new DoctorService();
	
	ClientService clientService = new ClientService();
	
	ClientArchiveService clientArchiveService = new ClientArchiveService();
	
	MessageBoardService messageBoardService = new MessageBoardService();
	
	MessageService messageService = new MessageService();
	
	AnnouncmentService announcmentService = new AnnouncmentService();
	
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");
		
		//当前登录用户
		Admin adminNow = (Admin)request.getSession().getAttribute(AdminLoginServlet.LOGIN_ADMIN);

		if("updatePwd".equals(m)){
			
			//修改密码
			
			String newPwd = request.getParameter("newPwd");
			
			String password = request.getParameter("password");
			
			String msg = "";
			if( adminNow.getAdminPwd().equals(password) ) {
				
				//成功
				adminService.updateAdminPwd(adminNow.getAdminId(),newPwd);
				
				msg = "{\"result\":\"true\",\"msg\":\"修改成功,请重新登录！\"}";
				
				request.getSession().removeAttribute(AdminLoginServlet.LOGIN_ADMIN);
				
				
			}else if(!adminNow.getAdminPwd().equals(password)){
				msg = "{\"result\":\"false\",\"msg\":\"修改失败，原始密码错误！\"}";
			}else {
				msg = "{\"result\":\"false\",\"msg\":\"修改失败，请重试！\"}";
			}
			
			
			response.setContentType("application/json; charset=utf-8");
			
			Writer writer = response.getWriter();
			
			writer.write(msg);
			
			writer.close();
			
			
		}else if("updateBase".equals(m)){
			
			//修改基本信息
			
			Admin admin = new Admin();
			admin.setName(request.getParameter("name"));
			admin.setSex( Integer.parseInt( request.getParameter("sex")));
			admin.setAge(Integer.parseInt(request.getParameter("age")));
			admin.setPhone(request.getParameter("phone"));
			admin.setEmail(request.getParameter("email"));
			
			
			//更新
			int i = adminService.updateAdminBase(admin,adminNow.getAdminId());
			
			Writer writer = response.getWriter();
			
			//设置响应类型
			response.setContentType("application/json; charset=utf-8");
			
			String msg = "";
			if(i > 0) {
				//成功
				msg = "{\"result\":\"true\",\"msg\":\"修改成功,请刷新！\"}";
				
				
				//重新在查询一遍管理员信息
				Admin adminNew = adminService.getAdmin(adminNow.getAdminName());
				
				request.getSession().setAttribute(AdminLoginServlet.LOGIN_ADMIN,adminNew);
				
			}else {
				//失败
				msg = "{\"result\":\"false\",\"msg\":\"修改失败，请刷新页面后重试\"}";
			}
			writer.write(msg);
			writer.close();
		
		}else if("adminIndex".equals(m)){
			
			//查数据（首页用到的）
			
			//查询共有多少咨询师
			int doctorNum = doctorService.getDoctorNum();
			
			request.setAttribute("doctorNum", doctorNum);
			
			//查询共有多少注册来访者
			int clientNum = clientService.getClientNum();
			
			request.setAttribute("clientNum", clientNum);
			
			//查询共有多少咨询记录
			int clientArchive = clientArchiveService.getClientArchiveNum();
			
			request.setAttribute("clientArchive", clientArchive);
			
			
			//查询所有显示的留言(最新的十条）
			ArrayList<MessageBoard> newMessageBoardList = messageBoardService.getMessageBoardNum(10);
			
			request.setAttribute("newMessageBoardList", newMessageBoardList);
			
			
			//查询所有未读的消息(最新的十条）
			ArrayList<Message> messageList = messageService.getMessageNum(10,adminNow.getAdminId(),"admin");
			
			request.setAttribute("messageList", messageList);

			
			//查询所有显示的公告(最新的十条)
			ArrayList<Announcement> announcmentList = announcmentService.getAnnouncmentNum(10); 
			
			request.setAttribute("announcmentList", announcmentList);
			  
			
			//转发到首页
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
			
		}else if("adminInfo".equals(m)) {
			
			//查看个人信息
			
			request.getRequestDispatcher("/admin/admin_info.jsp").forward(request, response);
		}
		
	
	}

}
