package net.sf.jColtrane.handler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ContextVariables {
	private List <SAXEvent> eventsList;
	private StringBuilder body;
	private Map <String,Object>generalUseMap;
	


	public ContextVariables() {
		eventsList=new LinkedList<SAXEvent>();
		body=new StringBuilder();
		generalUseMap=new HashMap<String, Object>();
	}
	
	public String getBody(){
		return body.toString();
	}
	
	protected void appendOnBody(char[] body){
		int garbage=0;
//		 this method is used to build the body of XML Documents
//		 It exclude any kind of whitespace that come before the body
		 
		if(this.body.length()==0){
			boolean findValidChar=false;
			while(garbage<body.length&&!findValidChar){
				if(Character.isWhitespace(body[garbage]))
					++garbage;
				else
					findValidChar=true;
			}
		}
		if(garbage!=body.length)
			this.body.append(body,garbage,body.length-garbage);
	}
	
	protected void eraseBody(){
		this.body.delete(0, body.length());
	}
	
	protected void pushEvent(SAXEvent event){
		eventsList.add(event);
	}
	
	protected SAXEvent popEvent(){
		if(eventsList.size()>0)
			return eventsList.remove(eventsList.size()-1);
		return null;
	}
	
	public SAXEvent getLastEvent(){
		
		return getEvent(0);
	}
	
	public String getCurrentBranch(){
		StringBuilder str=new StringBuilder("/");
		for(SAXEvent el:eventsList){
			str.append(el.getTag()+"/");
		}
		
		return str.toString();
	}
	
	public SAXEvent getEvent(int deep){
		if(deep>=0&&eventsList.size()>deep)
			return eventsList.get(eventsList.size()-deep-1);
		return null;
	}

	public Map<String, Object> getGeneralUseMap() {
		return generalUseMap;
	}

	public void putInGeneralUseMap(String key,Object obj) {
		this.generalUseMap.put(key, obj);
	}
	

	

}
