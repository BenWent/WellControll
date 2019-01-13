package cn.cdtu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.Report;
import cn.cdtu.beans.SamplePlan;
import cn.cdtu.beans.UnqualifiedMessage;
import cn.cdtu.mappers.ReportMapper;
import cn.cdtu.utils.JsonConverter;
import cn.cdtu.utils.UUIDUtil;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@Service
public class ReportService {
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private SamplePlanService samplePlanService;

	/**
	 * 
	 * @return 报告集合的JSON字符串
	 */
	public String findAllReports() {
		// 数据库中已经存在的报告
		List<Report> reports = reportMapper.findAllReports();
		// 数据库中所有的抽样计划
		List<SamplePlan> planList = samplePlanService.findAllSamplePlanList();
		String jsonString = JsonConverter.EMPTY_JSON_STRING;

		if (planList != null) {
			List<Report> reportList = new ArrayList<>();
			Report report;

			for (SamplePlan plan : planList) {
				int i = 0;
				if (reports != null) {
					for (; i < reports.size(); i++) {
						Report r = reports.get(i);
						// 数据库中已经存在该报告，直接中数据库中取出，不必根据抽样计划生成
						if (r.getSamplePlan().getId().equals(plan.getId())) {
							reportList.add(r);
							break;
						}
					}
				}
				if (reports == null || i >= reports.size()) {
					// 报告是根据抽样计划生成的
					report = new Report();
					report.setId(UUIDUtil.getUUID());
					report.setSamplePlan(plan);

					reportList.add(report);
				}
			}
			jsonString = JsonConverter.objectToJson(reportList);
		}
		return jsonString;
	}

	/**
	 * 
	 * @return 查找到的报告集合
	 */
	public List<Report> findAllReportList() {
		return reportMapper.findAllReports();
	}

	/**
	 * 
	 * @param report
	 *            待更新的报告信息
	 * @return 更新后的报告的dataTables格式的JSON字符串
	 */
	public String updateReport(Report report) {
		//只有标识已提交的用户的信息才会更新
		if (findReportById(report.getId()) == null && report.getStatus() == '1') {
			// 当数据库中还没有该条报告信息的时候，先把该报告信息插入到数据库中
			insertReport(report);
		} 
//		else {
//			reportMapper.updateReport(report);
//		}
		report.setSamplePlan(samplePlanService.findSamplePlanById(report.getSamplePlan().getId()));

		report.setSamplePlan(samplePlanService.findSamplePlanById(report.getSamplePlan().getId()));
		return JsonConverter.dataTablesJsonFormat(report);
	}

	public Report findReportById(String id) {
		return reportMapper.findReportById(id);
	}

	/**
	 * 清除报告
	 * @return 空JSON串
	 */
	public String removeAllReports() {
		reportMapper.removeAllReports();
		
		return JsonConverter.EMPTY_JSON_STRING;
	}

	/**
	 * 
	 * @param id
	 *            待删除报告的ID
	 */
	public void removeReportById(String id) {
		reportMapper.removeReportById(id);
	}

	/**
	 * 
	 * @param report
	 *            待插入的新的报告
	 * @return 插入数据库后的报告的dataTables格式的JSON数据
	 */
	public String insertReport(Report report) {
		reportMapper.insertReport(report);

		return JsonConverter.dataTablesJsonFormat(report);
	}

	/**
	 * 
	 * @return 生产厂商及其不合格产品数量集合的JSON数据字符串
	 */
	public String findUnqualifiedMessagesByYear(String year) {
		List<UnqualifiedMessage> unqualifiedMessages = reportMapper.findUnqualifiedMessagesByYear(year);

		return JsonConverter.objectToJson(unqualifiedMessages);
	}
	
	/**
	 * 
	 * @return 检查年份 集合的JSON数据字符串
	 */
	public String countCheckDateYear(){
		return JsonConverter.objectToJson(reportMapper.countCheckDateYear());
	}
}
