package cn.cdtu.mappers;

import java.util.List;

import cn.cdtu.beans.SamplePlan;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
public interface SamplePlanMapper {
	/**
	 * 新建一条抽查计划
	 * @param samplePlan 新建的抽查计划信息
	 */
	public abstract void insertSamplePlan(SamplePlan samplePlan);
	
	/**
	 * 
	 * @param id 待删除抽查计划的ID
	 */
	public abstract void removeSamplePlanById(String id);
	
	/**
	 * 删除所有的抽查计划
	 */
	public abstract void removeAllSamplePlans();
	
	/**
	 * 
	 * @param samplePlan 待更新的抽查计划
	 */
	public abstract void updateSamplePlan(SamplePlan samplePlan);
	
	/**
	 * 
	 * @param id 待查找的抽查计划的ID
	 * @return 查找到的抽查计划
	 */
	public abstract SamplePlan findSamplePlanById(String id);
	
	/**
	 * 
	 * @return 查询到的抽查计划集
	 */
	public abstract List<SamplePlan> findAllSamplePlans();
}
