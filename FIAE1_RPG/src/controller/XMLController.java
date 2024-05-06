package controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.ItemModel;





public class XMLController {
	// Adding Filepaths 
	public String characterInfoFilepath = "res/xml/characterInfo.xml";
	public String inventoryListFilepath = "res/xml/inventoryList.xml";
	public String progressFilepath= "res/xml/progress.xml";
	
	//Example for an ItemModel
	

	
		
	
	public void xmlWriteCharacterInfo(){	
		
	}
	public void xmlReadCharacterInfo() {
		
	}
	public void xmlWriteProgress() {
		
	}
	public void xmlReadProgress() {		
	}
	public void xmlWriteInventoryList(ItemModel itemModel) {
		
		try {
			//Building steps for a Document
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			
			//Adding root Element
			Element rootElement = document.createElement("InventoryList");
			document.appendChild(rootElement);
			
			//Adding name Child and "root" for every attribute
			Element nameChild = document.createElement(itemModel.getItemName());
			rootElement.appendChild(nameChild);
			
			//Adding durability Child
			Element durabilityChild = document.createElement("Durability");
			durabilityChild.appendChild(document.createTextNode(String.valueOf(itemModel.getItemDurability())));
			nameChild.appendChild(durabilityChild);
			
			//Adding quantity Child
			Element quantityChild = document.createElement("Quantity");
			quantityChild.appendChild(document.createTextNode(String.valueOf(itemModel.getItemQuantity())));
			nameChild.appendChild(quantityChild);
			
			//Adding price Child
			Element priceChild = document.createElement("Price");
			priceChild.appendChild(document.createTextNode(String.valueOf(itemModel.getItemPrice())));
			nameChild.appendChild(priceChild);
			
			//Adding damage Child
			Element damageChild = document.createElement("Damage");
			damageChild.appendChild(document.createTextNode(String.valueOf(itemModel.getItemDamage())));
			nameChild.appendChild(damageChild);
			
			//Adding isQuestItem Child
			Element isQuestItemChild = document.createElement("QuestItem");
			isQuestItemChild.appendChild(document.createTextNode(String.valueOf(itemModel.isQuestItem())));
			nameChild.appendChild(isQuestItemChild);
			
			//Adding category Child
			Element categoryChild = document.createElement("Category");
			categoryChild.appendChild(document.createTextNode(String.valueOf(itemModel.getCategory())));
			nameChild.appendChild(categoryChild);
			
			
			// Write Content into the File
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File("res/xml/formatierung.xslt")));
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(inventoryListFilepath));
			transformer.transform(source, result);
			
			
			// Console output
			StreamResult consoleResult = new StreamResult(System.out);
	        transformer.transform(source, consoleResult);
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void xmlReadInventoryList() {
		
	}








}



