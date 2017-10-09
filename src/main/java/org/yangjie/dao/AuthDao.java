package org.yangjie.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class AuthDao {

	/**
	 * 缓存授权信息, 用于验证授权回调的真实性
	 * 此处应使用外部存储实现, 长时间累计数据量过大后会内存泄漏
	 */
	private List<String> cacheList = new ArrayList<String>();
	
	
	/**
	 * 将授权信息添加到缓存列表
	 * @author YangJie [2016年5月3日 下午7:17:03]
	 * @param openid
	 */
	public boolean addOpenid(String openid){
		return cacheList.add(openid); // 将新记录追加到数组最后
	}
	
	/**
	 * 判断openid是否真实 (防止业务回调地址被攻击)
	 * @author YangJie [2016年5月3日 下午7:37:19]
	 * @param openid
	 * @return
	 */
	public boolean isExist(String openid){
		return cacheList.contains(openid);
	}
	
}