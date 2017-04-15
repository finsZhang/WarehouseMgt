package com.zx.whm.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * excel导出工具
 * @author yong
 *
 */
public class ExportExcelUtil {
	private static final DecimalFormat format = new DecimalFormat("####,###,###,###,###,##0.00");

	private static HSSFWorkbook wb = null;
	private static HSSFSheet sheet = null;//Excel工作表对象
	private static  HSSFCellStyle  HEADER_STYLE;
	private static  HSSFCellStyle  CONTENT_STYLE;
	private static  HSSFCellStyle  TITLE_STYLE;
	private static  HSSFCellStyle  DEFAULT_STYLE;
	private static  HSSFCellStyle  CONTENT_STYLE_FLOAT;

	private void initStyle(){
		//===== 创建单元格样式====
		HEADER_STYLE = wb.createCellStyle();
		HSSFFont headerFont = wb.createFont();
		HEADER_STYLE.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		HEADER_STYLE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		HEADER_STYLE.setWrapText(true);// 指定单元格自动换行
		HEADER_STYLE.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		HEADER_STYLE.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontName("宋体");
		headerFont.setFontHeight((short)220);
		HEADER_STYLE.setFont(headerFont);



		CONTENT_STYLE = wb.createCellStyle();
		HSSFFont contentFont = wb.createFont();
		CONTENT_STYLE.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		CONTENT_STYLE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		CONTENT_STYLE.setWrapText(true);
		// 设置单元格字体
		contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentFont.setFontName("宋体");
		contentFont.setFontHeight((short) 200);
		CONTENT_STYLE.setFont(contentFont);

		TITLE_STYLE = wb.createCellStyle();
		HSSFFont titleFont = wb.createFont();
		TITLE_STYLE.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		TITLE_STYLE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		TITLE_STYLE.setWrapText(true);// 指定单元格自动换行
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontName("宋体");
		titleFont.setFontHeight((short) 300);
		TITLE_STYLE.setFont(titleFont);

		DEFAULT_STYLE = wb.createCellStyle();
		HSSFFont defaultFont = wb.createFont();
		DEFAULT_STYLE.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		DEFAULT_STYLE.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		DEFAULT_STYLE.setWrapText(true);// 指定单元格自动换行
		defaultFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		defaultFont.setFontName("宋体");
		defaultFont.setFontHeight((short) 300);
		DEFAULT_STYLE.setFont(defaultFont);

		CONTENT_STYLE_FLOAT = wb.createCellStyle();
		HSSFFont contentFloatFont = wb.createFont();
		CONTENT_STYLE_FLOAT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		CONTENT_STYLE_FLOAT.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		CONTENT_STYLE_FLOAT.setWrapText(true);
		// 设置单元格字体
		contentFloatFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		contentFloatFont.setFontName("宋体");
		contentFloatFont.setFontHeight((short) 200);
		CONTENT_STYLE_FLOAT.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		CONTENT_STYLE_FLOAT.setFont(contentFloatFont);
	}

	public ExportExcelUtil() {
		super();
		wb = new HSSFWorkbook();//文档对象
		sheet = wb.createSheet();//创建Excel工作表对象
		initStyle();
	}

	/**
	 * @return the sheet
	 */
	public HSSFSheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet
	 *            the sheet to set
	 */
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the wb
	 */
	public HSSFWorkbook getWb() {
		return wb;
	}

