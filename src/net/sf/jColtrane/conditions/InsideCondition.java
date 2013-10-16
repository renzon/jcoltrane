package net.sf.jColtrane.conditions;

import java.util.LinkedList;
import java.util.List;

import net.sf.jColtrane.handler.ContextVariables;
import net.sf.jColtrane.handler.SAXEvent;

public class InsideCondition implements Condition {

	private TagCondition tagCondition=null;
	private UriCondition uriCondition=null;
	private LocalNameCondition localNameCondition=null;
	private List <AttributeCondition> attributeConditionList=null;
	
	public InsideCondition(String tagExpression,String uriExpression,String localNameExpression) {
		
		if(!tagExpression.equals(""))
			tagCondition=new TagCondition(tagExpression,0);
		if(!uriExpression.equals(""))
			uriCondition=new UriCondition(uriExpression,0);
		if(!tagExpression.equals(""))
			localNameCondition=new LocalNameCondition(localNameExpression,0);
	}
	
	public void addAttributeCondition(String qName,String value){
		if(!qName.equals(".*")||!value.equalsIgnoreCase(".*")){
			if(attributeConditionList==null)
				attributeConditionList=new LinkedList<AttributeCondition>();
			attributeConditionList.add(new AttributeCondition(qName,value,0));
		}
	}

	@Override
	public boolean verify(ContextVariables contextVariables) {
		//System.out.println("entrou");
		SAXEvent e;
		int elementDeep=0;
		do{
			e=contextVariables.getEvent(elementDeep);
			this.setElementDeep(elementDeep);
			
			if(e!=null){
				if(verifyAll(contextVariables))
					return true;
					


				++elementDeep;
			}
		}while(e!=null);
		
		return false;
	}
	
	private boolean verifyAll(ContextVariables contextVariables){
		if(this.verify(tagCondition,contextVariables)&&
				this.verify(uriCondition,contextVariables)&&
				this.verify(localNameCondition,contextVariables)){
			if(attributeConditionList!=null){
				for(AttributeCondition attCondition: attributeConditionList){
					if(!this.verify(attCondition,contextVariables))
						return false;
				}
			}
			
			return true;
		}
		return false;
	}

	private boolean verify(Condition condition,ContextVariables contextVariables) {
		if(condition!=null){
			
			return condition.verify(contextVariables);
		}
		return true;
	}

	
	public void setElementDeep(int elementDeep) {
		if(tagCondition!=null)
			tagCondition.setElementDeep(elementDeep);
		if(uriCondition!=null)
			uriCondition.setElementDeep(elementDeep);
		if(localNameCondition!=null)
			localNameCondition.setElementDeep(elementDeep);
		if(attributeConditionList!=null){
			for(AttributeCondition attCondition: attributeConditionList)
				attCondition.setElementDeep(elementDeep);
		}
		
	}

	


	

}
