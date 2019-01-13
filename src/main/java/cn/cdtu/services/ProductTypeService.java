package cn.cdtu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.ProductType;
import cn.cdtu.mappers.ProductTypeMapper;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@Service
public class ProductTypeService {
	@Autowired
	private ProductTypeMapper productTypeMapper;
	
	public void insertProductType(ProductType productType){
		productTypeMapper.insertProductType(productType);
	}
	
	public ProductType findProductTypeById(String id){
		return productTypeMapper.findProductTypeById(id);
	}
	
	public ProductType findProductTypeByName(String name){
		return productTypeMapper.findProductTypeByName(name);
	}
	
	public void removeProductTypeByName(String name){
		productTypeMapper.removeProductTypeByName(name);
	}
	
	public void removeProductTypeById(String id){
		productTypeMapper.removeProductTypeByName(id);
	}
	
	public void removeAllProductTypes(){
		productTypeMapper.removeAllProductTypes();
	}
}
