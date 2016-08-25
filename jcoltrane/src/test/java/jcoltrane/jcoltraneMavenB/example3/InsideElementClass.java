package jcoltrane.jcoltraneMavenB.example3;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.InsideElement;
import net.sf.jColtrane.annotations.methods.StartElement;

public class InsideElementClass {
	@InsideElement(tag="line")
	@StartElement
	public void executeInStartElement(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in start element\n");
	}
}
