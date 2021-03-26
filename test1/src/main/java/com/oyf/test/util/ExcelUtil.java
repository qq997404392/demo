package com.oyf.test.util;

import com.oyf.test.test4.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ouyangfei
 * @date Created in 2021/3/24
 * @description Excel工具类
 */
public class ExcelUtil {

    /**
     * 适合跑批处理
     * 分行处理Excel，返回对应实体类的List
     * @param workbook
     */
    public static List<User> processExcel(XSSFWorkbook workbook) {
        List<User> list = new ArrayList<>();
        // 获取Sheet表
        Sheet sheet = workbook.getSheetAt(0);
        // 总行数
        int rowLength = sheet.getLastRowNum();
        // 第一行数据
        XSSFRow headRow = (XSSFRow) sheet.getRow(0);
        for (int row = 1; row <= rowLength ; row++) {
            XSSFRow sheetRow = (XSSFRow) sheet.getRow(row);
            // 需要赋值的实体类
            User user = new User();
            for (int column = 0; column < sheetRow.getLastCellNum(); column++) {
                XSSFCell cellHeadField = headRow.getCell(column);
                XSSFCell cellField = sheetRow.getCell(column);
                if (null == cellHeadField || null == cellField) continue;
                cellHeadField.setCellType(CellType.STRING);
                String cellFieldName = stringValue(cellHeadField.getStringCellValue());
                cellField.setCellType(CellType.STRING);
                String cellFieldValue = stringValue(cellField.getStringCellValue());
                ExcelUtil.setFiledValue(user, cellFieldName, cellFieldValue);
            }
            list.add(user);
        }
        return list;
    }

    /**
     * 将表格的列表与实体类的属性名对应赋值
     * @param object 要赋值的实体类
     * @param excelFieldName 列名
     * @param value 列值
     */
    public static void setFiledValue(Object object, String excelFieldName, String value) {
        Class classz = object.getClass();
        List<Field> list = new ArrayList<>();
        list.addAll(Arrays.asList(classz.getDeclaredFields()));
        list.addAll(Arrays.asList(classz.getSuperclass().getDeclaredFields()));
        Field[] fields = new Field[list.size()];
        list.toArray(fields);
        for (Field field : fields) {
            String fieldName = field.getName();
            String fileName = field.getType().getName();
            String fileTypeName = fileName.toUpperCase();
            if (excelFieldName.equalsIgnoreCase(fieldName)) {
                // 反射时允许访问私有变量
                field.setAccessible(true);
                try {
                    if (isNumeric(value)) {
                        NumberFormat numberFormat = NumberFormat.getNumberInstance();
                        Number number = numberFormat.parse(value);
                        if (fileTypeName.contains("INT")) field.set(object, number.intValue());
                        else if (fileTypeName.contains("DOUBLE")) field.set(object, number.doubleValue());
                        else if (fileTypeName.contains("FLOAT")) field.set(object, number.floatValue());
                        else if (fileTypeName.contains("LONG")) field.set(object, number.longValue());
                        else if (fileTypeName.contains("SHORT")) field.set(object, number.shortValue());
                        else if (fileTypeName.contains("STRING")) field.set(object, value);
                    } else {
                        if (fileTypeName.contains("BOOLEAN")) field.set(object, Boolean.valueOf(value));
                        if (fileTypeName.contains("DATE")) {
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            sdf.applyPattern("yyyy-MM-dd");
                            field.set(object, sdf.parse(value));
                        } else {
                            field.set(object, value);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("暂不支持的数据类型[" + fieldName + "]");
                }
            }
        }
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) return false;
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    /**
     * 去除两边空格
     * unicode为12288字符为全角空格，直接使用trim()无法去除
     * @param str
     * @return
     */
    private static String stringValue(String str) {
        if (StringUtils.isBlank(str)) return null;
        return str.replace((char) 12288, ' ').trim();
    }

}
