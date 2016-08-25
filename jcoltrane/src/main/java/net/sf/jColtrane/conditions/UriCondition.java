package net.sf.jColtrane.conditions;

import java.util.regex.Pattern;

import net.sf.jColtrane.handler.ContextVariables;
import net.sf.jColtrane.handler.SAXEvent;

public class UriCondition extends BasicCondition implements Condition {

	private Pattern pattern;
	private int elementDeep;
	
	

	public UriCondition(String regularExpression, int elementDeep) {
		pattern=Pattern.compile(regularExpression);
		this.elementDeep=elementDeep;
	}
	
	public void setElementDeep(int elementDeep) {
		this.elementDeep = elementDeep;
	}




	@Override
	public boolean verify(ContextVariables contextVariables) {
		SAXEvent e=contextVariables.getEvent(elementDeep);
		if(e!=null)
			return pattern.matcher(e.getUri()).matches();
		return false;
	}

}
