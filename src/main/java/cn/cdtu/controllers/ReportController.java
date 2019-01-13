package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cdtu.beans.Report;
import cn.cdtu.services.ReportService;

/**
 * 
 * @author Happy
 *
 * @since 2018年5月3日
 */
@RequestMapping(value="/report",method={RequestMethod.POST})
@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/findAllReports.action")
	public void findAllReports(HttpServletResponse resp)throws IOException{
		System.out.println("查询报告");
		
		resp.getWriter().write(reportService.findAllReports());
	}
	
	@RequestMapping("/updateReport.action")
	public void updateReport(Report report,HttpServletResponse resp)throws IOException{
		System.out.println("更新报告");
		
		resp.getWriter().write(reportService.updateReport(report));
	}
	
	@RequestMapping("/removeAllReports.action")
	public void removeAllReports(HttpServletResponse resp)throws IOException{
		System.out.println("清除报告");
		
		resp.getWriter().write(reportService.removeAllReports());;
	}
	
	@RequestMapping("/findUnqualifiedMessages.action")
	public void findUnqualifiedMessages(String year,HttpServletResponse resp)throws IOException{
		System.out.println("获取信息");
		
		resp.getWriter().write(reportService.findUnqualifiedMessagesByYear(year));
	}
	
	@RequestMapping("/countCheckDateYear.action")
	public void countCheckDateYear(HttpServletResponse resp)throws IOException{
		System.out.println("获取年份集合");
		System.out.println(reportService.countCheckDateYear());
		resp.getWriter().write(reportService.countCheckDateYear());
	}
}
