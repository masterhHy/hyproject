package com.hao.splidercenter.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hao.splidercenter.po.ParentPo;

public class OutputWriterUtils {

	public static void outPut(List<? extends ParentPo> poList) {
		String fileName = Thread.currentThread().getName();
		String dir="";
		if(poList.size()>0){
			dir = poList.get(0).getClass().getName().replace(".", "_");
			File dirFile = new File("data/"+dir);
			dirFile.mkdirs();
			String path ="data/"+dir+"/"+fileName+".txt";
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true)));
				for (ParentPo parentPo : poList) {
					out.write(parentPo.toWritingString()+"\r\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static<T> void deleteDir(Class<T> clazz){
		String dir=clazz.getName().replace(".", "_");
		File dirFile = new File("data/"+dir);
		dirFile.mkdirs();
		File[] listFiles = dirFile.listFiles();
		if(listFiles!=null&&listFiles.length>0){
			for (File file : listFiles) {
				file.delete();
			}
		}
	}
	
	/**
	 * 获取当前季度
	 * @return
	 */
	public static String getCurrentPublicDate(){
		String spliderDate="";
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String current = sdf.format(d);
		String[] arr = current.split("-");
		int year = Integer.parseInt(arr[0]);
		int month = Integer.parseInt(arr[1]) ;
		if(month<=3){
			spliderDate=(year-1)+"-12-31";
		}else if(month<=6){
			spliderDate=year+"-03-31";
		}else if(month<=9){
			spliderDate=year+"-06-30";
		}else if(month<=12){
			spliderDate=year+"-09-30";
		}
		
		return spliderDate;
	}
	/**
	 * 获取上一季度
	 * @return
	 */
	public static String getupPublicDate(){
		String spliderDate="";
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String current = sdf.format(d);
		String[] arr = current.split("-");
		int year = Integer.parseInt(arr[0]);
		int month = Integer.parseInt(arr[1]) ;
		if(month<=3){
			spliderDate=(year-1)+"-09-30";
		}else if(month<=6){
			spliderDate=(year-1)+"-12-31";
		}else if(month<=9){
			spliderDate=year+"-03-31";
		}else if(month<=12){
			spliderDate=year+"-06-30";
		}
		
		return spliderDate;
	}
	
	

	
}
