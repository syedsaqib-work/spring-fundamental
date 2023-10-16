package com.spring.springfundamental.swiftmessage;

import com.prowidesoftware.swift.model.MxSwiftMessage;
import com.prowidesoftware.swift.model.mx.AbstractMX;
import com.prowidesoftware.swift.model.mx.BusinessAppHdrV01;
import com.prowidesoftware.swift.model.mx.MxCamt04800103;
import com.prowidesoftware.swift.model.mx.MxPacs00800108;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ParseUnknownMessage {
    public void parseUnknownMessageSwiftMx() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String xml = getUsXml();
        // parse into generic structure
        /****
        AbstractMX mx = AbstractMX.parse(xml);



        System.out.println("Parsed message type: " + mx.getMxId().id());

        // check the parsed type and cast to specific model
        if ("camt.048.001.03".equals(mx.getMxId().id())) {
            MxCamt04800103 camt = (MxCamt04800103) mx;
            System.out.println("Message id: " + camt.getModfyRsvatn().getMsgHdr().getMsgId());
        }

         ***/
        System.out.println("ParseUnknownMessage_ParseUnknownMessage");

        // parse into generic structure
        AbstractMX mx = AbstractMX.parse(xml);
        System.out.println("Parsed message type: " + mx.getMxId().id());
        System.out.println("mx business prcess : " + mx.getBusinessProcess());
        System.out.println("mx functionality : " + mx.getFunctionality());
        System.out.println("mx variant : " + mx.getVariant());
        System.out.println("mx version : " + mx.getVersion());


        // check the parsed type and cast to specific model
        if ("pacs.008.001.08".equals(mx.getMxId().id())) {
            MxPacs00800108 pacs = (MxPacs00800108) mx;
            //System.out.println("Message id: " + camt.getModfyRsvatn().getMsgHdr().getMsgId());
            System.out.println("UETR : " + pacs.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getUETR());
            System.out.println("refNo : " + pacs.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getPmtId().getInstrId());
            System.out.println("date : " + pacs.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmDt().toString());
            System.out.println("ccy : " + pacs.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmAmt().getCcy());
            System.out.println("amt : " + pacs.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmAmt().getValue().floatValue());


        }

/*        MxSwiftMessage mxSwiftMessage = MxSwiftMessage.parse(xml);
        String msgDefIdr = ((BusinessAppHdrV01) mxSwiftMessage.getAppHdr()).getMsgDefIdr();   //camt.029.001.09
        String str = msgDefIdr.replace(".","");
        System.out.println(msgDefIdr);
        System.out.println(str);
        Object obj = (Object) Class.forName("Mx"+ StringUtils.capitalize(str)).newInstance();
        System.out.println(obj);*/


    }


    public String getSampleXml(){
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

    public String getUsXml(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ISOMessage>\n" +
                "    <AppHdr xmlns=\"urn:iso:std:iso:20022:tech:xsd:head.001.001.02\">\n" +
                "        <Fr>\n" +
                "            <FIId>\n" +
                "                <FinInstnId>\n" +
                "                    <BICFI>SCBLUS33XXX</BICFI>\n" +
                "                    <ClrSysMmbId>\n" +
                "                        <ClrSysId>\n" +
                "                            <Cd>USABA</Cd>\n" +
                "                        </ClrSysId>\n" +
                "                        <MmbId>memeidECU</MmbId>\n" +
                "                    </ClrSysMmbId>\n" +
                "                </FinInstnId>\n" +
                "            </FIId>\n" +
                "        </Fr>\n" +
                "        <To>\n" +
                "            <FIId>\n" +
                "                <FinInstnId>\n" +
                "                    <BICFI>SCBLUS33XXX</BICFI>\n" +
                "                    <ClrSysMmbId>\n" +
                "                        <ClrSysId>\n" +
                "                            <Cd>GRBIC</Cd>\n" +
                "                        </ClrSysId>\n" +
                "                        <MmbId>MEMID123</MmbId>\n" +
                "                    </ClrSysMmbId>\n" +
                "                    <LEI>LEI93930938484839302</LEI>\n" +
                "                </FinInstnId>\n" +
                "            </FIId>\n" +
                "        </To>\n" +
                "        <BizMsgIdr>ADV1504GB01</BizMsgIdr>\n" +
                "        <MsgDefIdr>pacs.008.001.08</MsgDefIdr>\n" +
                "        <BizSvc>swift.cbprplus.02</BizSvc>\n" +
                "        <CreDt>2013-06-28T00:07:47+10:00</CreDt>\n" +
                "        <Prty>HIGH</Prty>\n" +
                "\t</AppHdr>\n" +
                "    <Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08\">\n" +
                "        <FIToFICstmrCdtTrf>\n" +
                "            <GrpHdr>\n" +
                "                <MsgId>ADV1504GB01</MsgId>\n" +
                "                <CreDtTm>2013-06-28T00:07:47+10:00</CreDtTm>\n" +
                "                <NbOfTxs>1</NbOfTxs>\n" +
                "                <SttlmInf>\n" +
                "                    <SttlmMtd>INDA</SttlmMtd>\n" +
                "                    <SttlmAcct>\n" +
                "                        <Id>\n" +
                "                            <IBAN>FR1420041010050500013M02606</IBAN>\n" +
                "                        </Id>\n" +
                "                        <Tp>\n" +
                "                            <Cd>ODFT</Cd>\n" +
                "                        </Tp>\n" +
                "                        <Ccy>GBP</Ccy>\n" +
                "                        <Nm>MR. Acc 456</Nm>\n" +
                "                        <Prxy>\n" +
                "                            <Tp>\n" +
                "                                <Cd>DNAM</Cd>\n" +
                "                            </Tp>\n" +
                "                            <Id>BRS 789</Id>\n" +
                "                        </Prxy>\n" +
                "                    </SttlmAcct>\n" +
                "                </SttlmInf>\n" +
                "            </GrpHdr>\n" +
                "            <CdtTrfTxInf>\n" +
                "                <PmtId>\n" +
                "                    <InstrId>ADV1504GB01</InstrId>\n" +
                "                    <EndToEndId>ERS</EndToEndId>\n" +
                "                    <TxId>ADV1504GB01</TxId>\n" +
                "                    <UETR>d9242127-5f69-49d1-9cff-bc42f9f7c0b3</UETR>\n" +
                "                </PmtId>\n" +
                "                <PmtTpInf>\n" +
                "                    <InstrPrty>HIGH</InstrPrty>\n" +
                "                    <SvcLvl>\n" +
                "                        <Cd>URGP</Cd>\n" +
                "                    </SvcLvl>\n" +
                "                    <LclInstrm>\n" +
                "                        <Cd>ARC</Cd>\n" +
                "                    </LclInstrm>\n" +
                "                    <CtgyPurp>\n" +
                "                        <Cd>BONU</Cd>\n" +
                "                    </CtgyPurp>\n" +
                "                </PmtTpInf>\n" +
                "                <IntrBkSttlmAmt Ccy=\"USD\">1000000.00</IntrBkSttlmAmt>\n" +
                "                <IntrBkSttlmDt>2018-11-05</IntrBkSttlmDt>\n" +
                "                <SttlmPrty>URGT</SttlmPrty>\n" +
                "                <ChrgBr>SHAR</ChrgBr>\n" +
                "                <PrvsInstgAgt1>\n" +
                "                    <FinInstnId>\n" +
                "                        <Nm>CBBANK05 ISO FIN Institution</Nm>\n" +
                "                        <PstlAdr>\n" +
                "                            <StrtNm>Street No.1</StrtNm>\n" +
                "                            <BldgNb>Building 555</BldgNb>\n" +
                "                            <Flr>2nd Floor</Flr>\n" +
                "                            <PstCd>500090</PstCd>\n" +
                "                            <TwnNm>Down Town</TwnNm>\n" +
                "                            <Ctry>US</Ctry>\n" +
                "                        </PstlAdr>\n" +
                "                    </FinInstnId>\n" +
                "                </PrvsInstgAgt1>\n" +
                "                <PrvsInstgAgt2>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>LOYDGB21E57</BICFI>\n" +
                "                        <LEI>12345678900987654321</LEI>\n" +
                "                    </FinInstnId>\n" +
                "                </PrvsInstgAgt2>\n" +
                "                <PrvsInstgAgt3>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>LGBAGB22001</BICFI>\n" +
                "                        <LEI>UK716255151441414144</LEI>\n" +
                "                    </FinInstnId>\n" +
                "                </PrvsInstgAgt3>\n" +
                "                <InstgAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>SCBLUS33XXX</BICFI>\n" +
                "                        <ClrSysMmbId>\n" +
                "                            <ClrSysId>\n" +
                "                                <Cd>USABA</Cd>\n" +
                "                            </ClrSysId>\n" +
                "                            <MmbId>memeidECU</MmbId>\n" +
                "                        </ClrSysMmbId>\n" +
                "                    </FinInstnId>\n" +
                "                </InstgAgt>\n" +
                "                <InstdAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>SCBLUS33XXX</BICFI>\n" +
                "                        <ClrSysMmbId>\n" +
                "                            <ClrSysId>\n" +
                "                                <Cd>GRBIC</Cd>\n" +
                "                            </ClrSysId>\n" +
                "                            <MmbId>MEMID123</MmbId>\n" +
                "                        </ClrSysMmbId>\n" +
                "                        <LEI>LEI93930938484839302</LEI>\n" +
                "                    </FinInstnId>\n" +
                "                </InstdAgt>\n" +
                "                <IntrmyAgt1>\n" +
                "                    <FinInstnId>\n" +
                "                        <ClrSysMmbId>\n" +
                "                            <ClrSysId>\n" +
                "                                <Cd>USPID</Cd>\n" +
                "                            </ClrSysId>\n" +
                "                            <MmbId>1234</MmbId>\n" +
                "                        </ClrSysMmbId>\n" +
                "                        <LEI>US717177262615225255</LEI>\n" +
                "                        <Nm>CB Bank 02 ADDRESS</Nm>\n" +
                "                        <PstlAdr>\n" +
                "                            <Dept>TRADE</Dept>\n" +
                "                            <SubDept>FINANCE</SubDept>\n" +
                "                            <StrtNm>DALLAS STREET</StrtNm>\n" +
                "                            <BldgNb>BUILD 432</BldgNb>\n" +
                "                            <BldgNm>KAZIRANGA</BldgNm>\n" +
                "                            <Flr>12</Flr>\n" +
                "                            <PstBx>560100</PstBx>\n" +
                "                            <Room>204</Room>\n" +
                "                            <PstCd>12345</PstCd>\n" +
                "                            <TwnNm>Neo Town</TwnNm>\n" +
                "                            <TwnLctnNm>City Centre</TwnLctnNm>\n" +
                "                            <DstrctNm>District 1</DstrctNm>\n" +
                "                            <CtrySubDvsn>Northern</CtrySubDvsn>\n" +
                "                            <Ctry>GB</Ctry>\n" +
                "                        </PstlAdr>\n" +
                "                    </FinInstnId>\n" +
                "                </IntrmyAgt1>\n" +
                "                <IntrmyAgt2>\n" +
                "                    <FinInstnId>\n" +
                "                        <ClrSysMmbId>\n" +
                "                            <ClrSysId>\n" +
                "                                <Cd>USABA</Cd>\n" +
                "                            </ClrSysId>\n" +
                "                            <MmbId>MEMID342412</MmbId>\n" +
                "                        </ClrSysMmbId>\n" +
                "                        <Nm>Business CBBANK03</Nm>\n" +
                "                        <PstlAdr>\n" +
                "                            <Dept>Trade</Dept>\n" +
                "                            <SubDept>ISO Finance</SubDept>\n" +
                "                            <StrtNm>Street</StrtNm>\n" +
                "                            <BldgNb>112</BldgNb>\n" +
                "                            <BldgNm>Euclayptus</BldgNm>\n" +
                "                            <Flr>122</Flr>\n" +
                "                            <PstBx>560100</PstBx>\n" +
                "                            <Room>1122</Room>\n" +
                "                            <PstCd>88998</PstCd>\n" +
                "                            <TwnNm>Kolar</TwnNm>\n" +
                "                            <TwnLctnNm>Bangalore</TwnLctnNm>\n" +
                "                            <DstrctNm>Bangalore</DstrctNm>\n" +
                "                            <CtrySubDvsn>United States</CtrySubDvsn>\n" +
                "                            <Ctry>US</Ctry>\n" +
                "                        </PstlAdr>\n" +
                "                    </FinInstnId>\n" +
                "                </IntrmyAgt2>\n" +
                "                <IntrmyAgt3>\n" +
                "                    <FinInstnId>\n" +
                "                        <BICFI>LGBAGB22001</BICFI>\n" +
                "                        <LEI>UK716255151441414144</LEI>\n" +
                "                    </FinInstnId>\n" +
                "                </IntrmyAgt3>\n" +
                "                <IntrmyAgt3Acct>\n" +
                "                    <Id>\n" +
                "                        <IBAN>AE070331234567890123456</IBAN>\n" +
                "                    </Id>\n" +
                "                    <Tp>\n" +
                "                        <Cd>SACC</Cd>\n" +
                "                    </Tp>\n" +
                "                    <Ccy>BND</Ccy>\n" +
                "                    <Nm>Name Thanos</Nm>\n" +
                "                </IntrmyAgt3Acct>\n" +
                "                <UltmtDbtr>\n" +
                "                    <Nm>Cash Mgmt 5 (CM5)</Nm>\n" +
                "                    <PstlAdr>\n" +
                "                        <Dept>Dept ! 83939</Dept>\n" +
                "                        <SubDept>Sub and department</SubDept>\n" +
                "                        <StrtNm>Street @ name</StrtNm>\n" +
                "                        <BldgNb>Building (123)</BldgNb>\n" +
                "                        <BldgNm>2nd @ Floor</BldgNm>\n" +
                "                        <Flr>#3rd floor</Flr>\n" +
                "                        <PstBx>post box</PstBx>\n" +
                "                        <PstCd>Post ( 9303*</PstCd>\n" +
                "                        <TwnNm>( New York ) Town name</TwnNm>\n" +
                "                        <TwnLctnNm>Town location $01</TwnLctnNm>\n" +
                "                        <DstrctNm>District Name</DstrctNm>\n" +
                "                        <CtrySubDvsn>Country or Region sub divistion</CtrySubDvsn>\n" +
                "                        <Ctry>US</Ctry>\n" +
                "                    </PstlAdr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <Othr>\n" +
                "                                <Id>CM5 - OTHER IDENTIFICATION</Id>\n" +
                "                                <SchmeNm>\n" +
                "                                    <Cd>TXID</Cd>\n" +
                "                                </SchmeNm>\n" +
                "                                <Issr>Issuer NFS</Issr>\n" +
                "                            </Othr>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </UltmtDbtr>\n" +
                "                <InitgPty>\n" +
                "                    <Nm>Cash Mgmt 5 (CM5)</Nm>\n" +
                "                    <PstlAdr>\n" +
                "                        <Dept>Dept ! 83939</Dept>\n" +
                "                        <SubDept>Sub and department</SubDept>\n" +
                "                        <StrtNm>Street @ name</StrtNm>\n" +
                "                        <BldgNb>Building (123)</BldgNb>\n" +
                "                        <BldgNm>2nd @ Floor</BldgNm>\n" +
                "                        <Flr>#3rd floor</Flr>\n" +
                "                        <PstBx>post box</PstBx>\n" +
                "                        <PstCd>Post ( 9303*</PstCd>\n" +
                "                        <TwnNm>( New York ) Town name</TwnNm>\n" +
                "                        <TwnLctnNm>Town location $01</TwnLctnNm>\n" +
                "                        <DstrctNm>District Name</DstrctNm>\n" +
                "                        <CtrySubDvsn>Country or Region sub divistion</CtrySubDvsn>\n" +
                "                        <Ctry>US</Ctry>\n" +
                "                    </PstlAdr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <Othr>\n" +
                "                                <Id>CM5 - OTHER IDENTIFICATION</Id>\n" +
                "                                <SchmeNm>\n" +
                "                                    <Cd>TXID</Cd>\n" +
                "                                </SchmeNm>\n" +
                "                                <Issr>Issuer NFS</Issr>\n" +
                "                            </Othr>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </InitgPty>\n" +
                "                <Dbtr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <AnyBIC>SBININBBXXX</AnyBIC>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </Dbtr>\n" +
                "                <DbtrAcct>\n" +
                "                    <Id>\n" +
                "                        <IBAN>GB33BUKB20201555555555</IBAN>\n" +
                "                    </Id>\n" +
                "                    <Tp>\n" +
                "                        <Cd>SVGS</Cd>\n" +
                "                    </Tp>\n" +
                "                    <Ccy>USD</Ccy>\n" +
                "                    <Nm>Mr. KTS</Nm>\n" +
                "                    <Prxy>\n" +
                "                        <Tp>\n" +
                "                            <Cd>DNAM</Cd>\n" +
                "                        </Tp>\n" +
                "                        <Id>Trade</Id>\n" +
                "                    </Prxy>\n" +
                "                </DbtrAcct>\n" +
                "                <DbtrAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <ClrSysMmbId>\n" +
                "                            <ClrSysId>\n" +
                "                                <Cd>ATBLZ</Cd>\n" +
                "                            </ClrSysId>\n" +
                "                            <MmbId>MEMID1234354</MmbId>\n" +
                "                        </ClrSysMmbId>\n" +
                "                        <LEI>US111111111111111111</LEI>\n" +
                "                        <Nm>CB Bank 01 Address 3939</Nm>\n" +
                "                        <PstlAdr>\n" +
                "                            <StrtNm>Downstreet</StrtNm>\n" +
                "                            <BldgNb>Amazon</BldgNb>\n" +
                "                            <Flr>12</Flr>\n" +
                "                            <PstCd>1111111111</PstCd>\n" +
                "                            <TwnNm>New York</TwnNm>\n" +
                "                            <Ctry>JP</Ctry>\n" +
                "                        </PstlAdr>\n" +
                "                    </FinInstnId>\n" +
                "                </DbtrAgt>\n" +
                "                <CdtrAgt>\n" +
                "                    <FinInstnId>\n" +
                "                        <ClrSysMmbId>\n" +
                "                            <ClrSysId>\n" +
                "                                <Cd>USPID</Cd>\n" +
                "                            </ClrSysId>\n" +
                "                            <MmbId>1234</MmbId>\n" +
                "                        </ClrSysMmbId>\n" +
                "                        <LEI>US717177262615225255</LEI>\n" +
                "                        <Nm>CB Bank 02 ADDRESS</Nm>\n" +
                "                        <PstlAdr>\n" +
                "                            <Dept>TRADE</Dept>\n" +
                "                            <SubDept>FINANCE</SubDept>\n" +
                "                            <StrtNm>DALLAS STREET</StrtNm>\n" +
                "                            <BldgNb>BUILD 432</BldgNb>\n" +
                "                            <BldgNm>KAZIRANGA</BldgNm>\n" +
                "                            <Flr>12</Flr>\n" +
                "                            <PstBx>560100</PstBx>\n" +
                "                            <Room>204</Room>\n" +
                "                            <PstCd>12345</PstCd>\n" +
                "                            <TwnNm>Neo Town</TwnNm>\n" +
                "                            <TwnLctnNm>City Centre</TwnLctnNm>\n" +
                "                            <DstrctNm>District 1</DstrctNm>\n" +
                "                            <CtrySubDvsn>Northern</CtrySubDvsn>\n" +
                "                            <Ctry>GB</Ctry>\n" +
                "                        </PstlAdr>\n" +
                "                    </FinInstnId>\n" +
                "                </CdtrAgt>\n" +
                "                <CdtrAgtAcct>\n" +
                "                    <Id>\n" +
                "                        <Othr>\n" +
                "                            <Id>Account 1234</Id>\n" +
                "                            <SchmeNm>\n" +
                "                                <Prtry>Prop Acc Id</Prtry>\n" +
                "                            </SchmeNm>\n" +
                "                            <Issr>Issuer Down Town</Issr>\n" +
                "                        </Othr>\n" +
                "                    </Id>\n" +
                "                    <Tp>\n" +
                "                        <Prtry>Acc Type</Prtry>\n" +
                "                    </Tp>\n" +
                "                    <Ccy>CAD</Ccy>\n" +
                "                    <Nm>John Sena</Nm>\n" +
                "                    <Prxy>\n" +
                "                        <Tp>\n" +
                "                            <Prtry>Proponix 99</Prtry>\n" +
                "                        </Tp>\n" +
                "                        <Id>Acc Id</Id>\n" +
                "                    </Prxy>\n" +
                "                </CdtrAgtAcct>\n" +
                "                <Cdtr>\n" +
                "                    <Nm>CM2 ISO</Nm>\n" +
                "                    <PstlAdr>\n" +
                "                        <Dept>Trade Department EDE</Dept>\n" +
                "                        <SubDept>TRD TRP</SubDept>\n" +
                "                        <StrtNm>CM2 Street</StrtNm>\n" +
                "                        <BldgNb>Blgd 4</BldgNb>\n" +
                "                        <BldgNm>RED and Green</BldgNm>\n" +
                "                        <Flr>5th floor</Flr>\n" +
                "                        <PstBx>5000092</PstBx>\n" +
                "                        <PstCd>889989</PstCd>\n" +
                "                        <TwnNm>My Town</TwnNm>\n" +
                "                        <Ctry>US</Ctry>\n" +
                "                    </PstlAdr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <LEI>12345678909887655555</LEI>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </Cdtr>\n" +
                "                <UltmtCdtr>\n" +
                "                    <Nm>CM3 ISO</Nm>\n" +
                "                    <PstlAdr>\n" +
                "                        <Dept>Trade</Dept>\n" +
                "                        <SubDept>SaaS</SubDept>\n" +
                "                        <StrtNm>Street X</StrtNm>\n" +
                "                        <BldgNb>9-108/9</BldgNb>\n" +
                "                        <BldgNm>KGF</BldgNm>\n" +
                "                        <Flr>3RD Floor</Flr>\n" +
                "                        <PstBx>687</PstBx>\n" +
                "                        <Room>909</Room>\n" +
                "                        <PstCd>989890</PstCd>\n" +
                "                        <TwnNm>High Street Town</TwnNm>\n" +
                "                        <TwnLctnNm>Low</TwnLctnNm>\n" +
                "                        <DstrctNm>Dist of Dist</DstrctNm>\n" +
                "                        <CtrySubDvsn>India</CtrySubDvsn>\n" +
                "                        <Ctry>US</Ctry>\n" +
                "                    </PstlAdr>\n" +
                "                    <Id>\n" +
                "                        <OrgId>\n" +
                "                            <AnyBIC>SBININBBXXX</AnyBIC>\n" +
                "                            <LEI>98589789758976894687</LEI>\n" +
                "                            <Othr>\n" +
                "                                <Id>Other Id XYZ</Id>\n" +
                "                                <SchmeNm>\n" +
                "                                    <Cd>CUST</Cd>\n" +
                "                                </SchmeNm>\n" +
                "                                <Issr>Issuer KGF 01</Issr>\n" +
                "                            </Othr>\n" +
                "                        </OrgId>\n" +
                "                    </Id>\n" +
                "                </UltmtCdtr>\n" +
                "                <RgltryRptg>\n" +
                "                    <DbtCdtRptgInd>CRED</DbtCdtRptgInd>\n" +
                "                    <Authrty>\n" +
                "                        <Nm>Harish K</Nm>\n" +
                "                        <Ctry>US</Ctry>\n" +
                "                    </Authrty>\n" +
                "                    <Dtls>\n" +
                "                        <Tp>Type Y</Tp>\n" +
                "                        <Dt>2023-02-05</Dt>\n" +
                "                        <Ctry>GB</Ctry>\n" +
                "                        <Cd>002</Cd>\n" +
                "                        <Amt Ccy=\"USD\">3000.00</Amt>\n" +
                "                        <Inf>Info 3</Inf>\n" +
                "                        <Inf>Info 4</Inf>\n" +
                "                    </Dtls>\n" +
                "                    <Dtls>\n" +
                "                        <Tp>Type X</Tp>\n" +
                "                        <Dt>2023-02-06</Dt>\n" +
                "                        <Ctry>US</Ctry>\n" +
                "                        <Cd>001</Cd>\n" +
                "                        <Amt Ccy=\"USD\">2000.00</Amt>\n" +
                "                        <Inf>Info 1</Inf>\n" +
                "                        <Inf>Info 2</Inf>\n" +
                "                    </Dtls>\n" +
                "                </RgltryRptg>\n" +
                "                <RmtInf>\n" +
                "                    <Ustrd>This text is @ Information Text</Ustrd>\n" +
                "                </RmtInf>\n" +
                "            </CdtTrfTxInf>\n" +
                "        </FIToFICstmrCdtTrf>\n" +
                "    </Document>\n" +
                "</ISOMessage>";
        return  xml;
    }
}
