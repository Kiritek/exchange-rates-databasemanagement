package com.cyganski.datareader;

import com.cyganski.datareader.Currency;
import com.cyganski.datareader.XMLService;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class XmlParserApplication implements CommandLineRunner {


    private XMLService xmlService;

    public XmlParserApplication(XMLService xmlService) {
        this.xmlService = xmlService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(XmlParserApplication.class);
        // disable spring banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    CommandLineRunner commandLineRunner(KafkaTemplate<String, Currency> kafkaTemplate){
        return args->{
            kafkaTemplate.send("exchangeRates", );
        };
    }

    @Override
    public void run(String... args) throws Exception {

        // load course from XMLService
        Currency currency = xmlService.parseCurrency();

        // print course details
        System.out.println(currency);

    }
}

