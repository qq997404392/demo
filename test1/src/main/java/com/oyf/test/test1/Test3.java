package com.oyf.test.test1;

import com.oyf.test.util.FileToBase64;
import org.w3c.dom.Document;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class Test3 {
    public static void main(String args[]) throws Exception {
        String path = "D:/logback-boot.xml";
        try {
            File fileinput = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileinput);

            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            // 将转换过的xml的String 样式打印到控制台
            System.out.println(" 将转换过的xml的String 样式打印到控制台");

            String content = writer.toString();
            System.out.println(content);

            String base64 = FileToBase64.encodeBase64File(content);
            System.out.println(base64.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
