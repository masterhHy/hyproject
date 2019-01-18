package com.hao.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;


/**
 * 
 * @author gjh
 *  递归工具类型
 *  实体类必须含有 children 这个字段 且类型为List<T>
 * 		必须包含 @Id 注解，放在id字段get方法上面
 */
public class RecursionUtils<T> {
	
	
	private String idMethodName;
	private String parentIdKey;
	private List<T> list;
	private Map<String,List<Integer>> ixMap;//key 为对象parentId val为存放parent对象的Index
	/**
	 * 
	 * @param list 
	 * @param parentIdKey 存放父类Id 的字段名称
	 * @return
	 * @throws Exception 
	 */
	public List<T> formatterHisChild(List<T> list,String parentIdKey) throws Exception{
			
			if(list!=null&&list.size()>0){
				Class<? extends Object> clazz = list.get(0).getClass();
				
				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field : declaredFields) {
					Id idField = field.getAnnotation(Id.class);
					if(idField!=null){
						String name = field.getName();
						String getMethod ="get"+name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
						idMethodName=getMethod;
					}
				}
				this.parentIdKey=parentIdKey;
				this.list=list;
				
				this.addIndex();
				return this.process();
				
			}
			
		return null;
	}
	
	
	
	
	
	
	protected List<T> process() throws Exception{
		List<T> rootList = this.getRootT();
		for (T t : rootList) {
			this.putHisChild(t);
		}
		return rootList;
	}
	private void putHisChild(T t) throws Exception{
		Object id = this.getTid(t);
		List<T> hisChild = this.getHisChild(id);
		if(hisChild.size()!=0){
			this.setTChildrenList(t, hisChild);
			for (T child : hisChild) {
				this.putHisChild(child);
			}
		}else{
			this.setTChildrenList(t, null);
		}
	}
	
	private List<T> getRootT() throws Exception{
		List<String> id = new ArrayList<>();
		List<T> rootList = new ArrayList<>();
		for (T t : this.list) {
			id.add(this.getTid(t).toString());
		}
		for (T t : this.list) {
			String parentId = this.getTparentId(t)==null?null:this.getTparentId(t).toString();
			if(!id.contains(parentId)){
				rootList.add(t);
			}
		}
		return rootList;
		
	}
	
	
	private List<T> getHisChild(Object id) throws Exception{
		List <T> childList = new ArrayList<T>();
		List<Integer> ixList = ixMap.get(id.toString());
		if(ixList!=null){
			for (Integer ix : ixList) {
				T t = this.list.get(ix);
				childList.add(t);
			}
		}
		return childList;
	}
	
	protected Object  getTid(T t) throws Exception{
		Class<? extends Object> clazz = t.getClass();
		return clazz.getMethod(idMethodName).invoke(t);
	}
	
	protected  Object getTparentId(T t) throws  Exception{
		Class<? extends Object> clazz = t.getClass();
		Field idField = clazz.getDeclaredField(parentIdKey);
		idField.setAccessible(true);
		Object id = idField.get(t);
		if(id==null){
			return null;
		}
		if(id.getClass().getName().equals(clazz.getName())){//父级为对象
			return this.getTid((T)id);
		}else{
			return id;
		}
		
	}
	protected  List<T> getTChildrenList(T t) throws  Exception{
		Class<? extends Object> clazz = t.getClass();
		Field idField = clazz.getDeclaredField("children");
		idField.setAccessible(true);
		return (List<T>)idField.get(t);
		
	}
	protected  void setTChildrenList(T t,Object val) throws  Exception{
		Class<? extends Object> clazz = t.getClass();
		Field idField = clazz.getDeclaredField("children");
		idField.setAccessible(true);
		idField.set(t, val);
		
	}
	
	private T copy(T t) throws Exception{
		T newInstance = (T)t.getClass().getConstructor().newInstance();
		Field[] fields = newInstance.getClass().getDeclaredFields();
		for (Field field : fields) {
			if(!field.getName().equals("children")&&!field.getName().equals(parentIdKey)){
				field.setAccessible(true);
				if(Modifier.toString(field.getModifiers()).indexOf("final")==-1){
					field.set(newInstance, field.get(t));
				}
			}
		}
		return newInstance;
	}
	
	//若子类 和父类对象互相应用，把这个关系解除
	private void setParentObject(T t) throws Exception{
		Class<? extends Object> clazz = t.getClass();
		Field idField = clazz.getDeclaredField(parentIdKey);
		idField.setAccessible(true);
		Object id = idField.get(t);
		if(id!=null&&id.getClass().getName().equals(clazz.getName())){
			if(list.contains(id)){
				idField.set(t, this.copy((T)id));
			}
		}
	}
	
	
	private void addIndex() throws Exception{
		ixMap = new HashMap<>();
		
		for(int i=0;i<list.size();i++){
			T t = list.get(i);
			this.setParentObject(t);
			Object id = this.getTparentId(t);
			if(id!=null){
				if(ixMap.containsKey(id.toString())){
					ixMap.get(id.toString()).add(i);
				}else{
					List<Integer> ixList = new ArrayList<>();
					ixList.add(i);
					ixMap.put(id.toString(), ixList);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * 自定义根节点，返回 他孩子的id
	 * 
	 */
	public List<String> getHisChild(List<T> list,String parentIdKey,String rootKey) throws Exception{
		
		if(list!=null&&list.size()>0){
			Class<? extends Object> clazz = list.get(0).getClass();
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				Id idColunm = method.getAnnotation(Id.class);
				if(idColunm!=null){
					idMethodName=method.getName();
				}
			}
			this.parentIdKey=parentIdKey;
			this.list=list;
			this.addIndex();
			List<String> res = new ArrayList<>();
			this.findHisChild(rootKey, res);
			
			
			return res;
			
		}
		
		return null;
		
	}
	
	private List<String> findHisChild(String pid,List<String> list) throws Exception{
		List<T> hisChild = this.getHisChild(pid);
		for (T t : hisChild) {
			String key = this.getTid(t).toString();
			list.add(key);
			this.findHisChild(key, list);
		}
		return list;
		
	}
	
	
}
