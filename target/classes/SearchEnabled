package my.search;

import java.util.*;

/**
 * 支持搜索功能的Bean类需要实现该接口
 * @author liudong
 */
public interface SearchEnabled {

	/**
	 * 获取搜索对象的关键字
	 * @return
	 */
	public long getId();

	/**
	 * 返回搜索对象需要存储的字段名，例如createTime, author等
	 * @return
	 */
	public String[] GetStoreFields();

	/**
	 * 返回搜索对象的索引字段，例如title,content
	 * @return
	 */
	public String[] GetIndexFields();
	
	/**
	 * 返回对象的扩展信息
	 * @return
	 */
	public HashMap<String, String> GetExtendValues();

	/**
	 * 返回对象的扩展索引信息
	 * @return
	 */
	public HashMap<String, String> GetExtendIndexValues();
	
	/**
	 * 列出id值大于指定值得所有对象
	 * @param id
	 * @param count
	 * @return
	 */
	public List<? extends SearchEnabled> ListAfter(long id, int count) ;
	
	/**
	 * 返回文档的权重
	 * @return
	 */
	public float GetBoost();

}
