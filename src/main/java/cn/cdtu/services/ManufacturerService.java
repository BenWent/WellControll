package cn.cdtu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.Manufacturer;
import cn.cdtu.mappers.ManufacturerMapper;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@Service
public class ManufacturerService {
	@Autowired
	private ManufacturerMapper manufacturerMapper;
	
	public void insertManufacturer(Manufacturer manufacturer){
		manufacturerMapper.insertManufacturer(manufacturer);
	}
	
	public Manufacturer findManufacturerById(String id){
		return manufacturerMapper.findManufacturerById(id);
	}
	
	public Manufacturer findManufacturerByName(String name){
		return manufacturerMapper.findManufacturerByName(name);
	}

	public void removeManufacturerById(String id){
		manufacturerMapper.removeManufacturerById(id);
	}
	
	public void removeManufacturerByName(String name){
		manufacturerMapper.removeManufacturerByName(name);
	}
}
