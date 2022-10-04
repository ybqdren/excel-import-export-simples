package com.github.ybqdren;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by ybqdren
 */
public class Main {
    public void importData(){
       new Data().generator();
    }

    // 使用 HSSFWorkbook
    public void exportDataToExcelHSSFWorkbook(){

    }

    // 使用XSSWorkbook
    public void exportDataToExcelXSSWorkbook(){

    }

    // 使用SXSSFWorkbook
    public void exportDataToExcelBySXSSFWorkbook(){

    }

    // 使用easyexcel进行导出
    public void exportDataToExcelByEasyExcel(HttpServletResponse response){
        OutputStream outputStream = null;
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("导出开始时间：%s".formatted(startTime));
            outputStream = response.getOutputStream();
            ExcelWriter excelWriter = new ExcelWriterBuilder().file(outputStream).excelType(ExcelTypeEnum.XLSX).build();
            String fileName = new String("excel100w".getBytes(), "UTF-8");

            // 模拟数据 todo 待从数据库中查询
            List<Table> datas = new ArrayList<>();

            // 模拟100w
            int count = 2000001;
            // 记录数据总数
            Integer totalCount = datas.size();
            // 每一个sheet存放100w条数据
            Integer sheetDataRows = 1000000;
            // 每次写入的数据量是20w
            Integer writeDataRows = 200000;
            // 计算需要的sheet数量
            Integer sheetNum = totalCount % sheetDataRows == 0 ? (totalCount / sheetDataRows):(totalCount / sheetDataRows + 1);
            // 计算一般情况下每一个sheet需要写入的次数（一般情况不包含最后一个sheet,因为最后一个sheet不确定会写入多少条数据)
            Integer oneSheetWriteCount = sheetDataRows / writeDataRows;
            // 计算最后一个sheet需要写入的次数
            Integer lastSheetWriteCount = totalCount % sheetDataRows == 0 ? oneSheetWriteCount : (totalCount % sheetDataRows % writeDataRows == 0 ? (totalCount / sheetDataRows / writeDataRows) : (totalCount / sheetDataRows / writeDataRows + 1));

            // 开始分批查询分词吸入
            List<List<String>> dataList = new ArrayList<>();
            for(int i=0 ; i<sheetNum ;i++){
                // 封装数据，每次从数据库中批量查询20w条数据 todo

            }

            response.setHeader("Content-Disposition", "attachment;filename=%s.xlsx".formatted(
                    new String((fileName).getBytes("gb2312"), "ISO-8859-1")
            ));
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            excelWriter.finish();
            outputStream.flush();
            long endTime = System.currentTimeMillis();
            System.out.println("导出结束时间：%s ms".formatted(endTime));
            System.out.println("导出所用时间：%s 秒".formatted((endTime - startTime) / 1000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
