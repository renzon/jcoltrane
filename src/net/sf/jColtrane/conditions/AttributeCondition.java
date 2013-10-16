package net.sf.jColtrane.conditions;

import java.util.regex.Pattern;

import net.sf.jColtrane.handler.ContextVariables;
import net.sf.jColtrane.handler.SAXEvent;

public class AttributeCondition extends BasicCondition implements Condition {

	private Pattern patternName;
	private Pattern patternValue;
	private int elementDeep;
	 
	
	

	public AttributeCondition(String name, String value,int elementDeep) {
		patternName=Pattern.compile(name);
		patternValue=Pattern.compile(value);
		this.elementDeep=elementDeep;
	}
	
	public void setElementDeep(int elementDeep) {
		this.elementDeep = elementDeep;
	}




	@Override
	public boolean verify(ContextVariables contextVariables) {
		
		SAXEvent e=contextVariables.getEvent(elementDeep);
		if(e!=null){
			for(String qName: e.getAtributesHolder().getQNames()){
				if(patternName.matcher(qName).matches()&&patternValue.matcher(e.getAtributesHolder().getValue(qName)).matches()){
					return true;
				}
			}
		}
		return false;
	}

}
