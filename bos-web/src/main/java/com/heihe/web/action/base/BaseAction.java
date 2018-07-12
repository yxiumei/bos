package com.heihe.web.action.base;



import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.heihe.domain.Staff;
import com.heihe.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	public static String LIST="list";
    protected PageBean pageBean = new PageBean();
	// 创建离线提交查询对象
	DetachedCriteria detachedCriteria = null;
	  // 封装当前页，页面大小
	  public void setPage(Integer page) {
			pageBean.setCurrentPage(page);
		}
		public void setRows(Integer rows) {
			pageBean.setPageSize(rows);
		}
	    //模型对象
		protected T model;
		public T getModel() {
			return model;
		}
		
		// 将java对象转换成json字符串
		public void java2json(Object obj,String[] str){
			// 使用json-lib 将pageBean转换成json格式,写到页面
			// JSONObject  -----将单一对象转换成json
			// JSONArray  ------将数组，集合对象转换成json
			
			// 指定那些字段不用转换
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(str);
			
			String json = JSONObject.fromObject(obj,jsonConfig).toString();
			//写到页面
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			try {
				ServletActionContext.getResponse().getWriter().print(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	// 将java集合转换成json字符串
	public void java2json(List list, String[] str) {
		// 使用json-lib 将pageBean转换成json格式,写到页面
		// JSONObject -----将单一对象转换成json
		// JSONArray ------将数组，集合对象转换成json

		// 指定那些字段不用转换
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(str);

		String json = JSONArray.fromObject(list, jsonConfig).toString();
		// 写到页面
		ServletActionContext.getResponse().setContentType(
				"text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		//在构造方法中动态获取实体类型，通过反射创建model对象
		public BaseAction() {
			ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
			//获得BaseAction上声明的泛型数组
			Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
			Class<T> entityClass = (Class<T>) actualTypeArguments[0];
			// 封装分页查询时所用的离线查询对象
			detachedCriteria = DetachedCriteria.forClass(entityClass);
			pageBean.setDetachedCriteria(detachedCriteria);
			//通过反射创建对象
			try {
				model = entityClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}