package br.com.paguemob.kimura.interview;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories 
@EntityScan("br.com.paguemob.kimura.interview.model")
public class InterviewApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		new InterviewApplication()
		.configure(new SpringApplicationBuilder(InterviewApplication.class))
		.run(args);
	}
}
