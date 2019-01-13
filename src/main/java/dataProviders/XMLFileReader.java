package dataProviders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFileReader extends FilesReader {

	private DocumentBuilderFactory obtainDOMParser;
	private DocumentBuilder dOMParser;
	private Document document;
	
	private String dataTagName;
	private final String EXTENSION = "xml";
	
	protected XMLFileReader(String filePath, String dataTagName) {
		super(filePath);
		this.dataTagName = dataTagName;
	}
	 
	 private DocumentBuilder initializeDOMParser() {
		 obtainDOMParser = DocumentBuilderFactory.newInstance();
		 try {
			 System.out.println("Initializing DOM Parser");
			 dOMParser = obtainDOMParser.newDocumentBuilder();
		 }catch(ParserConfigurationException e) {
			 e.printStackTrace();
		 }
		 return dOMParser;
	 }
	 
	 private Document createDocumentInstance() {
		 if(initializeDOMParser()!=null) {
			 System.out.println("Validating XML File....");
			 if(validateFile(filePath,EXTENSION)) {
				 try {
					 System.out.println("Creating DOM Document Instance");
					 document = dOMParser.parse(filePath);
				 } catch (SAXException | IOException e) {
					 e.printStackTrace(); 
				 }
			 }
		 }
		 return document;
	 }

	 @Override
	 public Object[][] readFile() {
		 Object[][] array = null;
		 if(createDocumentInstance()!=null) {
			 System.out.println("Reading XML File");
			 NodeList nodeRows = readRowNodes();
			 array = new Object[nodeRows.getLength()][1];
			 
			 for(int i=0; i<nodeRows.getLength(); i++) {
				 Node nodeRow = nodeRows.item(i);
				 NodeList nodeCells = readCellNodes(nodeRow);
				 array[i][0] = addValuesToMap(nodeCells);
			 }
		 }
		 return array;
	 }
	 
	 private NodeList readRowNodes() {
		 	Element rootElement = document.getDocumentElement();
			System.out.println("Root Element's Name : "+rootElement.getNodeName());
			
			NodeList nodeRows = rootElement.getElementsByTagName(dataTagName);
			System.out.println("Number of rows found : "+nodeRows.getLength()+"\n");
			
			return nodeRows;
	 }
	 
	 private NodeList readCellNodes(Node nodeRow) {
		 NodeList nodeCells = null;
		 if(nodeRow.getNodeType() == Node.ELEMENT_NODE) {
			 nodeCells = nodeRow.getChildNodes();
		 }
		 return nodeCells;
	 }
	
	 @Override
	protected Map<String, String> addValuesToMap(Object object) {
		Map<String, String> map = new HashMap<>();
		NodeList nodeCells = (NodeList) object;
		 for(int j=0; j<nodeCells.getLength(); j++) {
			 Node nodeCell = nodeCells.item(j);
			 if(nodeCell.getNodeType() == Node.ELEMENT_NODE)  
				 map.put(nodeCell.getNodeName(), nodeCell.getTextContent());
		 }				
		 return map;
	}
	
}
