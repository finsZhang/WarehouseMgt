package com.zx.whm.common.util.cache;

import com.zx.whm.common.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectCacheWithFile {

	private static String CACHE_DIR = "object_cache/";
	private static Logger logger = LoggerFactory.getLogger(ObjectCacheWithFile.class);
	
		
	static{
		try {
			String tmp = PropertiesUtils.getContextProperty("FILE_CACHE_FOLDER");
			if(tmp != null){
				CACHE_DIR = tmp;
			}
		} catch (Exception e) {
		} 
		
		try {
			File f = new File(CACHE_DIR);
			if(!f.exists()){
				f.mkdirs();
			}
			
			f=null;
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从文件中获取缓存对象
	 * @param cacheType 缓存类型
	 * @param cacheName 缓存名称
	 * @return
	 */
	public static Object getObjectFromFile(String cacheType , String cacheName)  {
		return getObjectFromFile(cacheType ,cacheName ,-1);
	}
	
	/**
	 * 从文件中获取缓存对象
	 * @param cacheType
	 * @param cacheName
	 * @param expireSeconds 缓存过期时间，如果该值小于等于0，则不判断过期时间
	 * @return
	 */
	public static Object getObjectFromFile(String cacheType , String cacheName , long expireSeconds)  {
		ObjectInputStream objInputStream = null;
		File f;
		try {
			long start = System.currentTimeMillis();
			f = new File(getCacheFileName(cacheType ,cacheName ));
			if(f.exists() == false){
				return null;
			}
			
			//判断缓存文件是否过期，如果过期，则返回空 
			if(expireSeconds >0 ){
				long difSecond = (System.currentTimeMillis() - f.lastModified())/1000;
				if(difSecond>expireSeconds){
					logger.debug("FileCacheExpired:文件缓存过期");
					return null;
				}
			}
			
			objInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			
			Object obj =  objInputStream.readUnshared();
			
			if(obj != null){
				long end = System.currentTimeMillis();
				logger.debug("useFileCache:耗时：" + (end - start) + "ms ,缓存大小：" + f.length());
			}
			return obj;
		} catch (Exception e) {
			logger.error("获取文件缓存出错，不影响系统正常运行" ,e);
			return null;
		}finally{
			f = null;
			try {
				if(objInputStream != null){
					objInputStream.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	
	/** 
	 * 
	 * 将缓存存入文件
	 * @param cacheType
	 * @param cacheName
	 * @param obj 需要保存的对象
	 */
	public static   void saveObjectToFile(String cacheType , String cacheName , Object obj )  {
		ObjectOutputStream objOutputStream = null;
		File tempFile;
		File formFile ;
		try { 
			tempFile = new File(TempFileManager.getTmpFileName( ));
			if(tempFile.getParentFile()!= null	&& tempFile.getParentFile().exists() == false){
				tempFile.getParentFile().mkdirs();
			}
			objOutputStream = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(tempFile)));
			
			//存入对象
			objOutputStream.writeObject(obj);
			objOutputStream.flush();
			objOutputStream.close();
			
			//获取正式的缓存文件名
			formFile = new File(getCacheFileName(cacheType , cacheName));
			if(formFile.exists()){
				formFile.delete();
			}
			//创建缓存文件夹
			File folderFile = formFile.getParentFile();
			if(folderFile.exists() == false){
				folderFile.mkdirs();
			}
			
			//将临时文件改名
			tempFile.renameTo(formFile);
		} catch (Exception e) {
			logger.error("保存缓存文件出错，不影响系统正常运行" ,e); 
		}finally{
			tempFile = null;
			formFile = null;
			try {
				if(objOutputStream != null){
					objOutputStream.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 获取缓存文件名
	 * @param cacheType
	 * @param cacheName
	 * @return
	 * @throws Exception
	 */
	private static String getCacheFileName(String cacheType , String cacheName  ) throws Exception {
		return CACHE_DIR +"/"+ cacheType+"/"+ cacheName ;
	}
	
	
	/**
	 * 删除所有缓存
	 */
	public static void clearAllCacheFile() {
		File cacheDir = new File(CACHE_DIR);
		deleteCacheByFolder(cacheDir);
	
	}
	
	/**
	 * 按文件夹删除缓存文件
	 * @param folderFile
	 */
	private static void deleteCacheByFolder(File folderFile){
		if(folderFile.exists() == false){
			return;
		}
		
		//删除文件
		if(folderFile.isFile() == true){
			folderFile.delete();
		}
		
		//列出目录下的文件
		File cacheFiles[] = folderFile.listFiles();
		if(cacheFiles == null || cacheFiles.length==0){
			return;
		}
		
		for(File cacheFile :cacheFiles){
			deleteCacheByFolder(cacheFile);
		}
		
	}
	
	public static void main(String[] args) {
		try { 
			 
			long size =0;
			
			String cacheName = "testcache";
			String cacheFolder = "f";
			
			ArrayList tempList = new ArrayList();
			
			for(int i=0;i<6024;i++){
				HashMap map = new HashMap();
				tempList.add(map);
				
				for(int j=0;j<5;j++){
					map.put(i+"_" + j, i + "_" + j + "_dfdddfffffff");
					
					size = size + (i+"_" + j).length();
					size = size + (i + "_" + j + "_dfdddfffffff").length();
				}
			}

			long s1 = System.currentTimeMillis();
			saveObjectToFile(cacheFolder ,cacheName ,tempList );
			long e1 = System.currentTimeMillis();
			
			System.out.println("save:" +(e1-s1));
			
			long start = System.currentTimeMillis();
			Object result = getObjectFromFile( cacheFolder ,cacheName);
			long end = System.currentTimeMillis();
			System.out.println("read:" +(end - start));
			
			ArrayList re = (ArrayList)result;
			
			System.out.println(re.size());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
