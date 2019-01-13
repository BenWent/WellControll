package cn.cdtu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.StoreHouse;
import cn.cdtu.mappers.StoreHouseMapper;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@Service
public class StoreHouseService {
	@Autowired
	private StoreHouseMapper storeHouseMapper;
	
	public void insertStoreHouse(StoreHouse storeHouse){
		storeHouseMapper.insertStoreHouse(storeHouse);
	}
	
	public StoreHouse findStoreHouseById(String id){
		return storeHouseMapper.findStoreHouseById(id);
	}
	
	public StoreHouse findStoreHouseByName(String name){
		return storeHouseMapper.findStoreHouseByName(name);
	}
	
	public void removeStoreHouseByName(String name){
		storeHouseMapper.removeStoreHouseByName(name);
	}
	
	public void removeStoreHouseById(String id){
		storeHouseMapper.removeStoreHouseByName(id);
	}
	
	public void removeAllStoreHouses(){
		storeHouseMapper.removeAllStoreHouses();
	}
}
