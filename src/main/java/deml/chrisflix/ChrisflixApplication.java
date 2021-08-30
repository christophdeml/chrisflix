package deml.chrisflix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;
import java.net.*;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class ChrisflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChrisflixApplication.class, args);
	}

}
