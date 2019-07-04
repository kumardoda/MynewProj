package com.profiler.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	 public static String readFromExcel(String filePath,String sheetName,int row,int col) 
	    {
	         String sdata;
	     try {

	         File src=new File(filePath);
	         FileInputStream fis=null;
	        
	         
		         try{
		        	  fis=new FileInputStream(src);
		        	  HSSFWorkbook wb=new HSSFWorkbook(fis);
			          HSSFSheet sh1= wb.getSheet(sheetName);
			          sdata=sh1.getRow(row).getCell(col).getStringCellValue();
			       
		        	 
		          }catch(Exception e){
		        	 fis=new FileInputStream(src);
		        	 XSSFWorkbook wb= new XSSFWorkbook(fis);
		        	 XSSFSheet sh1= wb.getSheet(sheetName);
		        	 sdata=sh1.getRow(row).getCell(col).getStringCellValue();
		        	 
		         }
	        
	         } 
	     catch (Exception e) {
	         System.out.println(e.getMessage());
	         sdata=null;
	         }
	        return sdata;
	}
    
}
