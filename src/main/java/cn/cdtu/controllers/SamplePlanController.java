package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cdtu.beans.SamplePlan;
import cn.cdtu.exceptions.ProductException;
import cn.cdtu.exceptions.StandardException;
import cn.cdtu.services.SamplePlanService;
import cn.cdtu.utils.JsonConverter;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@RequestMapping(value="/plan",method={RequestMethod.POST})
@Controller
public class SamplePlanController {
	@Autowired
	private SamplePlanService samplePlanService;

	@RequestMapping("/findAllSamplePlans.action")
	public void findAllSamplePlans(HttpServletResponse resp) throws IOException {
		System.out.println("查询抽查计划");

		resp.getWriter().write(samplePlanService.findAllSamplePlans());
	}

	@RequestMapping("/insertSamplePlan.action")
	public void insertSamplePlan(SamplePlan samplePlan, HttpServletResponse resp) throws IOException {
		System.out.println("新增抽查计划");

		try {
			String jsonString = samplePlanService.insertSamplePlan(samplePlan);
			resp.getWriter().write(jsonString);
		} catch (StandardException | ProductException e) {
			e.printStackTrace();
			resp.getWriter().write(e.getMessage());
		} 
	}

	@RequestMapping("/updateSamplePlan.action")
	public void updateSamplePlan(SamplePlan samplePlan,HttpServletResponse resp)throws IOException{
		System.out.println("更新抽查计划");
		System.out.println(samplePlan);
		try {
			String jsonString = samplePlanService.updateSamplePlan(samplePlan);
			resp.getWriter().write(jsonString);
		} catch (StandardException | ProductException e) {
			e.printStackTrace();
			
			resp.getWriter().write(JsonConverter.kvJson("errorMessage", e.getMessage()));
		}
	}
	
	@RequestMapping("/removeSamplePlanById.action")
	public void removeSamplePlanById(String id,HttpServletResponse resp)throws IOException{
		System.out.println("删除抽查计划");
		
		resp.getWriter().write(samplePlanService.removeSamplePlanById(id));
	}
	
	@RequestMapping("/removeAllSamplePlans.action")
	public void removeAllSamplePlans(HttpServletResponse resp)throws IOException{
		System.out.println("清除抽查计划");
		
//		resp.getWriter().write(samplePlanService.removeAllSamplePlans());;
	}
}
