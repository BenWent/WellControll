package cn.cdtu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cdtu.beans.Device;
import cn.cdtu.beans.Manufacturer;
import cn.cdtu.mappers.DeviceMapper;
import cn.cdtu.utils.JsonConverter;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月30日
 */
@Service
public class DeviceService {
	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private ManufacturerService manufacturerService;
	
	/**
	 * 查询所有的设备信息
	 * @return 设备集合的JSON字符串
	 */
	public String findAllDevices(){
		List<Device> deviceList = deviceMapper.findAllDevices();
		
		String jsonString = JsonConverter.EMPTY_JSON_STRING;
		
		if(deviceList != null){
			jsonString = JsonConverter.objectToJson(deviceList);
		}
		
		return jsonString;
	}
	
	/**
	 * 
	 * @param id 待查找设备的唯一标识符
	 * @return 查找到的设备
	 */
	public Device findDeviceById(String id){
		return deviceMapper.findDeviceById(id);
	}
	
	/**
	 * 
	 * @param device 待插入的新的设备
	 * @return 新设备的JSON字符串
	 */
	public String insertDevice(Device device){
		Manufacturer manufacturer = manufacturerService.
				findManufacturerByName(device.getManufacturer().getName());
		if(manufacturer != null){
			device.setManufacturer(manufacturer);
		}else{
			manufacturerService.insertManufacturer(device.getManufacturer());
		}
		
		deviceMapper.insertDevice(device);
		
		return JsonConverter.dataTablesJsonFormat(device);
	}
	
	/**
	 * 
	 * @param device 待更新的新的设备
	 * @return 更新后设备的JSON字符串
	 */
	public String updateDevice(Device device){
		Manufacturer manufacturer = manufacturerService.
				findManufacturerByName(device.getManufacturer().getName());
		if(manufacturer != null){
			device.setManufacturer(manufacturer);
		}else{
			manufacturerService.insertManufacturer(device.getManufacturer());
		}
		
		deviceMapper.updateDevice(device);
		
		return JsonConverter.dataTablesJsonFormat(device);
	}
	
	/**
	 * 
	 * @param id 待删除设备的唯一标识符
	 * @return 空JSON串
	 */
	public String removeDeviceById(String id){
		Device device = findDeviceById(id);
		//先删除device，后删除厂商，因为device外键约束与manufacturer
		deviceMapper.removeDeviceById(id);
		
		if(device != null){
			if(deviceMapper.countManufacturerByName(device.getManufacturer().getName()) == 1){
				manufacturerService.removeManufacturerById(device.getManufacturer().getId());
			}
		}
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
	
	/**
	 * 删除所有的设备
	 * @return 空JSON串
	 */
	public String removeAllDevices(){
		List<Device> deviceList = deviceMapper.findAllDevices();
		
		deviceMapper.removeAllDevices();
		
		if(deviceList != null){
			for(Device device:deviceList){
				manufacturerService.removeManufacturerById(device.getManufacturer().getId());
			}
		}
		
		return JsonConverter.EMPTY_JSON_STRING;
	}
}
