package cn.cdtu.utils;

import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Happy
 *
 * @since 2018年4月29日
 */
public class JsonConverter {
	private static Gson gson = new GsonBuilder()
			.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
			.setDateFormat("yyyy-MM-dd")
//			.setPrettyPrinting()
			.serializeNulls().create();
	
	public static final String EMPTY_JSON_STRING = "{ }";
	
	/**
	 * 将任意Java对象转换成JSON字符串
	 * @param object 指定的Object对象
	 * @return 转换后的JSON字符串
	 */
	public static String objectToJson(Object object){
		return gson.toJson(object);
	}
	
	/**
	 * 返回dataTables要求的JSON格式字符串
	 * @param standard 待变成dataTables格式的Standard对象
	 * @return 符合dataTables格式的JSON
	 */
	public static String dataTablesJsonFormat(Object object){
		return "{" +
			"\"data\":" + "["
					+ gson.toJson(object)
				+ "]"
			+ "}";
	}
	
	/**
	 * 将给定的键值转换值为String类型的JSON对象
	 * @param key 给定的键
	 * @param value 给定的值
	 * @return {"key":"value"}
	 */
	public static String kvJson(String key,String value){
		return "{"
				+ "\"" + key + "\"" +":"
				+ "\"" + value + "\""
				+"}";
	}
}
