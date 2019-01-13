package cn.cdtu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.Product;
import cn.cdtu.beans.Report;
import cn.cdtu.beans.SamplePlan;
import cn.cdtu.beans.Standard;
import cn.cdtu.exceptions.ProductException;
import cn.cdtu.exceptions.StandardException;
import cn.cdtu.mappers.SamplePlanMapper;
import cn.cdtu.utils.JsonConverter;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@Service
public class SamplePlanService {
	@Autowired
	private SamplePlanMapper samplePlanMapper;
	@Autowired
	private StandardService standardService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ReportService reportService;
	
	/**
	 * 新增一条抽样计划
	 * @param samplePlan 新增的抽样计划信息
	 * @return 新增抽样计划信息的 dataTables JSON格式的数据
	 * @throws StandardException 要求执行的标准不存在
	 * @throws ProductException 要求检查的产品不存在
	 */
	public String insertSamplePlan(SamplePlan samplePlan)throws StandardException,ProductException{
		Product product = productService
				.findProductByName(samplePlan.getProduct().getName());
		if(product == null){
			throw new ProductException("该产品不存在");
		}else{
			List<SamplePlan> samplePlanList = findAllSamplePlanList();
			for(SamplePlan plan:samplePlanList){
				if(plan.getProduct().getName().equals(samplePlan.getProduct().getName())){
					throw new ProductException("该产品已经制定过抽查计划");
				}
			}
			
			samplePlan.setProduct(product);
		}
		
		Standard standard = standardService
				.findStandardByName(samplePlan.getStandard().getName());
		if(standard == null){
			throw new StandardException("该条标准不存在");
		}else{
			samplePlan.setStandard(standard);
		}
		
		samplePlanMapper.insertSamplePlan(samplePlan);
		
		return JsonConverter.dataTablesJsonFormat(samplePlan);
	}
	
	/**
	 * 
	 * @param id 待删除的抽样计划ID
	 * @return 控JSON字符串
	 */
	public String removeSamplePlanById(String id){
		List<Report> reportList = reportService.findAllReportList();
		
		if(reportList != null){
			for(Report report:reportList){
				if(report.getSamplePlan().getId().equals(id)){
					reportService.removeReportById(report.getId());
				}
			}
		}
		
		samplePlanMapper.removeSamplePlanById(id);
		return "{ }";
	}
	
	/**
	 * 删除所有的抽样计划
	 * @return 空JSON串
	 */
	public String removeAllSamplePlans(){
		//先清除所有的抽查报告
		reportService.removeAllReports();
		
		samplePlanMapper.removeAllSamplePlans();
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
	
	/**
	 * 
	 * @param samplePlan 待更新的抽样计划信息
	 * @return dataTables JSON格式的更新后的抽样计划
	 * @throws StandardException
	 * @throws ProductException
	 */
	public String updateSamplePlan(SamplePlan samplePlan)throws StandardException,ProductException{
		Product product = productService
				.findProductByName(samplePlan.getProduct().getName());
		if(product == null){
			throw new ProductException("该产品不存在");
		}else{
			samplePlan.setProduct(product);
		}
		
		Standard standard = standardService
				.findStandardByName(samplePlan.getStandard().getName());
		if(standard == null){
			throw new StandardException("该条标准不存在");
		}else{
			samplePlan.setStandard(standard);
		}
		
		samplePlanMapper.updateSamplePlan(samplePlan);
		
		return JsonConverter.dataTablesJsonFormat(samplePlan);
	}
	
	/**
	 * 
	 * @return 查询到的抽样信息集合的dataTables JSON格式的数据
	 */
	public String findAllSamplePlans(){
		List<SamplePlan> samplePlanList = samplePlanMapper.findAllSamplePlans();
		String jsonString = JsonConverter.EMPTY_JSON_STRING;
		
		if(samplePlanList != null){
			jsonString = JsonConverter.objectToJson(samplePlanList);
		}
		
		return jsonString;
	}
	
	/**
	 * 
	 * @return 以集合的形式返回所有的抽样计划信息
	 */
	public List<SamplePlan> findAllSamplePlanList(){
		return samplePlanMapper.findAllSamplePlans();
	}
	
	/**
	 * 
	 * @param id 指定抽样计划的唯一标识符
	 * @return 查询到的抽样计划
	 */
	public SamplePlan findSamplePlanById(String id){
		return samplePlanMapper.findSamplePlanById(id);
	}
}
