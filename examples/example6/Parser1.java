package example6;

import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.methods.StartElement;

public class Parser1 {
	@StartElement(tag="line")
	public void parse(@CurrentBranch String branch){
		System.out.println(branch);
		System.out.println("execute some action");
	}
}
