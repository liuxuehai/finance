package com.finance.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FinanceRedis {

	@Autowired
	private StringRedisTemplate template;

	final String key = "finance.stock.price";

	public String fentch(String key) {

		if (StringUtils.isEmpty(key)) {
			key = this.key;
		}

		ValueOperations<String, String> ops = this.template.opsForValue();
		if (!this.template.hasKey(key)) {
			return ops.get(key);
		}
		return "";
	}

	public String lpop(final String key) {
		String result = template.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = template.getStringSerializer();
				byte[] res = connection.lPop(serializer.serialize(key));
				return serializer.deserialize(res);
			}
		});
		return result;
	}

	public long lpush(final String key, final String data) {
		long result = template.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long v = connection.lPush(key.getBytes(), data.getBytes());
				return v;
			}
		});
		return result;
	}

	public long llen(final String key) {
		long result = template.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = template.getStringSerializer();
				long res = connection.lLen(serializer.serialize(key));
				return res;
			}
		});
		return result;
	}

}
