package cn.cdtu.mappers;

import java.util.List;

import cn.cdtu.beans.Report;
import cn.cdtu.beans.UnqualifiedMessage;

/**
 * 
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
public interface ReportMapper {
	/**
	 * 新增报告
	 * @param report 待新增的报告信息
	 */
	public abstract void insertReport(Report report);
	
	/**
	 * 删除所有的报告
	 */
	public abstract void removeAllReports();
	
	/**
	 * 
	 * @param id 待删除报告的ID
	 */
	public abstract void removeReportById(String id);
	
	/**
	 * 
	 * @param report 待更新的报告
	 */
	public abstract void updateReport(Report report);
	
	/**
	 * 
	 * @return 查找到的报告集合
	 */
	public abstract List<Report> findAllReports();
	
	/**
	 * 
	 * @param id 待查找的报告ID
	 * @return 查找到的报告信息
	 */
	public abstract Report findReportById(String id);

	/**
	 * 统计报告中所有厂商不合格产品的数量
	 * @return
	 */
	public abstract List<UnqualifiedMessage> findUnqualifiedMessagesByYear(String year);

	/**
	 * 统计共有多少 检查年份
	 * @return 检查年份 集合
	 */
	public abstract List<String> countCheckDateYear();
}
