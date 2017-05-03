package com.zx.whm.common.util;

import com.zx.whm.common.domain.ExcelEntity;
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
public class ExportExcelUtils {
	private static final DecimalFormat format = new DecimalFormat("####,###,###,###,###,##0.00");
	private static  HSSFCellStyle  HEADER_STYLE;
	private static  HSSFCellStyle  CONTENT_STYLE;
	private static  HSSFCellStyle  TITLE_STYLE;
	private static  HSSFCellStyle  DEFAULT_STYLE;
	private static  HSSFCellStyle  CONTENT_STYLE_FLOAT;

	public static void initStyle(HSSFWorkbook wb){
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

	/**
	 * 创建通用EXCEL头部
	 * @param rowStart 开始列
	 * @param titleName 标题头
	 * @param colSum 总列数
	 */
	public static void createTitle(HSSFSheet sheet,int rowStart, String titleName,int colSum,HSSFCellStyle cellStyle) {

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
	public static void createTwoTitle(HSSFSheet sheet,int rowStart, String titleName,Object[] codes, int colSum,HSSFCellStyle cellStyle) {
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
	public static void createAnotherTitle(HSSFSheet sheet,int rowStart, String titleName,int colSum,HSSFCellStyle cellStyle) {

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
	public static void createNormalTwoRow(HSSFWorkbook wb,HSSFSheet sheet,String[] params, int colSum) {
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
	public static void createHeader(HSSFSheet sheet,int startRow,String[] columHeader,HSSFCellStyle cellStyle) {

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
	public static void cteateCell(HSSFRow row,boolean isInt,boolean isFloat,int col,Object val,HSSFCellStyle cellStyle) {

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
	public static <T> void createContentBody(HSSFSheet sheet,int startRow,String[] headColumn,List<T> contents,HSSFCellStyle cellStyle) throws Exception{
		// 循环创建中间的单元格的各项的值
		Object value=null;
		for (int i =startRow; i < contents.size()+startRow; i++) {
			HSSFRow row = sheet.createRow((short) i);
			for (int j =0; j <headColumn.length; j++) {
				String fieldName=headColumn[j];
				Method getMethod= contents.get(i-startRow).getClass().getMethod(BeanUtils.getGetMethodNameFromFiledName(fieldName));
				value = getMethod.invoke(contents.get(i-startRow), null);
				cteateCell(row,false,false, j,value,cellStyle);
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
	public static void createBody(HSSFSheet sheet,int startRow,String[] headColumn,String[] sumColum,List<Object> contents,HSSFCellStyle cellStyle,String[] intCol,String...floatCol) throws Exception{

		// 循环创建中间的单元格的各项的值
		Object value=null;
		Map<Integer,BigDecimal> map=new HashMap<Integer,BigDecimal>();
		boolean sumflag=false;
		boolean floatFlag = false;
		boolean intFlag = false;
		if(contents==null){
			return;
		}
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
					cteateCell(row,false,true,j,format.format(value),cellStyle);
				}else if(intFlag){
					cteateCell(row,true,false,j,value,cellStyle);
				} else{
					cteateCell(row,false,false,j, value, cellStyle);
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
			cteateCell(row,false,false,0, "合计", cellStyle);
			String fieldName;
			for (int j =1; j <headColumn.length; j++) {
				fieldName=headColumn[j];
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						cteateCell(row,false,false, j, map.get(j)==null?"":format.format(map.get(j)), cellStyle);
						continue;
					}
				}
				cteateCell(row,false,false, j, map.get(j)==null?"":map.get(j), cellStyle);
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
	public static <T> void createTwoBody(HSSFSheet sheet,int startRow,String[] headColumn,String[] sumColum,List<Object> contents,HSSFCellStyle cellStyle,String nTitle,String[] nHeadTitle,String[] nHeadColumn,List<T> contentBody,String[] intCol,String...floatCol) throws Exception{

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
					cteateCell(row,false,true, j,format.format(value),cellStyle);
				}else if(intFlag){
					cteateCell(row,true,false,j,value,cellStyle);
				}else {
					cteateCell(row,false,false,j, value, cellStyle);
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
			cteateCell(row,false,false, 0, "合计", cellStyle);
			String fieldName;
			for (int j =1; j <headColumn.length; j++) {
				fieldName=headColumn[j];
				if(floatCol!=null&&floatCol.length!=0) {
					if (Arrays.asList(floatCol).contains(fieldName)) {
						cteateCell(row,false,true, j, map.get(j)==null?"":format.format(map.get(j)), cellStyle);
						continue;
					}
				}
				cteateCell(row,false,false, j, map.get(j)==null?"":map.get(j), cellStyle);
			}
		}
		num += 4;
		createAnotherTitle(sheet,num,nTitle,nHeadColumn.length, ExportExcelUtils.TITLE_STYLE);
		createHeader(sheet,num+1,nHeadTitle, ExportExcelUtils.HEADER_STYLE);
		createContentBody(sheet,num+2,nHeadColumn,contentBody, ExportExcelUtils.CONTENT_STYLE);

	}

	/**
	 * 创建数据体(双表格形式)
	 * @param startRow 开始行
	 * @param headColumn 头部标题
	 * @param contents 内容列表
	 * @throws Exception
	 */
	public static <T> void createTwoBody(HSSFSheet sheet,int startRow,String[] headColumn,List<Object> contents,HSSFCellStyle cellStyle,String[] nHeadTitle,String[] nHeadColumn,List<T> contentBody,String[] intCol,String...floatCol) throws Exception{
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
					cteateCell(row,false,true,j,format.format(value),cellStyle);
				}else if(intFlag){
					cteateCell(row,true,false, j, value, cellStyle);
				}else{
					cteateCell(row,false,false, j, value, cellStyle);
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
		createContentBody(sheet,num,nHeadColumn,contentBody, ExportExcelUtils.CONTENT_STYLE);
	}

	/**
	 * 创建合计行
	 *
	 * @param colSum
	 *            需要合并到的列索引
	 * @param cellValue
	 */
	public static void createLastSumRow(HSSFWorkbook wb,HSSFSheet sheet,int colSum, String[] cellValue) {

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
	public static void outputExcel(HSSFWorkbook wb,String fileName) {
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
	public static void exportExcelForWeb(HSSFWorkbook wb,HSSFSheet sheet,String title,String[] headTitle,String[] headColumn, List<Object> contentBody,HttpServletResponse response) throws Exception{
		ExportExcelUtils exportExcel = new ExportExcelUtils();
		exportExcel.createTitle(sheet,0,title,headTitle.length, ExportExcelUtils.TITLE_STYLE);
		exportExcel.createHeader(sheet,1,headTitle, ExportExcelUtils.HEADER_STYLE);
		exportExcel.createContentBody(sheet,2,headColumn,contentBody, ExportExcelUtils.CONTENT_STYLE);
		//exportExcel.outputExcel("e:\\cc.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		title = URLEncoder.encode(title, "utf-8");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + title + ".xls\"");
		OutputStream  os=response.getOutputStream();
		wb.write(os);
		os.flush();
		os.close();

	}

	//带合计统计
	public static void exportExcelForTotal(HSSFWorkbook wb,HSSFSheet sheet,String title,String[] headTitle,String[] headColumn,String[] sumColum,List<Object> contentBody,HttpServletResponse response,String[] intCol,String...floatCol) throws Exception{
		createTitle(sheet,0,title,headTitle.length, ExportExcelUtils.TITLE_STYLE);
		createHeader(sheet,1,headTitle, ExportExcelUtils.HEADER_STYLE);
		createBody(sheet,2,headColumn,sumColum,contentBody, ExportExcelUtils.CONTENT_STYLE,intCol,floatCol);
	}


	//创建一个边框
	public static void createBorder(HSSFWorkbook wb){
		HSSFCellStyle setBorder = wb.createCellStyle();
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_DASH_DOT);
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_DASH_DOT);
		setBorder.setBorderRight(HSSFCellStyle.BORDER_DASH_DOT);
		setBorder.setBorderTop(HSSFCellStyle.BORDER_DASH_DOT);

	}

}