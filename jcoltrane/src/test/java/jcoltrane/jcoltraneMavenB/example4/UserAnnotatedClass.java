package jcoltrane.jcoltraneMavenB.example4;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.StartElement;

public class UserAnnotatedClass {
	@UserAnnotation(currentBranch="/.*/line.*")
	@StartElement
	public void executeUserTest(@CurrentBranch String currentBranch){
		System.out.println(currentBranch);
		System.out.println("executing test!!!!!");
	}
}
