package com.redis.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.redis.hibernate.model.Employee;
import com.redis.hibernate.model.Product;
import com.redis.hibernate.util.HibernateUtil;
// https://www.journaldev.com/2980/hibernate-ehcache-hibernate-second-level-cache
public class EntityBasedMain {

	public static void main(String[] args) {
		
		System.out.println("Temp Dir:"+System.getProperty("java.io.tmpdir"));
		
		//Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		
		Session session = sessionFactory.getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		printStatistics(stats, 0);
		
		Product prod = (Product) session.load(Product.class, 100077L);
		printData(prod, stats, 1);
		
		prod = (Product) session.load(Product.class, 100077L);
		printData(prod, stats, 2);
		
		//clear first level cache, so that second level cache is used
		session.evict(prod);
		prod = (Product) session.load(Product.class, 100077L);
		printData(prod, stats, 3);
		
		transaction.commit();
		
		session = sessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		
		prod = (Product) session.load(Product.class, 100087L);
		printData(prod, stats, 4);
		
		prod = (Product) session.load(Product.class, 100077L);
		printData(prod, stats, 5);
		
		//Release resources
		transaction.commit();
		sessionFactory.close();
	}

	private static void printStatistics(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("EntityFetchCount = " + stats.getEntityFetchCount()); // " [If not found in cache, database query is executed]"
		System.out.println("SecondLevel CacheHit Count = " + stats.getSecondLevelCacheHitCount() ); //" [ If record was not available in 1st level cache and its available in 2nd Level cache]"
		System.out.println("SecondLevel CacheMiss Count = " + stats.getSecondLevelCacheMissCount()); //" [If record fetched from DB, Means It was not available at cache]"
		System.out.println("SecondLevel CachePut Count = " + stats.getSecondLevelCachePutCount()); //" [When the Object not available at 2nd Level cache, Its find from Database and kept at 2nd Level cache.\n Here count will be 2 due to two object i.e Employee & Address]"
	}

	private static void printData(Product emp, Statistics stats, int count) {
		System.out.println("\n" +count+":: Name="+emp.getName()+", Zipcode="+emp.getBarcode());
		printStatistics(stats, count);
	}

}
