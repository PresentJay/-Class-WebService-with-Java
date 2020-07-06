import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.Class;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class SOAPManager {

	private String requestMessageBody;
	private String requestMessageBody1;
	private String requestMessageBody2;
	private static final String startBody="<env:Body>"+"\n";
	private static final String endBody="</env:Body>"+"\n";
	private static final String endEnvelope="</env:Envelope>" +"\n";
	private String startOperationName;
	private String endOperationName;
	private ArrayList<String> parameters;
	private ArrayList<String> parametersRef;
	private String requestMessage;
	private StringBuffer responseMessage;

	private String contentType;
	private String serviceURL;

	private ArrayList<DataObject> objectList;
	private Element rootNode;
	private HttpURLConnection connection;


	public SOAPManager() {

		requestMessageBody1=
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ "\n\n"+
					"<env:Envelope" +
			   		"  xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\""	+ "\n" +
			   		"  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" + "\n" 		+
			   		"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" 	+ "\n" 		+
			   		"  xmlns:enc=\"http://schemas.xmlsoap.org/soap/encoding/\"" + "\n";

		requestMessageBody2=
			   		"  env:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" 		+ "\n\n" 	+
			   		" <env:Header/>" + "\n";

		parameters = new ArrayList<String>();
		parametersRef = new ArrayList<String>();
	}


	public void setURL(String serviceURL) {
		this.serviceURL=serviceURL;
	}

	public void setNamespace(String serviceNamespace) {

		String namespaceURL="";

		if(serviceNamespace!=null)
			namespaceURL=" xmlns:ns0=\""+serviceNamespace+"\"" +"\n";
		else
			namespaceURL=" xmlns:ns0=\"http://platform.silla.ac.kr/smartad/default\"" +"\n";

		requestMessageBody = requestMessageBody1+namespaceURL+requestMessageBody2;
	}

	public void setOperation(String opName) {
		startOperationName="<ns0:"+opName+">" +"\n";
		endOperationName="</ns0:"+opName+">" +"\n";

		contentType="text/xml; charset=\"UTF-8\"";
	}

	public void setParameter(String sequence, String value) throws Exception {
		parameters.add("<arg"+sequence+">"+value+"</arg"+sequence+">\n");
	}

	public void setParameter(String sequence, ArrayList<String> value) throws Exception {

		for(int i=0;i<value.size();i++)
			this.setParameter(sequence,value.get(i));
	}

	public void setParameter(String sequence, Object object) throws Exception {
		String fullObjectNames [] = object.getClass().getName().split("\\.");
		String objectName = fullObjectNames[fullObjectNames.length-1];

		String parameter = "<arg"+sequence+" xsi:type=\"ns0:"+objectName+"\">";

		Field[] fields = object.getClass().getDeclaredFields();
		String fieldName = "";
		String fieldValue = "";

		for (int i = 0; i < fields.length; i++) {
			fieldName = fields[i].getName();
			if(fields[i].get(object)!=null) {
				fieldValue = fields[i].get(object).toString();
				parameter=parameter+"<"+fieldName+">"+fieldValue+"</"+fieldName+">";
			}
		}
		parameter=parameter+"</arg"+sequence+">\n";
		parameters.add(parameter);
	}

	public void setParameterArrayObject(String sequence, ArrayList list) throws Exception{
		for(int i=0; i<list.size(); i++) {
			this.setParameter(sequence,list.get(i));
		}
	}


	public String getRequestMessage() throws Exception {
		requestMessage=requestMessageBody + startBody;

		if(startOperationName!=null)
			requestMessage=requestMessage+startOperationName;

		if(parameters.size()!=0)
			for(int i=0;i<parameters.size();i++)
				requestMessage=requestMessage+parameters.get(i);

		requestMessage=requestMessage+endOperationName;

		if(parametersRef.size()!=0)
			for(int i=0;i<parametersRef.size();i++)
				requestMessage=requestMessage+parametersRef.get(i);

		requestMessage=requestMessage+endBody+endEnvelope;

		return requestMessage;
	}


	public String getResponseMessage() throws Exception {
		this.action();
		responseMessage = new StringBuffer();
		InputStream in = connection.getInputStream();

		byte data[] = new byte[4096];
		int size;
		while((size=in.read(data))!=-1) {
			String str = new String(data,0,size,"UTF-8");
			str=str.replaceAll("<","\n<");
			responseMessage.append(str);
		}

		return responseMessage.toString();
	}

	public ArrayList<DataObject> send() throws Exception {
		this.action();
		this.soapMessageParser(connection.getInputStream());
		return objectList;
	}

// Request 메시지를 서버로 보냄
	private void action() throws Exception {
		this.getRequestMessage();

		URL url = new URL(serviceURL);
		connection = (HttpURLConnection)url.openConnection();

		connection.setDoOutput(true);
		connection.setRequestMethod("POST");

		connection.setRequestProperty("Content-Type","text/xml; charset=\"utf-8\"");
		connection.setRequestProperty("Target-Host",serviceURL);

		OutputStream out = connection.getOutputStream();

		byte data[] = requestMessage.getBytes("utf-8");
		out.write(data,0,data.length);
		out.flush();
		out.close();
	}


// response message에 대한 XML 파싱

	private void soapMessageParser(InputStream in) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		Document doc = parser.parse(in);
		rootNode=doc.getDocumentElement();

		Node returnNode = this.getNode("return");

		objectList = new ArrayList<DataObject>();

		while(returnNode!=null) {
			if(returnNode.getNodeType()==Node.ELEMENT_NODE)
				objectList.add(new DataObject(returnNode));
			returnNode=returnNode.getNextSibling();
		}
	}

	private Node getNode(String nodeName) {
		Node node = rootNode;

		while((node=getFirstChildNodeTypeNode(node))!=null) {
			if(node.getNodeName().equals(nodeName))
				return node;
		}

		return null;
	}

	private Node getFirstChildNodeTypeNode(Node node) {
		if(node.getChildNodes().getLength()<1)
			return null;

		node=node.getFirstChild();
		while(node.getNodeType()!=Node.ELEMENT_NODE)
			node=node.getNextSibling();

		return node;
	}
}