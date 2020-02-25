//package com.cxy.customize.excel;
//
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.poi.excel.ExcelReader;
//import cn.hutool.poi.excel.ExcelUtil;
//import cn.hutool.poi.excel.ExcelWriter;
//import cn.hutool.poi.excel.style.StyleUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.FillPatternType;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.hibernate.validator.HibernateValidator;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Service;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//
///**
// * Excel导入导出
// * 日期默认为2018-07-07
// * 提供了修改方法
// */
//@Slf4j(topic = "Logger")
//@Service(value="excelService")
//public class ExcelService {
//    /**
//     * 使用hibernate的注解,返回全部
//     */
//    private static Validator validator = Validation
//            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();
//
//
//    private static final Map<String, String>  ledgerName = new ConcurrentHashMap();
//    static {
//        ledgerName.put("SYSTEM_INFO", "信息系统台账模版.xls");
//        ledgerName.put("CLASSIFIED_CARRIER", "涉密载体台账模版.xls");
//        ledgerName.put("INFO_EQUIPMENT", "信息设备台账模版.xls");
//        ledgerName.put("SECURITY_PRODUCTS", "安全保密产品台账模版.xls");
//        ledgerName.put("STORAGE_DEVICE", "存储设备台账模版.xls");
//    }
//
//
//    /**
//     * 获得模版文件的名称   涉密载体台账模版.xls
//     * @param ledger
//     * @return
//     */
//    public String getTemplateName(String ledger) {
//        Iterator iterator = ledgerName.entrySet().iterator();
//        Map.Entry ledgerNameEntry;
//        while(iterator.hasNext()){
//            ledgerNameEntry = (Map.Entry)iterator.next();
//           if(ledger.equals(ledgerNameEntry.getKey())){
//               return (String) ledgerNameEntry.getValue();
//           }
//        }
//        return null;
//    }
//
//
//    /**
//     *  得到导出时间
//     *  格式 yyyy-MM-dd
//     * @return
//     */
//    public  String getExportFileName(String ledgerName){
//        String formatDate = DateUtil.formatDate(new Date());
//        return formatDate+ledgerName+".xls";
//    }
//
//
//
//
//    public File readTemplate(String fileFullPath) throws IOException {
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        return resourceLoader.getResource(fileFullPath).getFile();
//    }
//
//
//
//
//    /**
//     *
//     * @param inputStream  文件输入流
//     * @param headerRowIndex 标题起始索引 基0
//     * @param startRowIndex  数据起始索引 基0
//     * @return
//     */
//    public<T> List<T> importData(InputStream inputStream,int headerRowIndex, int startRowIndex,Class<T> beanType,Map<String, String> headerAlias){
//        //默认获取第一sheet
//        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        reader.setHeaderAlias(headerAlias);
//        return reader.read(headerRowIndex,startRowIndex,beanType);
//    }
//
//
//
//
//    /**
//     * @param rows             第一页页导出数据
//     * @param sheetName       第一页表名(sheet)
//     * @param headerAlias     第一页的标题转换映射
//     * @param headNum     标题个数
//     * @return
//     * @throws
//     */
//    public ExcelWriter writeSheet(ExcelWriter writer, List rows, String sheetName, Map<String, String> headerAlias, int headNum, boolean isFirstSheet){
//        if(isFirstSheet){
//            writer.renameSheet(sheetName);
//        }else {
//            writer.setSheet(sheetName);
//        }
//        writer.setHeaderAlias(headerAlias);
//        changeDataFormat(writer,14);
//        //设置黄色标题背景
//        Font font =  writer.createFont();
//        font.setFontName("黑体");
//        font.setFontHeightInPoints((short)16);
//        writer.getOrCreateRowStyle(0).setFont(font);
//        int i = (headNum-1==0)?1:headNum-1;
//        writer.merge(i, sheetName,false);
//        setTitleStyle(writer,IndexedColors.YELLOW,25);
//        writer.write(rows, true);
//        //清空标题
//        writer.clearHeaderAlias();
//        return writer;
//    }
//
//
//
//
//    /**
//     * 设置时间格式
//     * 14 (0xe), "m/d/yy"
//     * 22 (0x16), "m/d/yy h:mm"
//     */
//    public void changeDataFormat(ExcelWriter writer, int formatValue){
//        writer.getStyleSet().getCellStyleForDate().setDataFormat((short) formatValue);
//    }
//
//    /**
//     * 设置样式
//     * @param writer
//     */
//    public void setTitleStyle(ExcelWriter writer, IndexedColors color, int width){
//        CellStyle cellStyle =  writer.getStyleSet().getHeadCellStyle();
//        StyleUtil.setColor(cellStyle,color, FillPatternType.SOLID_FOREGROUND);
//      Font font =  writer.createFont();
//      font.setFontName("黑体");
//      font.setFontHeightInPoints((short)14);
//      cellStyle.setFont(font);
//        writer.setColumnWidth(-1,width);
//    }
//
//
//
//
//
//
//    /**
//     * 提供错误信息 以下载
//     * @param obj
//     * @return
//     */
//    public <T> String validatorParam(T obj) {
//            Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
//            if (!constraintViolations.isEmpty()){
//                StringBuffer sb = new StringBuffer();
//                Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
//                while (iterator.hasNext()) {
//                    ConstraintViolation<T> next = iterator.next();
//                    sb.append(next.getMessage());
//                    sb.append(";");
//                }
//                return sb.toString();
//            }
//            return null;
//        }
//
//
//}
