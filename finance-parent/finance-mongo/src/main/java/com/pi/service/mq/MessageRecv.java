package com.pi.service.mq;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.zeromq.ZMQ;

import com.alibaba.fastjson.JSON;
import com.pi.stock.mq.Message;

@Service
public class MessageRecv implements ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(MessageRecv.class);
	@Autowired
	private MessageConsumer messageConsumer;

	ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@PostConstruct
	public void init() {
		MessageThread messageThread = new MessageThread(messageConsumer);
		messageThread.start();
	}

}

class MessageThread extends Thread {

	private static Logger logger = LoggerFactory.getLogger(MessageThread.class);
	private MessageConsumer messageConsumer;

	public MessageThread(MessageConsumer messageConsumer) {
		this.messageConsumer = messageConsumer;
	}

	@Override
	public void run() {
		ZMQ.Context context = ZMQ.context(1);

		// Socket to talk to clients
		ZMQ.Socket socket = context.socket(ZMQ.REP);

		socket.bind("tcp://*:5555");

		while (!Thread.currentThread().isInterrupted()) {

			byte[] reply = socket.recv(0);
			String msg = new String(reply, ZMQ.CHARSET);
			logger.info("Received " + ": [" + msg + "]");
			// Create a "Hello" message.
			String request = "world";
			Message message = null;
			try {
				message = JSON.parseObject(msg, Message.class);
				logger.info("Received " + ": [" + message + "]");
			} catch (Exception e) {
				request = "error";
				logger.info("parse msg error[{}]", e);
			}

			messageConsumer.consumer(message);

			// Send the message
			socket.send(request.getBytes(ZMQ.CHARSET), 0);

		}

		socket.close();
		context.term();

	}

}
