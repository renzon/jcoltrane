package jcoltrane.jcoltraneMavenB.example5;

import java.util.Date;

import net.sf.jColtrane.annotations.methods.EndElement;
import net.sf.jColtrane.annotations.methods.StartElement;
import net.sf.jColtrane.handler.ContextVariables;
import net.sf.jColtrane.handler.SAXEvent;

public class ContextVariableReceiver {
	int key=0;
	@StartElement
	public void executeUserStartTest(ContextVariables contextVariables){
		System.out.println("\n\nExecuting in start element");
		System.out.println(contextVariables.getCurrentBranch());
		SAXEvent event=contextVariables.getLastEvent();
		System.out.println("Current tag: "+event.getTag());
		if(event!=null){
			for(String qName: event.getAtributesHolder().getQNames()){
				System.out.println("Attribute name: "+qName+"\nvalue: "+event.getAtributesHolder().getValue(qName));
			}
		}
		SAXEvent beforeEvent=contextVariables.getEvent(1);
		if(beforeEvent!=null)
			System.out.println("\nBefore tag: "+beforeEvent.getTag());
		++key;
		contextVariables.putInGeneralUseMap(Integer.toString(key), new Date());
	}
	
	@EndElement
	public void executeUserEndTest(ContextVariables contextVariables){
		System.out.println("\n\nExecuting in end element");
		System.out.println(contextVariables.getCurrentBranch());
		SAXEvent event=contextVariables.getLastEvent();
		System.out.println("Current tag: "+event.getTag());
		if(event!=null){
			for(String qName: event.getAtributesHolder().getQNames()){
				System.out.println("Attribute name: "+qName+"\nvalue: "+event.getAtributesHolder().getValue(qName));
			}
		}
		SAXEvent beforeEvent=contextVariables.getEvent(1);
		if(beforeEvent!=null)
			System.out.println("\nBefore tag: "+beforeEvent.getTag());
		Date date=(Date) contextVariables.getGeneralUseMap().get(Integer.toString(key));
		System.out.println(date.getTime());
		--key;
		
	}
}
