package com.cyganski.datareader;

import ch.qos.logback.core.util.CachingDateFormatter;
import com.cyganski.datareader.Currency;
import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class XMLService {
    private final Logger logger = LoggerFactory.getLogger(XMLService.class);

    public ResponseEntity<String> getResponse(RestTemplate _restTemplate) {
        try {
            return _restTemplate.exchange("https://www.bnro.ro/StatisticsReportHTML.aspx?icid=801&table=668&column=5457,5458,5462,5463,5467,5468,5469,5471,5474,5485&startDate=10-05-2022&stopDate=26-05-2022", HttpMethod.GET, null, String.class);
           // return _restTemplate.exchange("https://www.bnro.ro/StatisticsReportHTML.aspx?icid=801&table=668&column=&startDate=15-05-2022&stopDate=26-05-2022", HttpMethod.GET, null, String.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
        }
    }
    public Currency parseCurrency() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        var response = getResponse(restTemplate);
        var jsonString = "";
        if(response.getStatusCode() == HttpStatus.OK){
            jsonString = response.getBody();
            //System.out.print(jsonString);
        }

        String html = jsonString;
        Document document = Jsoup.parse(html);
        //waluty
        List<String> listaWalut = new ArrayList<String>();
        Elements walutyCheck = document.getElementsByTag("td");
        for (Element cell : walutyCheck) {

            if(cell.text().startsWith("C")){
                listaWalut.add(cell.text());
            }
        }
        System.out.println(listaWalut);

        //wartosci walut
        List<String> wartosciWalut = new ArrayList<String>();
        Elements wartosciCheck = document.getElementsByTag("td");
        for (Element cell : wartosciCheck) {
            if(Character.isDigit(cell.text().charAt(0))){
                wartosciWalut.add(cell.text());
            }
        }
        System.out.println(wartosciWalut);

        Currency currency = null;
        try {
            //Document doc = Jsoup.parse(html);

/*
            //Odczytac dane o walucie
            currency = new Currency(
                    new SimpleDateFormat("yyyy-MM-dd").parse(doc.getElementsByTagName("date").item(0).getTextContent()),
                    doc.getElementsByTagName("currency").item(0).getTextContent(),
                    Double.parseDouble(doc.getElementsByTagName("LT_RateValue").item(0).getTextContent())
            );
            NodeList nodeList = doc.getElementsByTagName("Cube");
            List<Currency> waluty = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Currency waluta = new Currency(
                            new SimpleDateFormat("yyyy-MM-dd").parse(elem.getElementsByTagName("date").item(0).getTextContent()),
                            elem.getElementsByTagName("currency").item(0).getTextContent(),
                            Double.parseDouble(elem.getElementsByTagName("value").item(0).getTextContent())
                    );
                    waluty.add(waluta);
                }
            }

*/
        }catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        List<Currency> waluty = new ArrayList<>();
        Currency waluta = new Currency(
                "dobry",
                "Bitcoin",
                4.23
        );
        waluty.add(waluta);
        currency = waluta;
        return currency;

    }

}
