package com.heihe.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.domain.Region;
import com.heihe.domain.Subarea;
import com.heihe.service.SubareaService;
import com.heihe.utils.FileUtils;
import com.heihe.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Autowired
	private SubareaService subareaService;
	//使用属性驱动获得所有删除分区的id
	private String ids;
	// 添加分区
	public String addsubarea() {
		subareaService.save(model);
		return LIST;
	}
	
	// 修改分区
	public String editsubarea(){
		subareaService.editSubarea(model);
		return LIST;
	}
	
	// 删除分区
	public String delBatch(){
		subareaService.delBatch(ids);
		return LIST;
	}
	// 查询分区列表
	public String pageQuery() {
		//添加离线查询对象
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		// 动态添加离线对象
		String addresskey = model.getAddresskey();
		if (StringUtils.isNotBlank(addresskey)){
			// 根据地址模糊查询
			detachedCriteria.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		if (region != null){
			String province = region.getProvince(); // 省
			String city = region.getCity();
			String district = region.getDistrict();  // 区
			// 涉及到多表连接,需要为表添加别名
			// 属性一表示分区对象中关联区域对象的属性名
			// 属性二表示：别名，可以任意取
			detachedCriteria.createAlias("region", "r");
			if (StringUtils.isNotBlank(province)){
				detachedCriteria.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if (StringUtils.isNotBlank(city)){
				detachedCriteria.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if (StringUtils.isNotBlank(district)){
				detachedCriteria.add(Restrictions.like("r.district", "%"+district+"%"));
			}
			
		}
		subareaService.pageQuery(pageBean);
		this.java2json(pageBean, new String[] { "currentPage",
				"detachedCriteria", "pageSize", "subareas","decidedzone" });
		return null;
	}
	
	/**
	 * 导出分区数据
	 * @throws IOException 
	 */
	public String exportEls() throws IOException{
		// 查询所有分区数据
		List<Subarea> list = subareaService.findAll();
		//使用POI将数据写入Excel中
			// 在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		// 创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");
        
		// 把所有分区数据写入表格中
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
			
		}
		// 使用输出形式进行下载（一个流、两个头）
		String fileName = "分区数据.xls";
		// 根据文件呢名获得MIME类型
		String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);
		// 获得写出流
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		// 设置相应的MIME类型
		ServletActionContext.getResponse().setContentType(mimeType);
	    // 争对不同浏览器，对文件名进行编码
		FileUtils.encodeDownloadFilename(fileName, "User-Agent");
		// 设置响应头
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+fileName);
		workbook.write(outputStream);
		return null;
	}
	/**
	 * 查询没有被关联到定区的分区，返回json数据
	 */
	public String listajax(){
		List<Subarea> list = subareaService.findListNotAsscoiation();
		this.java2json(list, new String[]{"region","decidedzone","region","startnum","endnum","single"});
		return null;
	}
	// 使用属性驱动获得定区的id
	private String decidedzoneId;
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	/**
	 * 通过定区id查询所关联的分区
	 */
	public String findListByDecidedzone(){
		List<Subarea> list = subareaService.findListByDecidedzone(decidedzoneId);
		this.java2json(list, new String[]{"decidedzone","subareas"});
		return null;
	}
	
	/**
	 * 查询分区数据，用做区域分布图显示
	 */
	public String findSubareasGrupByProvince(){
		List<Object> list = subareaService.findSubareasGrupByProvince();
		// 返回的是json数组对象
		this.java2json(list, new String[]{});
		return null;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
}
