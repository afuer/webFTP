package com.shaah.vouch_status.quartiz_sched.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import com.shaah.vouch_status.common.dtos.UserDto;
import com.shaah.vouch_status.common.utils.CommonUtils;
import com.shaah.vouch_status.quartiz_sched.baos.ConfigSchedBao;
import com.shaah.vouch_status.quartiz_sched.dtos.JobConfigDto;

/**
 * Servlet implementation class ConfigSchedServ
 */
@WebServlet("/configSchedServ")
public class ConfigSchedServ extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ConfigSchedServ.class);
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigSchedServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean res=false;
		HttpSession session = request.getSession();
		try {
		UserDto user = (UserDto)session.getAttribute(CommonUtils.USER_ATTR);
//		logger.info("user name is : "+user.getUserName());
		if(user!=null&&user.getId().compareTo(BigDecimal.ZERO)>0){
			

			String userName = user.getUserName();
		
			String jobName=  request.getParameter("jobName");
			String oldJobName=  request.getParameter("oldJob");
			
			String time2Run= request.getParameter("time").trim();
			String postFTPOpt=request.getParameter("postFTPAct").trim();
			
			String srcFtpServerURL = request.getParameter("srcFTP");
			String srcUserName = request.getParameter("srcuser");
			String srcPass = request.getParameter("srcPass");
			String source = request.getParameter("src");
			String bkupDir = request.getParameter("bkupDir");
			
			String distFtpServerURL = request.getParameter("distFTP");
			String distUserName = request.getParameter("distuser");
			String distPass = request.getParameter("distPass");
			String dist = request.getParameter("dist");
			
			
			int status=1;
			if(request.getParameter("save")!=null){
				status = CommonUtils.NT_STARTED_JOB_OPTION;
			}else if(request.getParameter("update")!=null){
				status = CommonUtils.UPDATE_JOB_STATUS_OPTION;
			}else if(request.getParameter("upNrun")!=null){
				status = CommonUtils.UPDATENRUN_JOB_STATUS_OPTION;
			}
//						Enumeration enumaa = request.getParameterNames();
//						String param=null;
//			while(enumaa.hasMoreElements()){
//			    param = enumaa.nextElement().toString();
//				logger.info(param+" with val : "+request.getParameter(param));
//				
//			}
			
//			logger.info(">>>>> "+request.getParameter("isFtps"));
			boolean srcsftp = request.getParameter("issrcSFtpen")!=null?true:false;
			boolean dstsftp = request.getParameter("isdstSFtpen")!=null?true:false;
			                                        
			
			logger.info(">>>>> this is a src ftps : "+srcsftp+" and dist ftps : "+dstsftp);
			
			
			JobConfigDto configObj = new JobConfigDto();
	//		configObj.setSatrtDate(satrtDate);
			configObj.setRunTime("0 "+time2Run+" ?");										
			configObj.setJobName(userName+"_"+jobName);
			configObj.setOldJobName(oldJobName);
			configObj.setUserId(user.getId().intValue());
			configObj.setPostFTPOpt(Integer.valueOf(postFTPOpt));
//			configObj.setGroupName(groupName);
			
			configObj.setSrcFtpServerURL(srcFtpServerURL);
			configObj.setSrcUserName(srcUserName);
			configObj.setSrcPass(srcPass);
			configObj.setSource(source);
			configObj.setSrcFtpsEnbl(srcsftp);
			configObj.setSrcBckupDir(bkupDir);
			
			
			configObj.setDistFtpServerURL(distFtpServerURL);
			configObj.setDistPass(distPass);
			configObj.setDistUserName(distUserName);
			configObj.setDist(dist);
			configObj.setStatus(status);
			configObj.setDistFtpsEnbl(dstsftp);
//			logger.info(" old job name is : "+oldJobName);
			
			ConfigSchedBao configBao = new ConfigSchedBao();
			Scheduler sched=null;
			
				sched = ((StdSchedulerFactory) request.getServletContext().getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY)).getScheduler();
			
			
				res = configBao.ConfigSched(configObj,sched);
		
			
		}
		
		if(res) {
			request.setAttribute("msg","succeed");
		}else{
			request.setAttribute("msg","failed !!");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/msg.jsp");
		dispatcher.forward(request,response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error happen when trying to Configure Schedule",e);
		}
	}

}
