package com.example.demo.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * excel 工具类
 * @author Agus
 * @date 2020年1月3日
 */
public class ExcelUtil {

	
	/**
	 * 读取excel
	 * @param file
	 * @return
	 */
	public static List<Map<Integer, String>> read(String file){

		MapDataListener listener = new ExcelUtil.MapDataListener();

		EasyExcel.read(file, listener).sheet().doRead();

		return listener.getDataList();

	}

	/**
	 * 读取excel
	 * @param file
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> read(String file, Class<T> clazz){

		ModleDataListener<T> listener = new ExcelUtil.ModleDataListener<T>();

		EasyExcel.read(file, clazz, listener).sheet().doRead();

		return listener.getDataList();

	}

	/**
	 * 读取excel
	 * @param file
	 * @param clazz
	 * @param listener
	 */
	public static <T> void read(String file, Class<T> clazz, AnalysisEventListener<T> listener){

		EasyExcel.read(file, clazz, listener).sheet().doRead();

	}

	/**
	 * 读取excel
	 * @param in
	 * @return
	 */
	public static List<Map<Integer, String>> read(InputStream in){

		MapDataListener listener = new ExcelUtil.MapDataListener();

		EasyExcel.read(in, listener).sheet().doRead();

		return listener.getDataList();

	}

	/**
	 * 读取excel
	 * @param in
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> read(InputStream in, Class<T> clazz){

		ModleDataListener<T> listener = new ExcelUtil.ModleDataListener<T>();

		EasyExcel.read(in, clazz, listener).sheet().doRead();
		
		return listener.getDataList();
		
	}
	
	/**
	 * 读取excel
	 * @param in
	 * @param clazz
	 * @return
	 */
	public static <T> void readByListener(InputStream in, Class<T> clazz, AnalysisEventListener<T> listener){
		
		EasyExcel.read(in, clazz, listener).sheet().doRead();
		
	}
	
	
	private static class MapDataListener extends AnalysisEventListener<Map<Integer, String>> {
		
	    List<Map<Integer, String>> dataList = new ArrayList<Map<Integer, String>>();

	    @Override
	    public void invoke(Map<Integer, String> data, AnalysisContext context) {
	        dataList.add(data);
	    }

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {

		}

		public List<Map<Integer, String>> getDataList() {
			return dataList;
		}

	}
	
	private static class ModleDataListener<T> extends AnalysisEventListener<T> {
		
	    List<T> dataList = new ArrayList<T>();

	    @Override
	    public void invoke(T data, AnalysisContext context) {
	        dataList.add(data);
	    }

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {

		}

		public List<T> getDataList() {
			return dataList;
		}

	}
	
}

