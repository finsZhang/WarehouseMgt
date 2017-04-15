package com.zx.whm.common.util.cache;

import com.zx.whm.common.util.ApplicationUtil;
import com.zx.whm.domain.SysDictitem;
import com.zx.whm.service.SysDictitemSV;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public  class DictCache {
	private static Logger logger = LoggerFactory.getLogger(DictCache.class);
	private static Map<String, Map<String,SysDictitem>> dictMap = new HashMap<String, Map<String, SysDictitem>>();
	private static SysDictitemSV sysDictitemSV = ApplicationUtil.getBean(SysDictitemSV.class);

	public static SysDictitem[] getDictItemsByDictName(String dictName){
		List<SysDictitem> list =  getDictItemListsByDictName( dictName);
		if(list == null){
			return new SysDictitem[0];
		}
		
		return list.toArray(new SysDictitem[0]);
	}
	/**
	 * 
	 * 功能说明：根据根节点取出树
	 * TODO 
	 * @param dictName
	 * @author hejm
	 * @return
	 */
	public static List<SysDictitem> getDictTreeByRootItemNo(String dictName, String itemNo){
		List<SysDictitem> list =  getDictItemListsByDictName( dictName);
		if(list == null){
			return null;
		}
		
		List<SysDictitem> newList = new ArrayList<SysDictitem>();
		for (SysDictitem value : list) {
			if("".equals(itemNo)){
				if(value.getParentItemNo()==null){
					newList.add(value);
				}
			}else {
				if(value.getParentItemNo()!=null){
					if(value.getParentItemNo().equals(itemNo) ){
						newList.add(value);
				    }
				}
			}
			
		}
		
		return newList;
	}
	/**
	 * 
	 * 功能描述：根据字典名称获取列表。
	 *
	 * 
	 * @param dictName 字典名称
	 * @return
	 */
	public static List<SysDictitem> getDictItemListsByDictName(String dictName) {
		List<SysDictitem> items = new ArrayList<SysDictitem>();
		if (StringUtils.isBlank(dictName)) {
			return items;
		}
		
		Map<String, SysDictitem> map = dictMap.get(dictName);
		if (map == null || map.size() == 0) {
			return items;
		}
		
		items.addAll(map.values());
		
		//根据item_no排序
		Collections.sort(items, new Comparator<SysDictitem>() {
			public int compare(SysDictitem o1, SysDictitem o2) {
				long tmp=o1.getItemOrder() - o2.getItemOrder();
				
				if(tmp==0){
					return 0;					
				}
				else if(tmp>0){
					return 1;
				}else{
					return -1;
				}
			}
		});
		
		//根据item_state来过滤字典
		for(int i=0;i<items.size();i++){
			SysDictitem item = items.get(i);
			if("0".equals(item.getItemState())){
				items.remove(item);
			}
		}

		return items;
	}
	
	
	/**
	 * 
	 * 功能描述：根据字典名称和字典项名称，获取字典对象
	 *
	 * @author wuyz Mar 20, 2012
	 * 
	 * @param dictName
	 * @param itemCode
	 * @return
	 */
	public static SysDictitem getDictItemByDictNameAndItemNo(String dictName, String itemCode) {
		if (StringUtils.isBlank(dictName) || StringUtils.isBlank(itemCode)) {
			return null;
		}
		
		Map<String, SysDictitem> map = dictMap.get(dictName);
		if (map == null || map.size() == 0) {
			return null;
		}
		return (SysDictitem)map.get(itemCode);
	}
	
	/**
	 * 
	 * 功能描述：根据字典名称和字典值来获取字典值描述
	 *
	 * @author wuyz 2012-3-20
	 * 
	 * @param dictName 字典名称
	 * @param itemCode 字典编码
	 * @return
	 */
	public static String getDictItemValue(String dictName, String itemCode) {
		SysDictitem value = getDictItemByDictNameAndItemNo(dictName, itemCode);
		return value == null ? "" : value.getItemName();
	}
	/**
	 * 
	 * 功能描述：根据字典名称和字典值来获取字典值描述，获取字典表中ItemDesc对应值
	 *
	 * @author wuyz 2014-3-20
	 * 
	 * @param dictName 字典名称
	 * @param itemCode 字典编码
	 * @return
	 */
	public static String getDictItemValueGetItemDesc(String dictName, String itemCode) {
		SysDictitem value = getDictItemByDictNameAndItemNo(dictName, itemCode);
		return value == null ? "" : value.getItemDesc();
	}
	
	/**
	 * 
	 * 功能描述：根据字典名称获取字典项数据
	 * 
	 * @param dictName 字典项名称，如果该值为空，则表示获取所有
	 * @return
	 */
	private static Map<String, Map<String,SysDictitem>> loadDictData(String dictName) {
		Map<String, Map<String,SysDictitem>> map = new HashMap<String, Map<String,SysDictitem>>();
		try {
			//根据字典项获取数据
			Map<String, Object> condMap = new HashMap<String, Object>();
			String condition="";
			SysDictitem[] dictValues;
			if (StringUtils.isNotBlank(dictName)) {
				dictValues = sysDictitemSV.findByDictName(dictName);
			}else {
				List<SysDictitem> sysDictitemEntities = sysDictitemSV.findAll();
				dictValues = new SysDictitem[sysDictitemEntities.size()];
				dictValues = sysDictitemEntities.toArray(dictValues);
			}
			//将返回的数据放入map
			for (int i=0;i<dictValues.length;i++) {
				SysDictitem value  = dictValues[i];
				String name = value.getDictName();
				Map<String, SysDictitem> tmpMap = map.get(name);
				if (tmpMap == null) {
					tmpMap = new HashMap<String, SysDictitem>();
					map.put(name, tmpMap);
				}
				tmpMap.put(value.getItemNo(), value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	

	/**
	 * 
	 * 功能描述：重新加载所有字典缓存数据
	 *
	 * @author wuyz Mar 20, 2012
	 *
	 */
	public static void reloadData() {
		logger.info("缓存字典参数信息重新加载 --- 开始");
		
		Map newMap  = loadDictData("");
		Map tmp = dictMap;
		synchronized (dictMap) {
			dictMap = newMap;
		}
		
		tmp = null;
		//将文件内容写入文件
		ObjectCacheWithFile.saveObjectToFile(CACHE_FOLDER_NAME, CACHE_FILE_NAME, dictMap);
		logger.info("缓存字典参数信息重新加载 --- 结束");
	}
	
	/**
	 * 启动时从文件加载 
	 */
	public static void loadDataFromFile(){
		Object newMap  =  ObjectCacheWithFile.getObjectFromFile(CACHE_FOLDER_NAME, CACHE_FILE_NAME);
		if(newMap != null){
			dictMap = (Map)newMap;
		}else{
			reloadData();
		}
	}
	
	/**
	 * jsp调用刷新字典
	 * @param 
	 * @return void
	 * @throws
	 */
	public static void refreshDictData(){
		reloadData();
	}
	
	private static final String CACHE_FILE_NAME = "SYS_DICT_ITEM";//存放在硬盘上的缓存文件
	private static final String CACHE_FOLDER_NAME = "WAREHOUSE_DICT_ITEM";//存放在硬盘上的缓存文件

}
