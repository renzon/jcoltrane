package net.sf.jColtrane.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;

public class AttributesHolder{
	private Map <String,String>attributes;
	public AttributesHolder(Attributes attributes) {
		this.attributes=new HashMap<String, String>();
		for(int i=0;i<attributes.getLength();++i){
			this.attributes.put(attributes.getQName(i), attributes.getValue(i));
		}
	}
	
	public Map<String, String> getAttributeMap(){
		return attributes;
	}
	
	public Set<String> getQNames(){
		return this.attributes.keySet();
	}

	public String getValue(String qName) {
		
		return this.attributes.get(qName);
	}

	

}
