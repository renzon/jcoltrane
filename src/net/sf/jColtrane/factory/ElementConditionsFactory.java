package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.util.List;

import net.sf.jColtrane.conditions.AttributeCondition;
import net.sf.jColtrane.conditions.Condition;
import net.sf.jColtrane.conditions.LocalNameCondition;
import net.sf.jColtrane.conditions.TagCondition;
import net.sf.jColtrane.conditions.UriCondition;

public abstract class ElementConditionsFactory implements ConditionsFactory {

	@Override
	abstract public List<Condition> getConditions(Annotation annotation); 
	
	protected Condition buildLocalNameCondition(String localName,int elementDeep) {
		if(localName.equals("")){
			return null;
		}
		return new LocalNameCondition(localName,elementDeep);
	}

	protected TagCondition buildTagCondition(String tag,int elementDeep){
		if(tag.equals("")){
			return null;
		}
		return new TagCondition(tag,elementDeep);
	}
	
	protected UriCondition buildUriCondition(String uri,int elementDeep){
		if(uri.equals("")){
			return null;
		}
		return new UriCondition(uri,elementDeep);	
	}
	
	protected AttributeCondition buildAtributteCondition(String name,String value, int elementDeep){
		
		return new AttributeCondition(name, value, elementDeep);
	}


}
