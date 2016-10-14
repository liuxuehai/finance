import java.util.ArrayList;
import java.util.List;

import org.zeromq.ZMQ;

import com.alibaba.fastjson.JSON;
import com.pi.stock.mq.Message;
import com.pi.stock.mq.StockPrice;

public class MassgeSend {

	public static void main(String[] args) {

		List<Message> messges = new ArrayList<Message>();

		messges.add(getMsg("600248", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600249", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600250", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600251", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600252", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600255", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600256", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600257", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600258", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600123", "2004-10-12", "2016-09-30"));
		messges.add(getMsg("600125", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600126", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600127", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600128", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600320", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600555", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600602", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600618", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600751", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600754", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600776", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600801", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600818", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600819", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600822", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600827", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600835", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600841", "2004-10-08", "2016-09-30"));
		
		messges.add(getMsg("600843", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600844", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600845", "2004-10-08", "2016-09-30"));
		messges.add(getMsg("600848", "2004-10-08", "2016-09-30"));

		ZMQ.Context context = ZMQ.context(1);

		// Socket to talk to server
		System.out.println("Connecting to hello world server");

		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		socket.connect("tcp://127.0.0.1:5555");

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
