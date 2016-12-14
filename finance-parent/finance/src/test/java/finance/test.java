package finance;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisShardInfo;

public class test {
	static String key = "finance.stock.price";
	
	static String scrapy = "finance.stock.scrapy";

	public static void main(String[] args) {
		JedisShardInfo info = new JedisShardInfo("192.168.1.105", 6379);

		JedisConnectionFactory f = new JedisConnectionFactory(info);
		f.setHostName("192.168.1.105");
		f.setPort(6379);
		RedisConnection connection = f.getConnection();

		String code = "002049";
		LocalDate today = LocalDate.parse("2016-11-30");
		LocalDate start = LocalDate.parse("2005-06-06");
		long s = start.until(today, ChronoUnit.DAYS);
		for (int i = 0; i <= s; i++) {

			DayOfWeek dayOfWeek = start.getDayOfWeek();
			if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
				process(connection, code, start.toString());
			}
			start = start.plusDays(1);
		}

	}

	private static void process(RedisConnection connection, String code, String date) {
		String host = "http://market.finance.sina.com.cn/downxls.php";
		String symbol = "";
		if (code.startsWith("6")) {
			symbol = "sh" + code;
		} else {
			symbol = "sz" + code;
		}
		String url = host + "?date=" + date + "&symbol=" + symbol;
		long v = connection.lPush(key.getBytes(), url.getBytes());
		System.out.println(v);
	}

}
