package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cdtu.beans.Standard;
import cn.cdtu.services.StandardService;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
@Controller
@RequestMapping(value="/standard",method={RequestMethod.POST})
public class StandardController {
	@Autowired
	private StandardService standardService;
	
	@RequestMapping("/findAllStandards.action")
	public void findAllStandard(HttpServletResponse resp)throws Exception{
		System.out.println("查询访问");
		
		resp.getWriter().write(standardService.findAllStandardsJson());
	}
	
	@RequestMapping("/insertStandard.action")
	public void insertStandard(Standard standard,HttpServletResponse resp)throws Exception{
		System.out.println("插入访问");
		
		resp.getWriter().write(standardService.insertStandardJson(standard));
	}
	
	@RequestMapping("/updateStandard.action")
	public void updateStandard(Standard standard,String specification,HttpServletResponse resp)throws Exception{
		System.out.println("更新");
	
		resp.getWriter().write(standardService.updateStandard(standard));
	}
	
	@RequestMapping("/removeStandardById.action")
	public void removeStandardById(String id,HttpServletResponse resp)throws Exception{
		System.out.println("删除");
		
		resp.getWriter().write(standardService.removeStandardById(id));
	}
	
	@RequestMapping("/removeAllStandards.action")
	public void removeAllStandards(HttpServletResponse resp)throws IOException{
		System.out.println("清除");
		
//		resp.getWriter().write(standardService.removeAllStandards());;
	}
}
