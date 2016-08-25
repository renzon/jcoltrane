package jcoltrane.jcoltraneMavenB.example1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler { 

    private StringBuilder currentBranch = new StringBuilder("/");
	private StringBuilder body = new StringBuilder();

	public void startDocument() {
		System.out.println("Starting Document: Hello World!!!\n");
	}

	public void endDocument() {
		System.out.println("End Document: Bye bye World!!!\n");
	}

	public void startElement(String uri, String localName, String tag,
			Attributes atributos) throws SAXException {
		currentBranch.append(tag + "/");
		System.out.println(currentBranch.toString());
		// user action
		System.out.println("Executing some action in start element\n");
		body.delete(0, body.length());
	}

	public void endElement(String uri, String localName, String tag)
			throws SAXException {
		if (!body.toString().equals("\n") && body.length() != 2)
			System.out.println(body.toString().trim());
		System.out.println(currentBranch);
		body.delete(0, body.length());
		// user action
		System.out.println("Executing some action in end element\n");
		currentBranch.delete(currentBranch.length() - tag.length() - 1,
				currentBranch.length());
	}

	public void characters(char[] ch, int start, int lenght)
			throws SAXException {
		body.append(ch, start, lenght);
	}
} 