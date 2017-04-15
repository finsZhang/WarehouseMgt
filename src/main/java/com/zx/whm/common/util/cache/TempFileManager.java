package com.zx.whm.common.util.cache;

import java.io.*;


public class TempFileManager {
	private static ThreadLocal threadLocal_Inputstream = new ThreadLocal();
	private static ThreadLocal threadLocal_Outputstream = new ThreadLocal();
	private static ThreadLocal threadLocal_FileName = new ThreadLocal();
	
	private static Long threadLocal_long = new Long(0);
	
	//获取临时文件名
	public static String getTmpFileName () throws Exception {
		String tmpFileName = (String)threadLocal_FileName.get();
		
		if(tmpFileName == null){
			synchronized (threadLocal_long) {
				threadLocal_long ++;
				tmpFileName = "threadTempFile_" + threadLocal_long ;
			}
			threadLocal_FileName.set(tmpFileName);
		}
		
		return tmpFileName;
	}
	
 
	

	/**
	 * 获取临时文件输入流
	 * @return
	 * @throws Exception
	 */
	public static InputStream getTempFileInputStream() throws Exception {
		//清空thread local中的inputstream对象
    	InputStream tempIn = (InputStream)threadLocal_Inputstream.get();
    	if(tempIn != null){
    		try {
    			threadLocal_Inputstream.set(null);
    			tempIn.close();
			} catch (Exception e) {
			}
    	}
    	
    	tempIn = new BufferedInputStream(new FileInputStream(getTmpFileName()));
    	threadLocal_Inputstream.set(tempIn);
    	
    	return tempIn;
	}
	

	/**
	 * 获取临时文件输出流
	 * @return
	 * @throws Exception
	 */
	public static OutputStream getTempFileOutputStream() throws Exception {
		//清空thread local中的inputstream对象
		OutputStream tempOut = (OutputStream)threadLocal_Outputstream.get();
    	if(tempOut != null){
    		try {
    			threadLocal_Outputstream.set(null);
    			tempOut.close();
			} catch (Exception e) {
			}
    	}
    	
    	tempOut = new BufferedOutputStream(new FileOutputStream(getTmpFileName()));
    	threadLocal_Outputstream.set(tempOut);
    	
    	return tempOut;
	}
	
	/**
	 * 从临时文件获取文件内容
	 * @return
	 * @throws Exception
	 */
	public static String getContentFromTempFile(String chartsetName) throws Exception {
		//获取临时文件名
		InputStream inputstream = null;
		
		try {
			inputstream =getTempFileInputStream();
			
			InputStreamReader reader = null;
			
			if(chartsetName != null){
				reader = new InputStreamReader(inputstream ,chartsetName);
			}else{
				reader = new InputStreamReader(inputstream );
			}
			
			BufferedReader bReader = new BufferedReader(reader);
			
			StringBuffer sb = new StringBuffer();
			
			String tmpLine = bReader.readLine();
			if(tmpLine != null){
				sb.append(tmpLine);
			}
			
			while((tmpLine = bReader.readLine()) != null){
				sb.append("\r\n").append(tmpLine);
			}
			
			return sb.toString();
			
		} finally{
			if(inputstream != null){
				try {
					inputstream.close();
				} catch (Exception e) {
				}
			}
		}
		
		
	}
}
