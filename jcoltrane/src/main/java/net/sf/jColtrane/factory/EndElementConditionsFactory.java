package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.annotations.methods.ContainAttribute;
import net.sf.jColtrane.annotations.methods.EndElement;
import net.sf.jColtrane.conditions.Condition;

public class EndElementConditionsFactory extends ElementConditionsFactory{
	static private final int LAST_EVENT_DEEP=0;

	@Override
	public List<Condition> getConditions(Annotation annotation) {
		if(annotation instanceof EndElement){
			List<Condition> conditions=new LinkedList<Condition>();
			EndElement endElement=(EndElement) annotation;
			if(!endElement.uri().equals(""))
				conditions.add(super.buildUriCondition(endElement.uri(), LAST_EVENT_DEEP));
			if(!endElement.tag().equals(""))
				conditions.add(super.buildTagCondition(endElement.tag(), LAST_EVENT_DEEP));
			if(!endElement.localName().equals(""))
				conditions.add(super.buildLocalNameCondition(endElement.localName(), LAST_EVENT_DEEP));
			
			if(endElement.attributes()!=null){
				for(ContainAttribute atr: endElement.attributes()){
					if(atr!=null&&(!atr.name().equals(".*")||!atr.value().equals(".*"))){
						conditions.add(super.buildAtributteCondition(atr.name(),atr.value(),LAST_EVENT_DEEP));
					}
				}
			}
			
			if(conditions.size()==0)
				return null;
			else
				return conditions;
		}
		return null;
	}

}
