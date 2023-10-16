package com.spring.springfundamental.xmlManipulation;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ExtractXmlBodyOnly {

    public void extractXmlBody(String whoCalled){
        System.out.println("whoCalled -> " + whoCalled);
        String xml = sampleMxMessage();
        extractMessageBodyElements(xml);
        //extractBody(xml);
    }

    public void extractMessageBodyElements(String xmlMessage) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(xmlMessage.getBytes(StandardCharsets.UTF_8));
            Document doc = dbBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

/*            Document doc2 = parseXmlTODocument2(xmlMessage);
            NodeList childNodes = doc2.getChildNodes();
            System.out.println(childNodes.getLength());
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (childNodes.item(i) instanceof Element) {
                    Element root = (Element) childNodes.item(i);
                    System.out.println(root.getNodeName());
                }
            }*/
            //System.out.println(childNodes.getLength());


           NodeList nodes = doc.getElementsByTagName("Proponix");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    System.out.println("Extracting xml element Values");
/*                    NodeList cNodes = element.getElementsByTagName("body");
                    System.out.println(cNodes.item(0));*/
                    getTagValue("body", element);

                }
            }

        } catch (Exception ex) {
            System.out.println("error while extracting xml elements from incoming message : {}");
            System.out.println(ex.getStackTrace().toString());
        }
    }

    public String extractBody(String xmlMessage) {
        System.out.println("extractBody start: {}");
        try {
            Document doc = parseXmlTODocument2(xmlMessage);

            XPath xPath = XPathFactory.newInstance().newXPath();
            Node messageIdNode = (Node) xPath.compile("Proponix/Header/DestinationID")
                    .evaluate(doc, XPathConstants.NODE);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);

            transformer.transform(domSource, sr);
            return sw.toString();
        } catch (XPathExpressionException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public Document parseXmlTODocument2(String xmlMessage) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            InputStream inst = new ByteArrayInputStream(xmlMessage.getBytes(StandardCharsets.UTF_8));
            Document doc = docBuilder.parse(inst);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getTagValue(String tag, Element element) {
        System.out.println(("xml getTagValue : {} " +  tag));
        if (element.getElementsByTagName(tag).item(0) != null) {
            NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = (Node) nodes.item(0);
            if (node != null) {
                return node.getNodeValue();
            }
        }
        return "";
    }

    public Map<String, String> getElementKeyValueMap(String xmlMessage, String elementName) {
        Document doc = parseXmlTODocument(xmlMessage);

        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName(elementName).item(0).getChildNodes();
        Map<String, String> propertyMap = nodeListToMap(nodeList);
        return propertyMap;
    }

    private Map<String, String> nodeListToMap(NodeList nodeList) {
        Map<String, String> result = new LinkedHashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                result.put(element.getTagName().toLowerCase(), element.getTextContent());
            }
        }
        return result;
    }

    private Document parseXmlTODocument(String xmlMessage) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            InputStream inst = new ByteArrayInputStream(xmlMessage.getBytes(StandardCharsets.UTF_8));
            Document doc = docBuilder.parse(inst);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sampleMxMessage(){
        String str = "\n" +
                "<Proponix>\n" +
                "    <Header>\n" +
                "        <DestinationID>PRO</DestinationID>\n" +
                "        <SenderID>CB1</SenderID>\n" +
                "        <OperationOrganizationID></OperationOrganizationID>\n" +
                "        <MessageType>ISO22IN</MessageType>\n" +
                "        <DateSent>20200327</DateSent>\n" +
                "        <TimeSent>0829</TimeSent>\n" +
                "        <MessageID>3190bT11111</MessageID>\n" +
                "    </Header>\n" +
                "    <SubHeader>\n" +
                "        <ReceiverAddress>CIBCCAT0XXX</ReceiverAddress>\n" +
                "        <SenderAddress>NCBACATTXXX</SenderAddress>\n" +
                "    </SubHeader>\n" +
                "    <Body>\n" +
                "        <ISOMessage>\n" +
                "            <AppHdr>\n" +
                "                <Fr>\n" +
                "                    <FIId>\n" +
                "                        <FinInstnId>\n" +
                "                            <BICFI>NCBACATTXXX</BICFI>\n" +
                "                        </FinInstnId>\n" +
                "                    </FIId>\n" +
                "                </Fr>\n" +
                "                <To>\n" +
                "                    <FIId>\n" +
                "                        <FinInstnId>\n" +
                "                            <BICFI>CIBCCAT0XXX</BICFI>\n" +
                "                        </FinInstnId>\n" +
                "                    </FIId>\n" +
                "                </To>\n" +
                "                <BizMsgIdr>IMDLC692870US01</BizMsgIdr>\n" +
                "                <MsgDefIdr>camt.029.001.09</MsgDefIdr>\n" +
                "                <BizSvc>swift.cbprplus.02</BizSvc>\n" +
                "                <CreDt>2008-09-29T04:49:45+03:00</CreDt>\n" +
                "            </AppHdr>\n" +
                "            <Document>\n" +
                "                <RsltnOfInvstgtn>\n" +
                "                    <Assgnmt>\n" +
                "                        <Id>IMDLC692870US01</Id>\n" +
                "                        <Assgnr>\n" +
                "                            <Agt>\n" +
                "                                <FinInstnId>\n" +
                "                                    <BICFI>SNDRCATTXXX</BICFI>\n" +
                "                                </FinInstnId>\n" +
                "                            </Agt>\n" +
                "                        </Assgnr>\n" +
                "                        <Assgne>\n" +
                "                            <Agt>\n" +
                "                                <FinInstnId>\n" +
                "                                    <BICFI>SNDRCATTXXX</BICFI>\n" +
                "                                </FinInstnId>\n" +
                "                            </Agt>\n" +
                "                        </Assgne>\n" +
                "                        <CreDtTm>2008-09-29T04:49:45+03:00</CreDtTm>\n" +
                "                    </Assgnmt>\n" +
                "                    <Sts>\n" +
                "                        <Conf>RJCR</Conf>\n" +
                "                    </Sts>\n" +
                "                    <CxlDtls>\n" +
                "                        <TxInfAndSts>\n" +
                "                            <CxlStsId>TESTID</CxlStsId>\n" +
                "                            <RslvdCase>\n" +
                "                                <Id>123</Id>\n" +
                "                                <Cretr>\n" +
                "                                    <Pty>\n" +
                "                                        <Nm>ABC LTD</Nm>\n" +
                "                                        <Id>\n" +
                "                                            <OrgId>\n" +
                "                                                <AnyBIC>SNDRCATTXXX</AnyBIC>\n" +
                "                                            </OrgId>\n" +
                "                                        </Id>\n" +
                "                                        <CtryOfRes>US</CtryOfRes>\n" +
                "                                    </Pty>\n" +
                "                                </Cretr>\n" +
                "                            </RslvdCase>\n" +
                "                            <OrgnlGrpInf>\n" +
                "                                <OrgnlMsgId>YTRE123</OrgnlMsgId>\n" +
                "                                <OrgnlMsgNmId>IEUWI7879</OrgnlMsgNmId>\n" +
                "                            </OrgnlGrpInf>\n" +
                "                            <OrgnlUETR>eb6305c9-1f7f-49de-aed0-16487c27b42d</OrgnlUETR>\n" +
                "                        </TxInfAndSts>\n" +
                "                    </CxlDtls>\n" +
                "                </RsltnOfInvstgtn>\n" +
                "            </Document>\n" +
                "        </ISOMessage>\n" +
                "    </Body>\n" +
                "    <Timestamp>\n" +
                "        <Date>20200327</Date>\n" +
                "        <Time>123424</Time>\n" +
                "    </Timestamp>\n" +
                "</Proponix>\n";

        return str;
    }
}
