package ir.adventure.observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class ObserverApplication {
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tehran"));   // It will set UTC timezone
	}
	public static void main(String[] args) {
		SpringApplication.run(ObserverApplication.class, args);
	}
}
