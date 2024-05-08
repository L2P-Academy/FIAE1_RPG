package controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.ItemModel;





public class XMLController {
	
	// Adding Filepaths 
	private String characterInfoFilepath = "res/xml/characterInfo.xml";
	private String inventoryListFilepath = "res/xml/inventoryList.xml";
	private String progressFilepath= "res/xml/progress.xml";
	
	// Adding Existens Varriable for Verafication in Write Methods
	private File fileExistsCharacterInfo = new File(characterInfoFilepath);
	private File fileExistsInventoryList = new File(inventoryListFilepath);
	private File fileExistsProgress = new File(progressFilepath);
	
		
	
	public void xmlWriteCharacterInfo(){	
		
	}
	public void xmlReadCharacterInfo() {
		
	}
	public void xmlWriteProgress() {
		
	}
	public void xmlReadProgress() {		
	}
	public void xmlWriteInventoryList(ArrayList<ItemModel> itemList) {
		
		//create a xml File If no exists
		if(!fileExistsInventoryList.exists()) {
			try {
				File inventoryList = new File(inventoryListFilepath);				
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
				
			try {
				//Building steps for a Document
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.newDocument();
							
				//Adding root Element
				Element rootElement = document.createElement("InventoryList");
				document.appendChild(rootElement);
				
				//Loop for every Item in the itemList				
				for(int i = 0 ; i < itemList.size(); i++) {
					//Varriable die mit der Forschleife hochzählt, damit jedes Item der Liste erfasst wird.
					ItemModel currentItem = itemList.get(i);
				
					//Adding name Child and "root" for every attribute
					Element nameChild = document.createElement(currentItem.getItemName());
					rootElement.appendChild(nameChild);
					
					//Adding durability Child
//					Element durabilityChild = document.createElement("Durability");
//					durabilityChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemDurability())));
//					nameChild.appendChild(durabilityChild);
					//Adding quantity Child
					Element quantityChild = document.createElement("Quantity");
					quantityChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemQuantity())));
					nameChild.appendChild(quantityChild);
					
					//Adding price Child
					Element priceChild = document.createElement("Price");
					priceChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemPrice())));
					nameChild.appendChild(priceChild);
					
					//Adding damage Child
					Element damageChild = document.createElement("Damage");
					damageChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemDamage())));
					nameChild.appendChild(damageChild);
					
					//Adding isQuestItem Child
					Element isQuestItemChild = document.createElement("QuestItem");
					isQuestItemChild.appendChild(document.createTextNode(String.valueOf(currentItem.getIsQuestItem())));
					nameChild.appendChild(isQuestItemChild);
				
//					//Adding category Child
//					Element categoryChild = document.createElement("Category");
//					categoryChild.appendChild(document.createTextNode(String.valueOf(currentItem.getCategory())));
//					nameChild.appendChild(categoryChild);
				
					}
					// Write Content into the File
					TransformerFactory tFactory = TransformerFactory.newInstance();
					Transformer transformer = tFactory.newTransformer(new StreamSource(new File("res/xml/formatierung.xslt")));
					DOMSource source = new DOMSource(document);
					StreamResult result = new StreamResult(new File(inventoryListFilepath));
					transformer.transform(source, result);
					
					
					// Console output
//					StreamResult consoleResult = new StreamResult(System.out);
//			        transformer.transform(source, consoleResult);
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	
	public void xmlReadInventoryList() {
		
	}








}



