package cn.cdtu.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.cdtu.beans.Product;
import cn.cdtu.services.ProductService;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
@RequestMapping(value="/product",method={RequestMethod.POST})
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/findAllProducts.action")
	public void findAllProducts(HttpServletResponse resp)throws IOException{
		System.out.println("查询产品");
		
		resp.getWriter().write(productService.findAllProducts());
	}
	
	@RequestMapping("/updateProduct.action")
	public void updateProduct(Product product,HttpServletResponse resp)throws IOException{
		System.out.println("更新产品");
		
		resp.getWriter().write(productService.updateProduct(product));
	}
	
	@RequestMapping("/insertProduct.action")
	public void insertProduct(Product product,HttpServletResponse resp)throws IOException{
		System.out.println("新增产品");
		
		resp.getWriter().write(productService.insertProduct(product));
	}
	
	@RequestMapping("/removeProductById.action")
	public void removeProductById(String id,HttpServletResponse resp)throws IOException{
		System.out.println("删除产品");
		
		resp.getWriter().write(productService.removeProductById(id));
	}
	
	@RequestMapping("/removeAllProducts.action")
	public void removeAllProducts(HttpServletResponse resp)throws IOException{
		System.out.println("清除产品");
		
//		resp.getWriter().write(productService.removeAllProducts());;
	}
}
