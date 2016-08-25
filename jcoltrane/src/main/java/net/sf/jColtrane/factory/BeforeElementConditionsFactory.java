package net.sf.jColtrane.factory;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.annotations.methods.BeforeElement;
import net.sf.jColtrane.annotations.methods.ContainAttribute;
import net.sf.jColtrane.conditions.Condition;

public class BeforeElementConditionsFactory extends ElementConditionsFactory{

	@Override
	public List<Condition> getConditions(Annotation annotation) {
		if(annotation instanceof BeforeElement){
			List<Condition> conditions=new LinkedList<Condition>();
			BeforeElement beforeElement=(BeforeElement) annotation;
			if(!beforeElement.uri().equals(""))
				conditions.add(super.buildUriCondition(beforeElement.uri(), beforeElement.elementDeep()));
			if(!beforeElement.tag().equals(""))
				conditions.add(super.buildTagCondition(beforeElement.tag(), beforeElement.elementDeep()));
			if(!beforeElement.localName().equals(""))
				conditions.add(super.buildLocalNameCondition(beforeElement.localName(),beforeElement.elementDeep()));
						
			if(beforeElement.attributes()!=null){
				for(ContainAttribute atr: beforeElement.attributes()){
					if(atr!=null&&(!atr.name().equals(".*")||!atr.value().equals(".*"))){
						conditions.add(super.buildAtributteCondition(atr.name(),atr.value(),beforeElement.elementDeep()));
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
