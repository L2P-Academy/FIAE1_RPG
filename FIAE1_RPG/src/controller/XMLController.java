package controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLController {

		
	public void xmlWriteCharacterInfo(){	
		
		//creating a xml Document Builder from the DocumentBuilderFactory
				//try catch is neccessary 
				//the first 3 prompts creates the needed tree structure in the document
				try {
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();	
					Document document = docBuilder.newDocument();
					
					//Adding Root Element 
					Element rootElement = document.createElement("characterInfo");
					document.appendChild(rootElement);
					
					//Adding several Elements
					Element name = document.createElement("Name");
					name.appendChild(document.createTextNode("Chris"));
					rootElement.appendChild(name);
					
					Element healthElement = document.createElement("HealthPoints");
					healthElement.appendChild(document.createTextNode("100"));
					rootElement.appendChild(healthElement);
					
					
					
					
//					//write the content into the xml File
//					TransformerFactory tFactory = TransformerFactory.newInstance();
//					Transformer transformer = tFactory.newTransformer();
//					DOMSource source = new DOMSource(document);
//					StreamResult result = new StreamResult(new File("E:\\Projects\\workspace\\dnd_Testing\\characterInfo.xml"));
//					transformer.transform(source, result);
//					
//					 // Output to console for testing
//			         StreamResult consoleResult = new StreamResult(System.out);
//			         transformer.transform(source, consoleResult);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
	}
	public void xmlReadCharacterInfo() {
		
	}
	public void xmlWriteProgress() {
		
	}
	public void xmlReadProgress() {		
	}
	public void xmlWriteInventoryList() {
		
	}
	public void xmlReadInventoryList() {
		
	}








}



