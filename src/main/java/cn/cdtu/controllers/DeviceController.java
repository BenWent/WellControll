package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cdtu.beans.Device;
import cn.cdtu.services.DeviceService;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月30日
 */
@RequestMapping(value="/device",method={RequestMethod.POST})
@Controller
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping("/findAllDevices.action")
	public void findAllDevices(HttpServletResponse resp)throws IOException{
		System.out.println("查询设备");
		
		resp.getWriter().write(deviceService.findAllDevices());
	}
	
	@RequestMapping("/insertDevice.action")
	public void insertDevice(Device device,HttpServletResponse resp)throws IOException{
		System.out.println("新增设备");
		System.out.println(device);
		resp.getWriter().write(deviceService.insertDevice(device));
	}
	
	@RequestMapping("/removeDeviceById.action")
	public void removeDeviceById(String id,HttpServletResponse resp)throws IOException{
		System.out.println("新增设备");
		
		resp.getWriter().write(deviceService.removeDeviceById(id));
	}
	
	@RequestMapping("/updateDevice.action")
	public void updateDevice(Device device,HttpServletResponse resp)throws IOException{
		System.out.println("更新设备");
		
		System.out.println(device);
		
		resp.getWriter().write(deviceService.updateDevice(device));
	}
	
	@RequestMapping("/removeAllDevices.action")
	public void removeAllDevices(HttpServletResponse resp)throws IOException{
		System.out.println("清除设备");
		
//		resp.getWriter().write(deviceService.removeAllDevices());
	}
}
