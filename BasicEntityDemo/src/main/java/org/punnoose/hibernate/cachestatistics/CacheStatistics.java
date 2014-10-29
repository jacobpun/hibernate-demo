package org.punnoose.hibernate.cachestatistics;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;

public class CacheStatistics {
	public static void printCacheStatistics(String cacheName){	
		Cache cache = CacheManager.getInstance().getCache(cacheName);
		Statistics stats = cache.getStatistics();
		System.out.println("CACHE STATISTICS\r\n================");
		System.out.println("Cache Name: " + stats.getAssociatedCacheName());
		System.out.println("Cache Count: " + stats.getMemoryStoreObjectCount());
		System.out.println("Average Response Time: " + stats.getAverageGetTime());
		System.out.println("Hits: " + stats.getCacheHits());
		System.out.println("Misses: " + stats.getCacheMisses());
	}
}