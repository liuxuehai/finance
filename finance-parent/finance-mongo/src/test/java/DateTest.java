import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.pi.base.ExcelRequest;
import com.pi.base.HttpMethod;
import com.pi.service.handler.StockInfoHandler;
import com.pi.service.header.HeaderBuilder;
import com.pi.service.processor.StockPriceProcessor;

public class DateTest {

	public static void main(String[] args) {

		LocalDate today = LocalDate.now();
		LocalDate start = LocalDate.of(2004, 10, 8);
		long s = start.until(today, ChronoUnit.DAYS);
		System.out.println(s);
		for (int i = 0; i <= s; i++) {
			start = start.plusDays(1);
			DayOfWeek dayOfWeek = start.getDayOfWeek();
			if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
				if (i % 10 == 0) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				String host = "http://market.finance.sina.com.cn/downxls.php";
				String date = start.toString();
				String symbol = "600054";
				String url = host + "?date=" + date + "&symbol=sh" + symbol;

				System.out.println(url);
			}
		}
	}
}
