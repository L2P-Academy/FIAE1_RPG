package controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Field;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.ItemModel;


public class XMLController {
	
	// Adding Filepaths 
	//private String characterInfoFilepath = "res/xml/characterInfo.xml";
	private String inventoryListFilepath = "res/xml/inventoryList.xml";
	//private String progressFilepath= "res/xml/progress.xml";
	
	// Adding Existens Varriable for Verafication in Write Methods
	//private File fileExistsCharacterInfo = new File(characterInfoFilepath);
	private File fileExistsInventoryList = new File(inventoryListFilepath);
	//private File fileExistsProgress = new File(progressFilepath);
	
	
	
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
				for(ItemModel currentItem : itemList) {
					
					//Adding itemTag as "Root" for each Item
					Element itemRoot = document.createElement("Item");
					rootElement.appendChild(itemRoot);
					
					//Adding name Child 
					Element nameChild = document.createElement("Name");
					nameChild.appendChild(document.createTextNode(currentItem.getItemName()));
					itemRoot.appendChild(nameChild);
					
					//Adding quantity Child
					if(currentItem.getItemQuantity() != 0) {
						Element quantityChild = document.createElement("Quantity");
						quantityChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemQuantity())));
						itemRoot.appendChild(quantityChild);
					}
					
					//Adding price Child
					if(currentItem.getItemPrice() != 0) {
						Element priceChild = document.createElement("Price");
						priceChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemPrice())));
						itemRoot.appendChild(priceChild);
					}
					
					//Adding damage Child
					if(currentItem.getItemDamage() != 0) {
						Element damageChild = document.createElement("Damage");
						damageChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemDamage())));
						itemRoot.appendChild(damageChild);
					}
					
					//Adding isQuestItem Child
					Element isQuestItemChild = document.createElement("QuestItem");
					isQuestItemChild.appendChild(document.createTextNode(String.valueOf(currentItem.getIsQuestItem())));
					itemRoot.appendChild(isQuestItemChild);
					
					//Adding itemDescription Child
					Element itemDescriptionChild = document.createElement("Description");
					itemDescriptionChild.appendChild(document.createTextNode(currentItem.getItemDescription()));
					itemRoot.appendChild(itemDescriptionChild);
				
					//Adding durability Child
//					Element durabilityChild = document.createElement("Durability");
//					durabilityChild.appendChild(document.createTextNode(String.valueOf(currentItem.getItemDurability())));
//					nameChild.appendChild(durabilityChild);
					
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
	
	public ArrayList<ItemModel> xmlReadInventoryList() {
		
		ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();
		
		try {
			
			if(fileExistsInventoryList.exists()) {
				//Building steps for a Document
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(inventoryListFilepath);
				document.normalize();
				// Getting all Childs from the Root. in this Case Item Tag
				NodeList itemNodeList = document.getElementsByTagName("Item");
				
				for (int i = 0; i < itemNodeList.getLength(); i++) {
				    Node itemNode = itemNodeList.item(i);
				    
					// Element casting is necessary for using Element methods
					Element itemElement = (Element) itemNode;

					String itemName = itemElement.getElementsByTagName("Name").item(0).getTextContent();					
					boolean isQuestItem = Boolean.parseBoolean(itemElement.getElementsByTagName("QuestItem").item(0).getTextContent());
					int quantity = Integer.parseInt(itemElement.getElementsByTagName("Quantity").item(0).getTextContent());
					String itemDescription = String.valueOf(itemElement.getElementsByTagName("Description").item(0).getTextContent());
					NodeList priceNodes = itemElement.getElementsByTagName("Price");
					NodeList damageNodes = itemElement.getElementsByTagName("Damage");
					
//					else if (damageNodes.getLength() > 0) {
//						int damage = Integer.parseInt(itemElement.getElementsByTagName("Damage").item(0).getTextContent());
//						ItemModel itemModel = new ItemModel(itemName, quantity, price, damage);
//				    	itemList.add(itemModel);
//					}
					if(priceNodes.getLength() == 0 && damageNodes.getLength() == 0){
				    	ItemModel itemModel = new ItemModel(itemName, quantity);
				    	itemList.add(itemModel);
				    
					}
					else if(priceNodes.getLength() != 0 && damageNodes.getLength() != 0) {
						double price = Double.parseDouble(itemElement.getElementsByTagName("Price").item(0).getTextContent());
						int damage = Integer.parseInt(itemElement.getElementsByTagName("Damage").item(0).getTextContent());
						ItemModel itemModel = new ItemModel(itemName, quantity, price, damage, isQuestItem, itemDescription);
				    	itemList.add(itemModel);
				    	
					} 
				}   
			}
		}
		catch (Exception e) {
			
		}
		
		return itemList; 
	}
}



