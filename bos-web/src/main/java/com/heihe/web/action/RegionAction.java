package com.heihe.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.domain.Region;
import com.heihe.service.RegionService;
import com.heihe.utils.PageBean;
import com.heihe.utils.PinYin4jUtils;
import com.heihe.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	@Autowired
	private RegionService regionService;

	// 使用属性驱动获得文件对象
	private File uploadExl;
	// 使用属性驱动获得批量删除的is
	private String ids;
	// 文件上传，从Exl了中导入
	public String fileUpload() throws Exception{
		List<Region> list = new ArrayList<Region>();
		// 创建工作薄对象
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(uploadExl));
		// 获得第一个单元格
		HSSFSheet sheet = hssfWorkbook.getSheet("Sheet1");
		// 遍历每一行
		for (Row row : sheet) {
			int num = row.getRowNum();
			// 第一行数据是表格名不需要导入
			if (num == 0){
				continue;
			}
			// 获得每一个单元格的值
			String id = row.getCell(0).getStringCellValue();
			// 省
			String province = row.getCell(1).getStringCellValue();
			// 城市
			String city = row.getCell(2).getStringCellValue();
			// 区
			String district = row.getCell(3).getStringCellValue();
			// 邮编
			String postcode = row.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			// 封装shortcode 和 citycode
			province = province.substring(0, province.length() - 1);	
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province + city + district;
			String[] string = PinYin4jUtils.getHeadByString(info);
			String shortcode  = StringUtils.join(string);
			// 汉字转拼音
			String citycode  =PinYin4jUtils.hanziToPinyin(city, "");
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			list.add(region);
		}
		regionService.batchImportRegion(list);
		return null;
	}
	
	// 添加区域
	public String add(){
		// 封装shortcode 和 citycode
		String province = model.getProvince();
		String city = model.getCity();
		String district = model.getDistrict();
		String postcode = model.getPostcode();
		
		province = province.substring(0, province.length() - 1);
		city = city.substring(0, city.length() - 1);
		district = district.substring(0, district.length() - 1);
		String info = province + city + district;
		String[] string = PinYin4jUtils.getHeadByString(info);
		String shortcode  = StringUtils.join(string);
		// 汉字转拼音
		String citycode  =PinYin4jUtils.hanziToPinyin(city, "");
		model.setCitycode(citycode);
		model.setShortcode(shortcode);
		regionService.add(model);
		return LIST;
	}
	
	// 分页查询区域
	public String pageQuery() throws IOException{
		regionService.pageQuery(pageBean);
		// 调用封装方法将Java对象转成字符串
		java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
		return null;
	}
	
	
	// 属性驱动接收，是否检索新的数据
	private String q;

	/**
	 * ajax异步获得选择区域的name,用做下拉框选择
	 */
	public String ajaxGetList(){
		List<Region> list = null;
		if (StringUtils.isNotBlank(q)){
			list = regionService.queryListByQ(q);
		}else{
			list = regionService.ajaxGetList();
		}
		// 转成json对象回写到页面
		java2json(list,new String[]{"province","city","district","postcode","shortcode","citycode","subareas"});
		return null;
	}
	
	// 编辑区域
	public String editRegion(){
		regionService.updateRegion(model);
		return LIST;
	}
	
	// 批量删除定区
	public String delBatch(){
		if (StringUtils.isNotBlank(ids)){
			regionService.deleBatch(ids);	
		}
		return LIST;
	}
	
	

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setUploadExl(File uploadExl) {
		this.uploadExl = uploadExl;
	}
	public void setQ(String q) {
		this.q = q;
	}
}
