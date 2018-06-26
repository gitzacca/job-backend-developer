package br.com.intelipost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class UsersApplication {

	@Autowired
	RedisServer redisServer;

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@PostConstruct
	public void startRedis() {
		if (!redisServer.isActive()) redisServer.start();
	}

	@PreDestroy
	public void stopRedis() {
		redisServer.stop();
	}

}
