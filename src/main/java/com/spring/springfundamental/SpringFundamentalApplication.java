package com.spring.springfundamental;

import com.spring.springfundamental.swiftmessage.ParseUnknownMessage;
import com.spring.springfundamental.swiftmessage.SwiftMxParsing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//public class SpringFundamentalApplication implements CommandLineRunner {

	public class SpringFundamentalApplication{

		public static void main(String[] args){

		ApplicationContext applicationContext = SpringApplication.run(SpringFundamentalApplication.class, args);

		/*
			SwiftMxParsing swiftMxParsing = applicationContext.getBean(SwiftMxParsing.class);
			System.out.println("swiftMxParsing bean");
			swiftMxParsing.parseSwiftMx("Saqib");
		*/

		/*
			ExtractXmlBodyOnly extractXmlBodyOnly = applicationContext.getBean(ExtractXmlBodyOnly.class);
			System.out.println("ExtractXmlBodyOnly bean");
			extractXmlBodyOnly.extractXmlBody("extractXmlBody call");
		 */

		/*
			ParseUnknownMessage parseUnknownMessage = applicationContext.getBean(ParseUnknownMessage.class);
			System.out.println("parseUnknownMessage bean");
			parseUnknownMessage.parseUnknownMessageSwiftMx();
		*/
	}


//	@Override
//	public void run(String... args) throws Exception {
//
//	}

}
