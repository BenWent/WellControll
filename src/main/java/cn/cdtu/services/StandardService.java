package cn.cdtu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.Standard;
import cn.cdtu.mappers.StandardMapper;
import cn.cdtu.utils.JsonConverter;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月28日
 */
@Transactional
@Service
public class StandardService {
	@Autowired
	private StandardMapper standardMapper;
	
	public Standard findStandardById(String id){
		return standardMapper.findStandardById(id);
	}
	
	public Standard findStandardByName(String name){
		return standardMapper.findStandardByName(name);
	}
	
	/**
	 * 查找所有的标准
	 * @return 以JSON的格式返回查到的所有标准
	 */
	public String findAllStandardsJson(){
		String jsonString = JsonConverter.EMPTY_JSON_STRING;
		List<Standard> standardList = standardMapper.findAllStandards();
		
		if(standardList != null){
			jsonString = JsonConverter.objectToJson(standardList);
		}
		
		return jsonString;
	}
	
	/**
	 * 插入一条新的标准
	 * @param standard 插入新标准的信息
	 * @return 以JSON格式形式返回插入的新标准
	 */
	public String insertStandardJson(Standard standard){
		standardMapper.insertStandard(standard);
		
		
		return JsonConverter.dataTablesJsonFormat(standard);
	}
	
	/**
	 * 更新一条标准
	 * @param standard 待更新标准的信息
	 * @return 以JSON格式返回更新后的标准
	 */
	public String updateStandard(Standard standard){
		standardMapper.updateStandard(standard);
		
		return JsonConverter.dataTablesJsonFormat(standard);
	}
	
	/**
	 * 删除一条标准
	 * @param id 待删除标准的ID
	 * @return 返回空JSON串
	 */
	public String removeStandardById(String id){
		standardMapper.removeStandardById(id);
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
	
	/**
	 * 删除所有的标准
	 * @return 空JSON串
	 */
	public String removeAllStandards(){
		standardMapper.removeAllStandards();
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
}
