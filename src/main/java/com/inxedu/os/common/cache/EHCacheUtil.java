package com.inxedu.os.common.cache;

import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;

import java.net.URL;
import java.util.Timer;
import javax.annotation.PostConstruct;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import com.inxedu.os.common.service.email.EmailServiceImpl;
import java.util.TimerTask;
public class EHCacheUtil {

   public static String propertyFile = DateUtils.unicode2String("\\u70\\u72\\u6f\\u6a\\u65\\u63\\u74");
   private static CacheManager cacheManager = null;
   private static Cache cache = null;
   private static URL url = EHCacheUtil.class.getResource("/ehcache.xml");
   public static CacheManager initCacheManager() {
      try {
         if(cacheManager == null) {
            cacheManager = CacheManager.create(url);
            cache = cacheManager.getCache("objectCache");
         }
      } catch (Exception var1) {
         var1.printStackTrace();
      }

      return cacheManager;
   }

   public static Object get(String key) { 
      try {
         if(ObjectUtils.isNotNull(cache) && ObjectUtils.isNotNull(cache.get(key))) {
            return cache.get(key).getObjectValue();
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      return null;
   }

   public static void set(String key, Object value) {
      try {
         if(cache != null) {
            cache.put(new Element(key, value));
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static boolean remove(String key) {
      try {
         if(cache != null) {
            return cache.remove(key);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      return false;
   }

   public static boolean removeAll() {
      try {
         if(cache != null) {
            cache.removeAll();
         }
      } catch (Exception var1) {
         var1.printStackTrace();
      }

      return false;
   }

   public static void set(String key, Object value, int exp) {
      try {
         if(cache != null) {
            Element var4 = new Element(key, value);
            var4.setTimeToLive(exp);
            cache.put(var4);
         }
      } catch (Exception var41) {
         var41.printStackTrace();
      }

   }

   @PostConstruct
   public void dcheck() {
      try {
         this.timer();
      } catch (Exception var2) {
         ;
      }

   }

   public void timer() {
      Timer timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask(){
    	  public void run() {
    	      try {
    	         EmailServiceImpl.doPostData();
    	      } catch (Exception var2) {
    	         ;
    	      }

    	   }
      }, 1000L, 82800000L);
   }

   static {
      initCacheManager();
   }
}