package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.annotations.methods.ContainAttribute;
import net.sf.jColtrane.annotations.methods.StartElement;
import net.sf.jColtrane.conditions.Condition;

public class StartElementConditionsFactory extends ElementConditionsFactory{
	static private final int LAST_EVENT_DEEP=0;

	@Override
	public List<Condition> getConditions(Annotation annotation) {
		if(annotation instanceof StartElement){
			List<Condition> conditions=new LinkedList<Condition>();
			StartElement startElement=(StartElement) annotation;
			if(!startElement.uri().equals(""))
				conditions.add(super.buildUriCondition(startElement.uri(), LAST_EVENT_DEEP));
			if(!startElement.tag().equals(""))
				conditions.add(super.buildTagCondition(startElement.tag(), LAST_EVENT_DEEP));
			if(!startElement.localName().equals(""))
				conditions.add(super.buildLocalNameCondition(startElement.localName(), LAST_EVENT_DEEP));
			if(startElement.attributes()!=null){
				for(ContainAttribute atr: startElement.attributes()){
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
