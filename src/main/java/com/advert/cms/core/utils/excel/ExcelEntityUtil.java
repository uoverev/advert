package com.advert.cms.core.utils.excel;

import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;

import com.advert.cms.core.utils.excel.annotation.Export;
import com.better.framework.common.exception.BusinessException;

/**
 * <p>
 * Description:Excel文件导出工具类
 * </p>
 * Copyright (c) feinno 2013
 *
 * @author wuyuanjie on 2015年4月10日
 */
public class ExcelEntityUtil {

	private static final Object[] EMPTY = new Object[0];

	public static <T> void createWorkbook(Workbook book, List<T> exports) {
		Sheet sheet = book.createSheet();
		if (exports == null || exports.isEmpty()) {
			return;
		}
		List<Entry> entrys = new ArrayList<Entry>();
		Class<?> entityClass = exports.get(0).getClass();

		getEntryByEntityClass(entityClass, entrys);

		// 排序
		Collections.sort(entrys, new Comparator<Entry>() {
			@Override
			public int compare(Entry o1, Entry o2) {
				return o1.order - o2.order;
			}
		});

		createSheetTitle(sheet, entrys);

		createSheetBody(sheet, entityClass, entrys, exports);
	}

	private static <T> void getEntryByEntityClass(Class<?> entityClass, List<Entry> entrys) {
		List<Field> fields = new ArrayList<Field>();
		iteratorClass(entityClass, fields);
		PropertyDescriptor property = null;
		for (Field field : fields) {
			Export e = field.getAnnotation(Export.class);
			if (e == null)
				continue;
			property = BeanUtils.getPropertyDescriptor(entityClass, field.getName());
			entrys.add(new Entry(property, e.name(), e.pattern(), e.json(), e.order()));
		}
	}

	private static void iteratorClass(Class<?> entityClass, List<Field> fields) {
		Field[] fs = entityClass.getDeclaredFields();
		for (Field f : fs) {
			fields.add(f);
		}
		Class<?> superClass = entityClass.getSuperclass();
		if (superClass != null) {
			iteratorClass(superClass, fields);
		}
	}

	private static <T> void createSheetTitle(Sheet sheet, List<Entry> entrys) {
		for (int i = 0, size = entrys.size(); i < size; i++) {
			Entry entry = entrys.get(i);
			Cell cell = getCell(sheet, 0, i);
			setCellValue(cell, entry.name, entry.pattern);
		}
	}

	private static <T> void createSheetBody(Sheet sheet, Class<?> entityClass, List<Entry> entrys, List<T> exports) {
		for (int i = 0, isize = exports.size(); i < isize; i++) {
			T export = exports.get(i);
			for (int j = 0, jsize = entrys.size(); j < jsize; j++) {
				Cell cell = getCell(sheet, (i + 1), j);
				setValueToCell(cell, export, entrys.get(j));
			}
		}
	}

	private static <T> void setValueToCell(Cell cell, T entity, Entry entry) {
		try {
			Method method = entry.property.getReadMethod();
			Object value = method.invoke(entity, EMPTY);
			Map<Integer, String> jsonMap = entry.json;
			if (jsonMap != null && !jsonMap.isEmpty()) {
				String name = jsonMap.get(value);
				value = (StringUtils.isNotBlank(name)) ? name : "未知";
			}
			setCellValue(cell, value, entry.pattern);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取POI的行对象
	 * 
	 * @param sheet
	 *            表对象
	 * @param row
	 *            行号，从0开始
	 * @return
	 */
	private static Row getRow(Sheet sheet, int row) {
		row = (row < 0) ? 0 : row;
		Row r = sheet.getRow(row);
		return (r == null) ? sheet.createRow(row) : r;
	}

	/**
	 * 获取单元格对象
	 * 
	 * @param sheet
	 *            表对象
	 * @param row
	 *            行，从0开始
	 * @param col
	 *            列，从0开始
	 * @return row行col列的单元格对象
	 */
	private static Cell getCell(Sheet sheet, int row, int col) {
		Row r = getRow(sheet, row);
		return getCell(r, col);
	}

	/**
	 * 获取单元格对象
	 * 
	 * @param row
	 *            行，从0开始
	 * @param col
	 *            列，从0开始
	 * @return 指定行对象上第col行的单元格
	 */
	private static Cell getCell(Row row, int col) {
		col = (col < 0) ? 0 : col;
		Cell c = row.getCell(col);
		return (c == null) ? row.createCell(col) : c;
	}

	/**
	 * 设置Excel样式和值
	 * 
	 * @param cell
	 * @param value
	 */
	private static void setCellValue(Cell cell, Object value, String pattern) {
		if (value == null) {
			cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
			cell.setCellValue("");
		} else if (value instanceof Byte || value.getClass() == byte.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Byte) value);
		} else if (value instanceof Short || value.getClass() == short.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Short) value);
		} else if (value instanceof Integer || value.getClass() == int.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long || value.getClass() == long.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Long) value);
		} else if (value instanceof Float || value.getClass() == float.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Float) value);
		} else if (value instanceof Double || value.getClass() == double.class) {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Double) value);
		} else if (value instanceof Date) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(DateFormatUtils.format((Date) value, pattern));
		} else if (value instanceof BigDecimal) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new DecimalFormat(pattern).format(new BigDecimal((Double) value)));
		} else {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue((String) value);
		}
	}

	private static class Entry {
		public final PropertyDescriptor property;
		public final String name;
		public final String pattern;
		public final Map<Integer, String> json;
		public final int order;

		public Entry(PropertyDescriptor property, String name, String pattern, String json, int order) {
			this.property = property;
			this.name = name;
			this.pattern = pattern;
			this.json = getFieldValueJson(json);
			this.order = order;
		}

	}

	private static Map<Integer, String> getFieldValueJson(String valueJson) {
		String json = null;
		Matcher m = Pattern.compile("\\{.+\\}").matcher(valueJson);
		if (m.find()) {
			json = m.group();
		}
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Map<Integer, String> data = new LinkedHashMap<Integer, String>();
		json = StringUtils.substring(json, 1, json.length() - 1);
		for (String item : StringUtils.split(json, ",")) {
			String[] fields = StringUtils.split(item, ":");
			data.put(Integer.valueOf(fields[0]), fields[1]);
		}
		return data;

	}

	private static <T> void doExportExcel(HttpServletResponse response, Workbook workbook, String fileName,
			String suffix, List<T> exportData) {
		OutputStream output = null;
		try {
			StringBuilder builder = new StringBuilder();
			if (StringUtils.isNotBlank(fileName)) {
				builder.append(new String(fileName.getBytes("gb2312"), "iso8859-1"));
			} else {
				builder.append(System.nanoTime());
			}
			builder.append('.').append(suffix);

			output = response.getOutputStream();
			response.reset();
			response.setContentType("bin");

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + builder.toString());

			createWorkbook(workbook, exportData);
			workbook.write(output);
			output.flush();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	public static <T> void doExportForXls(HttpServletResponse response, String fileName, List<T> exportData) {
		doExportExcel(response, new HSSFWorkbook(), fileName, "xls", exportData);
	}

	private ExcelEntityUtil() {
	}

}
