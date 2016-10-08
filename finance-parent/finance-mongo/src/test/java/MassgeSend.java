import java.util.ArrayList;
import java.util.List;

import org.zeromq.ZMQ;

import com.alibaba.fastjson.JSON;
import com.pi.stock.mq.Message;
import com.pi.stock.mq.StockPrice;

public class MassgeSend {

	public static void main(String[] args) {

		List<Message> messges = new ArrayList<Message>();
//		messges.add(getMsg("601005", "2016-06-02", "2016-09-30"));
//		messges.add(getMsg("601006", "2006-08-01", "2016-09-30"));
//		messges.add(getMsg("601007", "2007-04-06", "2016-09-30"));
		//messges.add(getMsg("601008", "2007-04-26", "2016-09-30"));

//		messges.add(getMsg("601099", "2007-12-28", "2016-09-30"));
//		messges.add(getMsg("601100", "2011-10-28", "2016-09-30"));
//		messges.add(getMsg("601101", "2010-03-31", "2016-09-30"));
//		messges.add(getMsg("601106", "2010-02-09", "2016-09-30"));

//		messges.add(getMsg("601107", "2009-07-27", "2016-09-30"));
//		messges.add(getMsg("601111", "2006-08-18", "2016-09-30"));
//		messges.add(getMsg("601113", "2011-05-09", "2016-09-30"));
//		messges.add(getMsg("601116", "2011-03-02", "2016-09-30"));
//
//		messges.add(getMsg("601117", "2010-01-07", "2016-09-30"));
//		messges.add(getMsg("601118", "2011-01-07", "2016-09-30"));
//		messges.add(getMsg("601126", "2010-12-31", "2016-09-30"));
//		messges.add(getMsg("601127", "2011-06-30", "2016-09-30"));
//
//		messges.add(getMsg("601137", "2011-01-27", "2016-09-30"));
//		messges.add(getMsg("601139", "2009-12-25", "2016-09-30"));
//		messges.add(getMsg("601155", "2015-12-04", "2016-09-30"));
		
		
		
//		messges.add(getMsg("601158", "2010-03-29", "2016-09-30"));
//		messges.add(getMsg("601163", "2016-09-09", "2016-09-30"));
//		messges.add(getMsg("601163", "2016-09-09", "2016-09-30"));
//		messges.add(getMsg("601166", "2007-02-05", "2016-09-30"));
//		
//		messges.add(getMsg("601168", "2007-07-12", "2016-09-30"));
//		messges.add(getMsg("601169", "2007-09-19", "2016-09-30"));
//		messges.add(getMsg("601177", "2010-10-11", "2016-09-30"));
//		messges.add(getMsg("601179", "2010-01-28", "2016-09-30"));
//		
//		messges.add(getMsg("601186", "2008-03-10", "2016-09-30"));
//		messges.add(getMsg("601188", "2010-03-19", "2016-09-30"));
//		messges.add(getMsg("601198", "2015-02-26", "2016-09-30"));
//		messges.add(getMsg("601199", "2016-09-24", "2016-09-30"));
//		
//		messges.add(getMsg("601208", "2011-05-20", "2016-09-30"));
//		messges.add(getMsg("601211", "2015-06-26", "2016-09-30"));
//		messges.add(getMsg("601216", "2011-02-22", "2016-09-30"));
//		messges.add(getMsg("601218", "2011-05-06", "2016-09-30"));
//		
//		messges.add(getMsg("601222", "2011-08-08", "2016-09-30"));
//		messges.add(getMsg("601225", "2014-01-28", "2016-09-30"));
//		messges.add(getMsg("601226", "2014-12-11", "2016-09-30"));
//		messges.add(getMsg("601231", "2012-02-20", "2016-09-30"));
		
//		messges.add(getMsg("601233", "2015-12-18", "2016-09-30"));
//		messges.add(getMsg("601238", "2012-03-29", "2016-09-30"));
//		messges.add(getMsg("601258", "2015-10-24", "2016-09-30"));
//		messges.add(getMsg("601288", "2010-07-15", "2016-09-30"));
//		
//		messges.add(getMsg("601311", "2011-06-02", "2016-09-30"));
//		messges.add(getMsg("601313", "2012-01-16", "2016-09-30"));
//		messges.add(getMsg("601318", "2007-03-01", "2016-09-30"));
//		messges.add(getMsg("601328", "2007-05-15", "2016-09-30"));
		
		messges.add(getMsg("601333", "2014-08-01", "2016-09-30"));
		messges.add(getMsg("601336", "2011-12-16", "2016-09-30"));
		messges.add(getMsg("601339", "2012-06-12", "2016-09-30"));
		messges.add(getMsg("601368", "2015-06-12", "2016-09-30"));
		
		messges.add(getMsg("601369", "2010-04-28", "2016-09-30"));
		messges.add(getMsg("601377", "2010-10-13", "2016-09-30"));
		messges.add(getMsg("601388", "2012-04-23", "2016-09-30"));
		messges.add(getMsg("601390", "2007-12-03", "2016-09-30"));
		
		
		messges.add(getMsg("601398", "2006-10-27", "2016-09-30"));
		messges.add(getMsg("601515", "2012-02-16", "2016-09-30"));
		messges.add(getMsg("601518", "2010-03-19", "2016-09-30"));
		messges.add(getMsg("601519", "2011-01-28", "2016-09-30"));
		
		messges.add(getMsg("601555", "2011-12-12", "2016-09-30"));
		messges.add(getMsg("601558", "2011-01-13", "2016-09-30"));
		messges.add(getMsg("601566", "2011-05-30", "2016-09-30"));
		messges.add(getMsg("601567", "2011-06-15", "2016-09-30"));
		
		messges.add(getMsg("601579", "2014-08-25", "2016-09-30"));
		messges.add(getMsg("601588", "2006-10-16", "2016-09-30"));
		messges.add(getMsg("601595", "2016-08-17", "2016-09-30"));
		messges.add(getMsg("601599", "2011-05-27", "2016-09-30"));
		
		messges.add(getMsg("601600", "2007-04-30", "2016-09-30"));
		messges.add(getMsg("601601", "2007-12-25", "2016-09-30"));
		messges.add(getMsg("601608", "2012-07-06", "2016-09-30"));
		messges.add(getMsg("601611", "2016-06-06", "2016-09-30"));
		
		messges.add(getMsg("601616", "2011-02-01", "2016-09-30"));
		messges.add(getMsg("601618", "2009-09-21", "2016-09-30"));
		messges.add(getMsg("601628", "2007-01-09", "2016-09-30"));
		messges.add(getMsg("601633", "2011-09-28", "2016-09-30"));
		
		messges.add(getMsg("601636", "2011-08-12", "2016-09-30"));
		messges.add(getMsg("601666", "2006-11-23", "2016-09-30"));
		messges.add(getMsg("601668", "2009-07-29", "2016-09-30"));
		messges.add(getMsg("601669", "2011-10-18", "2016-09-30"));
		

		ZMQ.Context context = ZMQ.context(1);

		// Socket to talk to server
		System.out.println("Connecting to hello world server");

		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		socket.connect("tcp://192.168.1.103:5555");

		for (Message temp : messges) {
			String request = JSON.toJSONString(temp);
			System.out.println("Sending  " + request + "  ");
			socket.send(request.getBytes(ZMQ.CHARSET), 0);

			byte[] reply = socket.recv(0);
			System.out.println("Received " + new String(reply, ZMQ.CHARSET) + " ");
		}

		socket.close();
		context.term();

	}

	public static Message getMsg(String code, String startDate, String endDate) {
		Message message = new Message();
		StockPrice price = new StockPrice();
		price.setCode(code);
		price.setStartDate(startDate);
		price.setEndDate(endDate);
		message.setData(price);
		message.setType("price");

		return message;
	}

}
