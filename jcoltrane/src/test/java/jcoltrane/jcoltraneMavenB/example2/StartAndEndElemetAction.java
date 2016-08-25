package jcoltrane.jcoltraneMavenB.example2;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.EndElement;
import net.sf.jColtrane.annotations.methods.StartElement;

public class StartAndEndElemetAction {
	@StartElement(tag="line")
	public void executeInStartElement(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in start element\n");
	}

	@EndElement(tag="property.*")
	public void executeInEndElement(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("Executing something in end element\n");
	}
}