	/**
	 * @param wb
	 *            the wb to set
	 */
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	/**
	 * 创建通用EXCEL头部
	 * @param rowStart 开始列
	 * @param titleName 标题头
	 * @param colSum 总列数
	 */
	public void createTitle(int rowStart, String titleName,int colSum,HSSFCellStyle cellStyle) {

		HSSFRow row = sheet.createRow(rowStart);//创建Excel工作表的行
		// 设置第一列
		HSSFCell cell = row.createCell(0);
		row.setHeight((short) 400);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		// 指定合并区域
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (colSum-1)));//合并从第0行0列到0行（colsum-1）列的区域
		cell.setCellStyle(cellStyle);
		cell.setCellValue(new HSSFRichTextString(titleName));//设置Excel工作表的值
	}

	/**
	 * 创建通用EXCEL头部
	 * @param rowStart 开始列
	 * @param titleName 标题头
	 * @param colSum 总列数
	 */
	public void createTwoTitle(int rowStart, String titleName,Object[] codes, int colSum,HSSFCellStyle cellStyle) {
		HSSFRow row = sheet.createRow(rowStart);//创建Excel工作表的行
		// 设置第一列
		HSSFCell cell = row.createCell(0);
		row.setHeight((short) 400);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		// 指定合并区域
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (colSum-1)));//合并从第0行0列到0行（colsum-1）列的区域
		cell.setCellStyle(cellStyle);
		cell.setCellValue(new HSSFRichTextString(titleName));//设置Excel工作表的值



		HSSFRow row2 = sheet.createRow(rowStart+1);//创建Excel工作表的行
		// 设置第一列
		HSSFCell cell3 = row2.createCell(0);
		row2.setHeight((short) 400);

		for (int i = 0; i < codes.length; i++) {
			cell3 = row2.createCell(i);
			cell3.setCellType(HSSFCell.ENCODING_UTF_16);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(new HSSFRichTextString((String) codes[i]));
		}
	}

	/**
	 * 创建通用EXCEL头部
	 * @param rowStart 开始列
	 * @param titleName 标题头
	 * @param colSum 总列数
	 */
	public void createAnotherTitle(int rowStart, String titleName,int colSum,HSSFCellStyle cellStyle) {

		HSSFRow row = sheet.createRow(rowStart);//创建Excel工作表的行
		// 设置第rowStart列
		HSSFCell cell = row.createCell(0);
		row.setHeight((short) 400);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		// 指定合并区域
		sheet.addMergedRegion(new Region(rowStart, (short) 0, rowStart, (short) (colSum-1)));//合并从第0行0列到0行（colsum-1）列的区域
		cell.setCellStyle(cellStyle);
		cell.setCellValue(new HSSFRichTextString(titleName));//设置Excel工作表的值
	}


	/**
	 * 创建通用报表第二行
	 *
	 * @param params
	 *            统计条件数组
	 * @param colSum
	 *            需要合并到的列索引
	 */
	public void createNormalTwoRow(String[] params, int colSum) {
		HSSFRow row1 = sheet.createRow(1);
		row1.setHeight((short) 300);

		HSSFCell cell2 = row1.createCell(0);//创建了单元格

		cell2.setCellType(HSSFCell.ENCODING_UTF_16);
		cell2.setCellValue(new HSSFRichTextString("统计时间：" + params[0] + "至"
				+ params[1]));

		// 指定合并区域
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);

		cell2.setCellStyle(cellStyle);

	}

	/**
	 * 设置报表列标题
	 * @param startRow 开始行
	 * @param columHeader  列标题字符串数组
	 */
	public void createHeader(int startRow,String[] columHeader,HSSFCellStyle cellStyle) {

		// 设置列头
		HSSFRow row2 = sheet.createRow(startRow);//创建Excel工作表的行
		// 指定行高
		row2.setHeight((short) 600);

		//设置样式

		HSSFCell cell3 = null;
		for (int i = 0; i < columHeader.length; i++) {
			cell3 = row2.createCell(i);
			cell3.setCellType(HSSFCell.ENCODING_UTF_16);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(new HSSFRichTextString(columHeader[i]));
		}

	}

	/**
	 * 创建内容单元格
	 * @param row 行
	 * @param col 列索引
	 * @param val 值对象
	 */
	public void cteateCell(HSSFRow row,boolean isInt,boolean isFloat,int col,Object val,HSSFCellStyle cellStyle) {

		HSSFCell cell = row.createCell(col);
		String value = val==null?"0":val.toString().replaceAll(",","");
		if(isInt){
			cell.setCellValue(Integer.parseInt(value));
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(cellStyle);
		}else if(isFloat){
			cell.setCellValue(new BigDecimal(value).floatValue());
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellStyle(CONTENT_STYLE_FLOAT);
		}else{
			cell.setCellStyle(cellStyle);
			if(val instanceof  Date){
				cell.setCellValue(new HSSFRichTextString(val == null ? "" : DateUtils.formatDate(val,"yyyy-MM-dd HH:mm:ss")));
			}else {
				cell.setCellValue(new HSSFRichTextString(val == null ? "" : val.toString()));
			}
			cell.setCellType(HSSFCell.ENCODING_UTF_16);
		}
	}

	/**
	 * 创建数据体
	 * @param startRow 开始行
	 * @param headColumn 头部标题
	 * @param contents 内容列表
	 * @throws Exception
	 */
	public <T> void createContentBody(int startRow,String[] headColumn,List<T> contents,HSSFCellStyle cellStyle) throws Exception{
		// 循环创建中间的单元格的各项的值
		Object value=null;
		for (int i =startRow; i < contents.size()+startRow; i++) {
			HSSFRow row = sheet.createRow((short) i);
			for (int j =0; j <headColumn.length; j++) {
				String fieldName=headColumn[j];
				Method getMethod= contents.get(i-startRow).getClass().getMethod(BeanUtils.getGetMethodNameFromFiledName(fieldName));
				value = getMethod.invoke(contents.get(i-startRow), null);
				this.cteateCell(row,false,false, j,value,cellStyle);
				//设置列的宽度
				sheet.setColumnWidth(j, 20 * 256);
			}
		}
	}


	/**
	 * 创建数据体
	 * @param sumColum 合计字段
	 * @param startRow 开始行
	 * @param headColumn 头部标题
	 * @param contents 内容列表
	 * @throws Exception
	 */
	public void createBody(int startRow,String[] headColumn,String[] sumColum,List<Object> contents,HSSFCellStyle cellStyle,String[] intCol,String...floatCol) throws Exception{

		// 循环创建中间的单元格的各项的值
		Object value=null;
		Map<Integer,BigDecimal> map=new HashMap<Integer,BigDecimal>();
		boolean sumflag=false;
		boolean floatFlag = false;
		boolean intFlag = false;
		for (int i =startRow; i < contents.size()+startRow; i++) {
			HSSFRow row = sheet.createRow((short) i);
			for (int j =0; j <headColumn.length; j++) {
				String fieldName=headColumn[j];
				if(sumColum!=null&&sumColum.length!=0) {
					if (Arrays.asList(sumColum).contains(fieldName)) {
						sumflag = true;
					}
				}
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						floatFlag = true;
					}
				}

				if(intCol!=null&&intCol.length!=0) {
					if (Arrays.asList(intCol).contains(fieldName)) {
						intFlag = true;
					}
				}

				String mname=BeanUtils.getGetMethodNameFromFiledName(fieldName);
				Method getMethod= contents.get(i-startRow).getClass().getMethod(mname);
				value = getMethod.invoke(contents.get(i-startRow), null);
				if(sumflag){
					if(map.get(j)==null){
						map.put(j,new BigDecimal(String.valueOf(value)));
					}else{
						map.put(j,map.get(j).add(new BigDecimal(String.valueOf(value))));
					}
				}
				if(floatFlag){
					this.cteateCell(row,false,true,j,format.format(value),cellStyle);
				}else if(intFlag){
					this.cteateCell(row,true,false,j,value,cellStyle);
				} else{
					this.cteateCell(row,false,false,j, value, cellStyle);
				}
				//设置列的宽度
				sheet.setColumnWidth(j, 20 * 256);
				sumflag=false;
				floatFlag= false;
				intFlag = false;
			}
		}
		if(sumColum!=null&&sumColum.length!=0){
			HSSFRow row = sheet.createRow((short)contents.size()+startRow);
			this.cteateCell(row,false,false,0, "合计", cellStyle);
			String fieldName;
			for (int j =1; j <headColumn.length; j++) {
				fieldName=headColumn[j];
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						this.cteateCell(row,false,false, j, map.get(j)==null?"":format.format(map.get(j)), cellStyle);
						continue;
					}
				}
				this.cteateCell(row,false,false, j, map.get(j)==null?"":map.get(j), cellStyle);
			}
		}
	}

	/**
	 * 创建数据体(双表格形式)
	 * @param sumColum 合计字段
	 * @param startRow 开始行
	 * @param headColumn 头部标题
	 * @param contents 内容列表
	 * @throws Exception
	 */
	public <T> void createTwoBody(int startRow,String[] headColumn,String[] sumColum,List<Object> contents,HSSFCellStyle cellStyle,String nTitle,String[] nHeadTitle,String[] nHeadColumn,List<T> contentBody,String[] intCol,String...floatCol) throws Exception{

		// 循环创建中间的单元格的各项的值
		Object value=null;
		Map<Integer,BigDecimal> map=new HashMap<Integer,BigDecimal>();
		boolean sumflag=false;
		boolean floatFlag = false;
		boolean intFlag = false;
		int num = 0;
		for (int i =startRow; i < contents.size()+startRow; i++) {
			HSSFRow row = sheet.createRow((short) i);
			for (int j =0; j <headColumn.length; j++) {
				String fieldName=headColumn[j];
				if(sumColum!=null&&sumColum.length!=0) {
					if (Arrays.asList(sumColum).contains(fieldName)) {
						sumflag = true;
					}
				}
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						floatFlag = true;
					}
				}

				if(intCol!=null&&intCol.length!=0) {
					if (Arrays.asList(intCol).contains(fieldName)) {
						intFlag = true;
					}
				}


				String mname=BeanUtils.getGetMethodNameFromFiledName(fieldName);
				Method getMethod= contents.get(i-startRow).getClass().getMethod(mname);
				value = getMethod.invoke(contents.get(i-startRow), null);
				if(sumflag){
					if(map.get(j)==null){
						map.put(j,new BigDecimal(String.valueOf(value)));
					}else{
						map.put(j,map.get(j).add(new BigDecimal(String.valueOf(value))));
					}
				}
				if(floatFlag){
					this.cteateCell(row,false,true, j,format.format(value),cellStyle);
				}else if(intFlag){
					this.cteateCell(row,true,false,j,value,cellStyle);
				}else {
					this.cteateCell(row,false,false,j, value, cellStyle);
				}
				//设置列的宽度
				sheet.setColumnWidth(j, 20 * 256);
				sumflag=false;
				floatFlag= false;
				intFlag = false;
			}
			num = i;
		}
		if(sumColum!=null&&sumColum.length!=0){
			HSSFRow row = sheet.createRow((short)contents.size()+startRow);
			this.cteateCell(row,false,false, 0, "合计", cellStyle);
			String fieldName;
			for (int j =1; j <headColumn.length; j++) {
				fieldName=headColumn[j];
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						this.cteateCell(row,false,true, j, map.get(j)==null?"":format.format(map.get(j)), cellStyle);
						continue;
					}
				}
				this.cteateCell(row,false,false, j, map.get(j)==null?"":map.get(j), cellStyle);
			}
		}
		num += 4;
		createAnotherTitle(num,nTitle,nHeadColumn.length,ExportExcelUtil.TITLE_STYLE);
		createHeader(num+1,nHeadTitle,ExportExcelUtil.HEADER_STYLE);
		createContentBody(num+2,nHeadColumn,contentBody,ExportExcelUtil.CONTENT_STYLE);

	}

	/**
	 * 创建数据体(双表格形式)
	 * @param startRow 开始行
	 * @param headColumn 头部标题
	 * @param contents 内容列表
	 * @throws Exception
	 */
	public <T> void createTwoBody(int startRow,String[] headColumn,List<Object> contents,HSSFCellStyle cellStyle,String[] nHeadTitle,String[] nHeadColumn,List<T> contentBody,String[] intCol,String...floatCol) throws Exception{
		// 循环创建中间的单元格的各项的值
		Object value=null;
		Map<Integer,BigDecimal> map=new HashMap<Integer,BigDecimal>();
		boolean floatFlag = false;
		boolean intFlag = false;
		int num = 0;
		for (int i =startRow; i < contents.size()+startRow; i++) {
			HSSFRow row = sheet.createRow((short) i);
			for (int j =0; j <headColumn.length; j++) {
				String fieldName=headColumn[j];
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						floatFlag = true;
					}
				}

				if(intCol!=null&&intCol.length!=0) {
					if (Arrays.asList(intCol).contains(fieldName)) {
						intFlag = true;
					}
				}


				String mname=BeanUtils.getGetMethodNameFromFiledName(fieldName);
				Method getMethod= contents.get(i-startRow).getClass().getMethod(mname);
				value = getMethod.invoke(contents.get(i-startRow), null);

				if(floatFlag){
					this.cteateCell(row,false,true,j,format.format(value),cellStyle);
				}else if(intFlag){
					this.cteateCell(row,true,false, j, value, cellStyle);
				}else{
					this.cteateCell(row,false,false, j, value, cellStyle);
				}

				//设置列的宽度
				sheet.setColumnWidth(j, 20 * 256);
				floatFlag= false;
				intFlag = false;
			}
			num = i;
		}
		num += 2;
		//createAnotherTitle(num,nTitle,nHeadColumn.length,ExportExcelUtil.TITLE_STYLE);
