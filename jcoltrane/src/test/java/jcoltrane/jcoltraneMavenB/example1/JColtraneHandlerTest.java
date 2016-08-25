package jcoltrane.jcoltraneMavenB.example1;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.sf.jColtrane.handler.JColtraneXMLHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

 

public class JColtraneHandlerTest { 

      public static void main(String[] args) { 
            SAXParser parser=null; 

            try { 
                  parser= SAXParserFactory.newInstance().newSAXParser(); 
            } catch (ParserConfigurationException e) { 
                  e.printStackTrace(); 
            } catch (SAXException e) { 
                  e.printStackTrace(); 
            } 

            File file=new File("src/test/java/jcoltrane/jcoltraneMavenB/example1/Example1.xml"); 
            if(parser!=null){ 
                  InputSource input=new InputSource(file.getAbsolutePath()); 
                  try { 
                        parser.parse(input,new JColtraneXMLHandler(new HelloWorld())); 
                  } catch (SAXException e) { 
                        e.printStackTrace(); 
                  } catch (IOException e) { 
                        e.printStackTrace(); 
                  } 
            } 
      } 
}

