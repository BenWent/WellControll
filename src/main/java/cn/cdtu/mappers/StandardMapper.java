package cn.cdtu.mappers;

import java.util.List;

import cn.cdtu.beans.Standard;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
public interface StandardMapper {
	/**
	 * 根据指定的ID查找标准
	 * @param id 指定ID
	 * @return 数据库中存放的Standard信息，如果数据库没有该id指定的标准，则返回null
	 */
	public abstract Standard findStandardById(String id);
	
	/**
	 * 根据指定的name查找标准
	 * @param name 指定name
	 * @return 数据库中存放的Standard信息，如果数据库没有该id指定的标准，则返回null
	 */
	public abstract Standard findStandardByName(String name);
	
	/**
	 * 查询所有标准
	 * @return
	 */
	public abstract List<Standard> findAllStandards();
	
	/**
	 * 新增一条标准
	 * @param standard 新增的标准
	 * @return Standard的唯一标识符
	 */
	public abstract void insertStandard(Standard standard);
	
	/**
	 * 更新一条标准信息
	 * @param standard 需要更新的标准信息
	 */
	public abstract void updateStandard(Standard standard);
	
	/**
	 * 删除指定的标准信息
	 * @param id 待删除标准的唯一标识符
	 */
	public abstract void removeStandardById(String id);
	
	/**
	 * 删除所有的标准
	 */
	public abstract void removeAllStandards();
}