//		createHeader(num+1,nHeadTitle,ExportExcelUtil.TITLE_STYLE);
		createContentBody(num,nHeadColumn,contentBody,ExportExcelUtil.CONTENT_STYLE);
	}

	/**
	 * 创建合计行
	 *
	 * @param colSum
	 *            需要合并到的列索引
	 * @param cellValue
	 */
	public void createLastSumRow(int colSum, String[] cellValue) {

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);

		HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
		HSSFCell sumCell = lastRow.createCell(0);

		sumCell.setCellValue(new HSSFRichTextString("合计"));
		sumCell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,
				sheet.getLastRowNum(), (short) colSum));// 指定合并区域

		for (int i = 2; i < (cellValue.length + 2); i++) {
			sumCell = lastRow.createCell(i);
			sumCell.setCellStyle(cellStyle);
			sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));

		}

	}

	/**
	 * 输入EXCEL文件
	 *
	 * @param fileName
	 *            文件名
	 */
	public void outputExcel(String fileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(fileName));
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出表到浏览器
	 * @param title 大标题
	 * @param headTitle 头部列标题
	 * @param headColumn 列字段名
	 * @param contentBody 内容体
	 * @param response
     * @throws Exception
     */
	public void exportExcelForWeb(String title,String[] headTitle,String[] headColumn, List<Object> contentBody,HttpServletResponse response) throws Exception{
		ExportExcelUtil exportExcel = new ExportExcelUtil();
		exportExcel.createTitle(0,title,headTitle.length,ExportExcelUtil.TITLE_STYLE);
		exportExcel.createHeader(1,headTitle,ExportExcelUtil.HEADER_STYLE);
		exportExcel.createContentBody(2,headColumn,contentBody,ExportExcelUtil.CONTENT_STYLE);
		//exportExcel.outputExcel("e:\\cc.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		title = URLEncoder.encode(title, "utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + title + ".xls\"");
		OutputStream  os=response.getOutputStream();
		exportExcel.wb.write(os);
		os.flush();
		os.close();

	}

	//带合计统计
	public void exportExcelForTotal(String title,String[] headTitle,String[] headColumn,String[] sumColum,List<Object> contentBody,HttpServletResponse response,String[] intCol,String...floatCol) throws Exception{
		ExportExcelUtil exportExcel = new ExportExcelUtil();
		exportExcel.createTitle(0,title,headTitle.length,ExportExcelUtil.TITLE_STYLE);
		exportExcel.createHeader(1,headTitle,ExportExcelUtil.HEADER_STYLE);
		exportExcel.createBody(2,headColumn,sumColum,contentBody,ExportExcelUtil.CONTENT_STYLE,intCol,floatCol);
		//exportExcel.outputExcel("e:\\cc.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		title = URLEncoder.encode(title, "utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + title + ".xls\"");
		OutputStream  os=response.getOutputStream();
		exportExcel.wb.write(os);
		os.flush();
		os.close();

	}

	//带合计统计（表头有统计数列的）
	public void exportExcelForTotalTwo(String title,String[] headTitle,Object[] codes,String[] headColumn,String[] sumColum,List<Object> contentBody,HttpServletResponse response,String[] intCol,String...floatCol) throws Exception{
		ExportExcelUtil exportExcel = new ExportExcelUtil();
		exportExcel.createTwoTitle(0,title,codes,headTitle.length,ExportExcelUtil.TITLE_STYLE);
		exportExcel.createHeader(2,headTitle,ExportExcelUtil.HEADER_STYLE);
		exportExcel.createBody(3,headColumn,sumColum,contentBody,ExportExcelUtil.CONTENT_STYLE,intCol,floatCol);
		//exportExcel.outputExcel("e:\\cc.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		title = URLEncoder.encode(title, "utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + title + ".xls\"");
		OutputStream  os=response.getOutputStream();
		exportExcel.wb.write(os);
		os.flush();
		os.close();

	}


	//带合计统计(两个表)
	public <T> void exportTwoExcelForTotal(String title,String[] headTitle,String[] headColumn,String[] sumColum,List<Object> contentBody,HttpServletResponse response,String nTitle,String[] nHeadTitle,String[] nHeadColumn,List<T> nContentBody,String[] intCol, String...floatCol) throws Exception{
		ExportExcelUtil exportExcel = new ExportExcelUtil();
		exportExcel.createTitle(0,title,headTitle.length,ExportExcelUtil.TITLE_STYLE);
//		exportExcel.createTitle(1,title,headTitle.length,ExportExcelUtil.TITLE_STYLE);
		exportExcel.createHeader(1,headTitle,ExportExcelUtil.HEADER_STYLE);
		exportExcel.createTwoBody(2,headColumn,sumColum,contentBody,ExportExcelUtil.CONTENT_STYLE,nTitle,nHeadTitle,nHeadColumn,nContentBody,intCol,floatCol);

		//exportExcel.outputExcel("e:\\cc.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		title = URLEncoder.encode(title, "utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + title + ".xls\"");
//		nTitle = URLEncoder.encode(nTitle, "utf-8");
//		response.addHeader("Content-Disposition", "attachment; filename=\"" + nTitle + ".xls\"");
		OutputStream  os=response.getOutputStream();
		exportExcel.wb.write(os);
		os.flush();
		os.close();

	}

	//创建一个边框
	public void createBorder(){
		HSSFCellStyle setBorder = wb.createCellStyle();
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_DASH_DOT);
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_DASH_DOT);
		setBorder.setBorderRight(HSSFCellStyle.BORDER_DASH_DOT);
		setBorder.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT);

	}

	//带合计统计(两个表)
	public <T> void exportTwoExcel(String title,String[] headTitle,String[] headColumn,List<Object> contentBody,HttpServletResponse response,String[] nHeadTitle,String[] nHeadColumn,List<T> nContentBody, String[] intCol,String...floatCol) throws Exception{
		ExportExcelUtil exportExcel = new ExportExcelUtil();
		exportExcel.createTitle(0,title,headTitle.length,ExportExcelUtil.TITLE_STYLE);
//		exportExcel.createTitle(1,title,headTitle.length,ExportExcelUtil.TITLE_STYLE);
		exportExcel.createHeader(1,headTitle,ExportExcelUtil.HEADER_STYLE);
		exportExcel.createTwoBody(2,headColumn,contentBody,ExportExcelUtil.CONTENT_STYLE,nHeadTitle,nHeadColumn,nContentBody,intCol,floatCol);
		//exportExcel.createBorder(0,0,headTitle.length,8);
		//exportExcel.createBorder();

		//exportExcel.outputExcel("e:\\cc.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		title = URLEncoder.encode(title, "utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + title + ".xls\"");
//		nTitle = URLEncoder.encode(nTitle, "utf-8");
//		response.addHeader("Content-Disposition", "attachment; filename=\"" + nTitle + ".xls\"");
		OutputStream  os=response.getOutputStream();
		exportExcel.wb.write(os);
		os.flush();
		os.close();

	}

	//创建一个边框,尚未使用
	public void createBorder(int leftTopX,int leftTopY, int rightBottomX,int rightBottomY){
		HSSFRow topRow = this.sheet.getRow(leftTopY);
		for(int i=leftTopX;i<rightBottomX;i++){ //设置上边框
			if(topRow!=null){
				HSSFCell cell = topRow.getCell(i);
				if(cell==null){
					cell = topRow.createCell(i);
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); //
					cell.setCellStyle(cellStyle);
				}else {
					HSSFCellStyle cellStyle = cell.getCellStyle();
					cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cell.setCellStyle(cellStyle);
				}
			}
		}

		for(int i=leftTopY;i<rightBottomY;i++){ //设置左边框
			HSSFRow row = sheet.getRow(i);
			if(row!=null){
				HSSFCell cell = row.getCell(leftTopX);
				if(cell==null){
					cell = row.createCell(leftTopX);
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); //
					cell.setCellStyle(cellStyle);
				}else {
//					HSSFCellStyle cellStyle = cell.getCellStyle();
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cell.setCellStyle(cellStyle);
				}
			}
		}
		HSSFRow bottomRow = sheet.getRow(rightBottomY);
		for(int i=leftTopX;i<rightBottomX;i++){ //设置下边框
			if(bottomRow!=null){
				HSSFCell cell = bottomRow.getCell(i);
				if(cell==null){
					cell = bottomRow.createCell(i);
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //
					cell.setCellStyle(cellStyle);
				}else {
					HSSFCellStyle cellStyle = cell.getCellStyle();
					cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cell.setCellStyle(cellStyle);
				}
			}
		}

		for(int i=leftTopY;i<rightBottomY;i++){ //设置右边框
			HSSFRow row = sheet.getRow(i);
			if(row!=null){
				HSSFCell cell = row.getCell(rightBottomX-1);
				if(cell==null){
					cell = row.createCell(rightBottomX-1);
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); //
					cell.setCellStyle(cellStyle);
				}else {
					//HSSFCellStyle cellStyle = cell.getCellStyle();
					HSSFCellStyle cellStyle = wb.createCellStyle();
					cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cell.setCellStyle(cellStyle);
				}
			}
		}
	}

}