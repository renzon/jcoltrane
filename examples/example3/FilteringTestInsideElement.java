package example3;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.sf.jColtrane.handler.JColtraneXMLHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class FilteringTestInsideElement {
	public static void main(String[] args) {
		SAXParser parser=null;
		try {
			parser= SAXParserFactory.newInstance().newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		File file=new File("examples\\example3\\Example3.xml");
		if(parser!=null){
			InputSource input=new InputSource(file.getAbsolutePath());
			try {
				parser.parse(input,new JColtraneXMLHandler(new InsideElementClass()));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}


