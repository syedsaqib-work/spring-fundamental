package com.spring.springfundamental.swiftmessage;

import com.prowidesoftware.swift.io.parser.MxParser;
import com.prowidesoftware.swift.io.parser.XMLParser;
import com.prowidesoftware.swift.model.MxId;
import com.prowidesoftware.swift.model.MxSwiftMessage;
import com.prowidesoftware.swift.model.SwiftMessage;
import com.prowidesoftware.swift.model.mx.*;
import com.prowidesoftware.swift.model.mx.dic.Document8;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SwiftMxParsing {

    public void parseSwiftMx(String m){
        System.out.println("Index main" + m);
        String messageXml = sampleMxMessage();
        //String newMessage = getMessageSample();

       // AbstractMX mx = AbstractMX.parse(message);
        //System.out.println("AbstractMX Swift Mx Message : {}" +  mx.toJson());

        MxSwiftMessage mxSwiftMessage = MxSwiftMessage.parse(messageXml);
        //System.out.println("mxSwiftMessage Swift Mx Message : {}" +  mxSwiftMessage.toJson());
        //System.out.println(mxSwiftMessage.getAppHdr().xml());



        AppHdr appHdr = mxSwiftMessage.getAppHdr();
        String msgDefIdr = ((BusinessAppHdrV01) appHdr).getMsgDefIdr();
        System.out.println("msgDefIdr -> " + msgDefIdr);

        MxCamt02800109 mxCamt02800109 = new MxCamt02800109(mxSwiftMessage);
        mxCamt02800109.getAddtlPmtInf();

        Optional<MxId> mxId = MxParseUtils.identifyMessage("<?xml version=\"1.0\" encoding=\"UTF-8\"?><message>"+"</message>");
        System.out.println("identiryMessage : {} " + mxId.get());
        System.out.println("Sender : {}" +  mxSwiftMessage.getSender());
        System.out.println("Receiver : {}"+  mxSwiftMessage.getReceiver());
        System.out.println("message type : {}"+ mxSwiftMessage.getMessageType());
        System.out.println("Sender msg identifier :{}"+ mxSwiftMessage.getIdentifier());


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

    public String getMessageSample(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Doc:Document xmlns:Doc=\"urn:swift:xsd:camt.048.001.03\" xmlns:xsi=\"httDoc://www.w3.org/2001/XMLSchema-instance\">\n"
                + "  <Doc:ModfyRsvatn>\n"
                + "    <Doc:MsgHdr>\n"
                + "      <Doc:MsgId>001</Doc:MsgId>\n"
                + "    </Doc:MsgHdr>\n"
                + "    <Doc:RsvatnId>\n"
                + "      <Doc:Cur>\n"
                + "        <Doc:Tp>\n"
                + "          <Doc:Cd>CARE</Doc:Cd>\n"
                + "        </Doc:Tp>\n"
                + "      </Doc:Cur>\n"
                + "    </Doc:RsvatnId>\n"
                + "    <Doc:NewRsvatnValSet>\n"
                + "      <Doc:Amt>\n"
                + "        <Doc:AmtWthtCcy>1234.0</Doc:AmtWthtCcy>\n"
                + "      </Doc:Amt>\n"
                + "    </Doc:NewRsvatnValSet>\n"
                + "  </Doc:ModfyRsvatn>\n"
                + "</Doc:Document>";

        return xml;
    }
}
