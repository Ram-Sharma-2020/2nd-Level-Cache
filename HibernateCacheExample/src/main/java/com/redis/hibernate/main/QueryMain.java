package com.redis.hibernate.main;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.redis.hibernate.model.Product;
import com.redis.hibernate.util.HibernateUtil;

public class QueryMain {

public static void main(String[] args) {
		
		System.out.println("Temp Dir:"+System.getProperty("java.io.tmpdir"));
		
		//Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		
		Session session = sessionFactory.getCurrentSession();
		
		printStatistics(stats, 0);
		Product prod =getProducts(session, 100077L);
		
		printData(prod, stats, 1);
		
		session = sessionFactory.getCurrentSession();
		prod =getProducts(session, 100077L);
		printData(prod, stats, 2);
		
		//clear first level cache, so that second level cache is used
		
		//session.evict(prod);
		session = sessionFactory.getCurrentSession();
		prod =getProducts(session, 100077L);
		printData(prod, stats, 3);
		
		
		
		session = sessionFactory.getCurrentSession();
		prod =getProducts(session, 100087L);
		printData(prod, stats, 4);
		
		session = sessionFactory.getCurrentSession();
		prod =getProducts(session, 100077L);
		printData(prod, stats, 5);
		
		//Release resources
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
	
	public static Product getProducts(Session session, Long barcode) {
		try {
			Thread.sleep(60000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("From Product WHERE barcode = :barcode")
				.setCacheable(true)
				.setCacheRegion("product");
		query.setParameter("barcode", barcode);
		Product p = (Product) query.uniqueResult();
		tr.commit();
		System.out.println(p.name + "   "+ p.barcode + "  "+ p.mrp);
		return p;
	}
}
