package net.sf.jColtrane.handler;


import org.xml.sax.Attributes;

public class SAXEvent {
	
	private String uri;
	private String tag;
	private String localName;
	private AttributesHolder atributes;
	public SAXEvent(String uri, String tag,String localName, 
			Attributes atributes) {
		this.uri=uri;
		this.localName=localName;
		this.tag=tag;
		this.atributes = new AttributesHolder(atributes);
		
	}
	
	public AttributesHolder getAtributesHolder() {
		return atributes;
	}
	

	

	public String getUri() {
		return uri;
	}

	

	public String getTag() {
		return tag;
	}

	

	public String getLocalName() {
		return localName;
	}

	
}