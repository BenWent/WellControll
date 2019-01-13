package cn.cdtu.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.Manufacturer;
import cn.cdtu.beans.Product;
import cn.cdtu.beans.ProductType;
import cn.cdtu.beans.StoreHouse;
import cn.cdtu.mappers.ProductMapper;
import cn.cdtu.utils.JsonConverter;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@Transactional
@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ManufacturerService manufacturerService;
	@Autowired
	private StoreHouseService storeHouseService;
	
	/**
	 * 新建产品
	 * @param product 新建的产品信息
	 * @return 新建产品信息的dataTables JSON格式数据
	 */
	public String insertProduct(Product product){
		//产品类型名对应的产品类型
		ProductType type = productTypeService
				.findProductTypeByName(product.getProductType().getName());
		if(type != null){
			//该种产品类型已经存在
			product.setProductType(type);
		}else{
			//该种产品类型不存在
			productTypeService.insertProductType(product.getProductType());
		}
		
		Manufacturer manufacturer = manufacturerService
				.findManufacturerByName(product.getManufacturer().getName());
		if(manufacturer != null){
			product.setManufacturer(manufacturer);
		}else{
			manufacturerService.insertManufacturer(product.getManufacturer());
		}
		
		StoreHouse storeHouse = storeHouseService
				.findStoreHouseByName(product.getStoreHouse().getName());
		if(storeHouse != null){
			product.setStoreHouse(storeHouse);
		}else{
			storeHouseService.insertStoreHouse(product.getStoreHouse());
		}
		
		productMapper.insertProduct(product);
		
		return JsonConverter.dataTablesJsonFormat(product);
	}
	
	/**
	 * 
	 * @param product 待移出的产品信息
	 * @return 移出后的产品信息的dataTables JSON格式数据
	 */
	public String removeProductById(String id){
		Product product = findProductById(id);
		
		productMapper.removeProductById(id);
		
		if(productMapper.countProductTypeByName(product.getProductType().getName()) == 1){
			productTypeService.removeProductTypeById(product.getProductType().getId());
		}
		
		if(productMapper.countManufacturerByName(product.getManufacturer().getName()) == 1){
			manufacturerService.removeManufacturerById(product.getManufacturer().getId());
		}
		
		if(productMapper.countStoreHouseByName(product.getStoreHouse().getName()) == 1){
			storeHouseService.removeStoreHouseById(product.getStoreHouse().getId());
		}
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
	
	/**
	 * 清除所有的产品信息
	 * @return 空JSON串
	 */
	public String removeAllProducts(){
		productTypeService.removeAllProductTypes();
		storeHouseService.removeAllStoreHouses();
		
		productMapper.removeAllProducts();
		
		//移除与产品相关的厂家
		List<Product> productList = productMapper.findAllProducts();
		
		if(productList != null){
			for(Product product : productList){
				manufacturerService
					.removeManufacturerById(product.getManufacturer().getId());
			}
		}
		return JsonConverter.EMPTY_JSON_STRING;
	}
	
	/**
	 * 
	 * @param product 产品的更新信息
	 * @return 更新后产品的dataTables  JSON格式的数据
	 */
	public String updateProduct(Product product){
		ProductType type = productTypeService
				.findProductTypeByName(product.getProductType().getName());
		if(type != null){
			product.setProductType(type);
		}else{
			productTypeService.insertProductType(product.getProductType());
		}
		
		Manufacturer manufacturer = manufacturerService
				.findManufacturerByName(product.getManufacturer().getName());
		if(manufacturer != null){
			product.setManufacturer(manufacturer);
		}else{
			manufacturerService.insertManufacturer(product.getManufacturer());
		}
		
		StoreHouse storeHouse = storeHouseService
				.findStoreHouseByName(product.getStoreHouse().getName());
		if(storeHouse != null){
			product.setStoreHouse(storeHouse);
		}else{
			storeHouseService.insertStoreHouse(product.getStoreHouse());
		}
		
		productMapper.updateProduct(product);
		
		return JsonConverter.dataTablesJsonFormat(product);
	}
	
	/**
	 * 
	 * @return 查询到的产品集合的 dataTables JSON格式的数据
	 */
	public String findAllProducts(){
		String jsonString = JsonConverter.EMPTY_JSON_STRING;
		
		List<Product> productList = productMapper.findAllProducts();
		System.out.println(productList);
		if(productList != null){
			jsonString = JsonConverter.objectToJson(productList);
		}
		
		return jsonString;
	}
	
	/**
	 * 
	 * @param id 待查询产品的唯一标符号
	 * @return 查询到的产品信息
	 */
	public Product findProductById(String id){
		return productMapper.findProductById(id);
	}
	
	/**
	 * 
	 * @param name 待查询产品的名字
	 * @return 查询到的产品信息
	 */
	public Product findProductByName(String name){
		return productMapper.findProductByName(name);
	}
}
