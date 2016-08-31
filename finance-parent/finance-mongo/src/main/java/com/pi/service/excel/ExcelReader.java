package com.pi.service.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;

import com.pi.stock.model.StockInfo;

public class ExcelReader {

	
	public void readExcel(InputStream input,List<StockInfo> infos){
		 try {
			Workbook workbook = WorkbookFactory.create(input);
			Sheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if(StringUtils.isEmpty(getSheetCellContent(row.getCell(0, Row.RETURN_BLANK_AS_NULL)))){
                    continue;
                }
                StockInfo info=new StockInfo();
               // info.set
                
            }
			
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getSheetCellContent(Cell cell){
        if(cell==null){
            return null;
        }
        return cell.getStringCellValue().replaceAll("\r", "").replaceAll("\n","").trim();
    }
	
}
