package finance;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.alibaba.fastjson.JSON;
import com.finance.pojo.model.mq.StockPrice;

import redis.clients.jedis.JedisShardInfo;

public class Sput {
	static String scrapy = "finance.stock.scrapy";

	public static void main(String[] args) {
		JedisShardInfo info = new JedisShardInfo("127.0.0.1", 6379);

		JedisConnectionFactory f = new JedisConnectionFactory(info);
		RedisConnection connection = f.getConnection();
		StockPrice s = new StockPrice();
		s.setCode("600280");
		s.setStartDate("2004-10-08");
		s.setEndDate("2016-11-30");
		
		lPush(connection, s);

	}

	private static void lPush(RedisConnection connection, StockPrice temp) {
		long v = connection.lPush(scrapy.getBytes(), JSON.toJSONString(temp).getBytes());
		System.out.println(v);
	}
	
	private static StockPrice lPop(RedisConnection connection) {
		byte[] reslut = connection.lPop(scrapy.getBytes());
		String ss = new String(reslut);
		StockPrice temp = JSON.parseObject(ss, StockPrice.class);
		System.out.println(temp);
		return temp;
	}

}
