package net.sf.jColtrane.handler;


import java.util.HashMap;
import java.util.Map;

import net.sf.jColtrane.descriptor.ExecutionDescriptor;
import net.sf.jColtrane.factory.ExecutionDescriptorFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;




public class JColtraneXMLHandler extends DefaultHandler {
	private Map <Object,ExecutionDescriptor> descriptorMap;
	private ContextVariables contextVariables=null;
	public JColtraneXMLHandler(Object... targetObjects) {
		super();
		this.descriptorMap=new HashMap<Object,ExecutionDescriptor>();
		for(Object obj: targetObjects){
			descriptorMap.put(obj, ExecutionDescriptorFactory.getExecutionDescriptor(obj.getClass()));
		}
		
		
		this.contextVariables=new ContextVariables();	
	}
	
	
	

	
	
	public void startDocument(){
		for(Object obj:descriptorMap.keySet()){
			descriptorMap.get(obj).executeStartDocumentMethods(obj);
		}
	}
	
	public void endDocument(){
		for(Object obj:descriptorMap.keySet()){
			descriptorMap.get(obj).executeEndDocumentMethods(obj);
		}
		
	}
	
	
	
	
	public void startElement(String uri,String localName,String tag,Attributes atributes)throws SAXException{
		
		contextVariables.pushEvent(new SAXEvent(uri, tag, localName, atributes));
		for(Object obj:descriptorMap.keySet()){
			descriptorMap.get(obj).executeStartElementMethods(obj,contextVariables);
		}
		
		
	}
	
	public void endElement(String uri,String localName,String tag)throws SAXException{
		
		for(Object obj:descriptorMap.keySet()){
			descriptorMap.get(obj).executeEndElementMethods(obj,contextVariables);
		}
		contextVariables.popEvent();
		contextVariables.eraseBody();
		
	}
	
	public void characters(char[] ch, int start, int lenght)throws SAXException{
		char body[]=new char[lenght];
		for(int i=0;i<body.length;++i){
			body[i]=ch[start+i];
		}
		contextVariables.appendOnBody(body);
	}


	
	
	
	
}
