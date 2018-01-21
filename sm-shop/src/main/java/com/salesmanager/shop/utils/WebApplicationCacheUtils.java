package com.salesmanager.shop.utils;

import com.salesmanager.shop.utils.CacheUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class WebApplicationCacheUtils {
	
	@Inject
	private CacheUtils cache;
	
	public Object getFromCache(String key) throws Exception {
		return cache.getFromCache(key);
	}
	
	public void putInCache(String key, Object object) throws Exception {
		cache.putInCache(object, key);
	}

}
