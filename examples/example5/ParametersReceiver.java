package example5;

import java.util.Map;

import net.sf.jColtrane.annotations.args.Attribute;
import net.sf.jColtrane.annotations.args.AttributeMap;
import net.sf.jColtrane.annotations.args.Body;
import net.sf.jColtrane.annotations.args.CurrentBranch;
import net.sf.jColtrane.annotations.args.Tag;
import net.sf.jColtrane.annotations.methods.StartElement;

public class ParametersReceiver {

	@StartElement
	public void executeUserStartTest(@CurrentBranch String currentBranch,
			@Attribute("page") int pageNumber,
			@AttributeMap Map<String, String> attributeMap,
			@Tag String tag,
			@Body(newLine=false) String body){
		System.out.println();
		System.out.println(currentBranch);
		System.out.println("current tag: "+tag);
		System.out.println("page number: "+pageNumber);
		System.out.println("Att Map: "+attributeMap);
		System.out.println("Body"+body);
	}
	
	
}
