package example1;

import net.sf.jColtrane.annotations.methods.EndDocument;
import net.sf.jColtrane.annotations.methods.EndElement;
import net.sf.jColtrane.annotations.methods.StartDocument;
import net.sf.jColtrane.annotations.methods.StartElement;

public class HelloWorld { 

	@StartDocument 
	public void executeInStartDocument(){ 
		System.out.println("Hello World!!!\n"); 
	} 

	@EndDocument 
	public void executeInEndDocument(){ 
		System.out.println("Bye bye World!!!\n"); 
	} 

	@StartElement 
	public void executeInStartElement(){ 
		System.out.println("Executing something in start element\n"); 
	} 

	@EndElement 
	public void executeInEndElement(){ 
		System.out.println("Executing something in end element\n"); 
	} 

}


