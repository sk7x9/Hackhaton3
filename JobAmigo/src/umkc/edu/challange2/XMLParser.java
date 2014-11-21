package umkc.edu.challange2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.speech.tts.TextToSpeech;

public class XMLParser {

	String userselectedstate = null;
	String userselectedcity = null;
	String key = "09a71f84fc";
	//	String finalurl = "www.supermarketapi.com/api.asmx/StoresByCityState?APIKEY=APIKEY&SelectedCity="
	//			+ userselectedstate + "&SelectedState=" + userselectedcity;
	String itemUrl = "http://api.careerbuilder.com/v1/jobsearch?DeveloperKey=WDHN54P6QRVYN04D3352&Name=";

	//	XMLParser obj = new XMLParser();

	// obj.getStringFromURL();
	// obj.parseData("");
	public String getStringFromURL(String urlstring) {
		String data = null;
		URL url = null;
		System.out.println("Executing getStringFromURL");
		StringBuffer bufferData = new StringBuffer();
		try {

			url = new URL(urlstring);
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			System.out.println("+++ Connected to url and reading data: "+ urlstring );
			while ((data = in.readLine()) != null) {
				bufferData.append(data);
			}
			in.close();
		} catch (Exception e) {
			System.out.println("Execption caught");
		}
		//System.out.println("+++ Return xml data: "+bufferData.toString());
		return bufferData.toString();

	}

	public ArrayList<ParserObject> parseData(String url) {

		String item_final_url = itemUrl;

		System.out.println("+++ final url : " + item_final_url);
		ArrayList<ParserObject> itemList = new ArrayList<ParserObject>();
		Document document = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(
					getStringFromURL(item_final_url)));
			document = db.parse(is);
			document.getDocumentElement().normalize();

			NodeList nList = document
					.getElementsByTagName("JobSearchResult");
			System.out.println("+++++** Number of childs in results: " + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				NodeList nl = nNode.getChildNodes();

				String name = "";
				String desc = "";
				String title = "";
				String emp_type = "";
				String educ = "";
				String exp = "";
				String details = "";				
				String loc = "";
				String pay = "";				
				String img = "";
				String did="";
				for (int i = 0; i < nl.getLength(); i++) {

					if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Node eElement = (Node) nl.item(i);
						if (eElement.getNodeName().equalsIgnoreCase("CompanyImageURL")) {
							img = eElement.getTextContent();
						} 
						else if (eElement.getNodeName().equalsIgnoreCase(
								"Company")) {
							name = eElement.getTextContent();}

						else if (eElement.getNodeName().equalsIgnoreCase(
								"DescriptionTeaser"))
							desc = eElement.getTextContent();

						else if (eElement.getNodeName().equalsIgnoreCase(
								"JobTitle"))
							title = eElement.getTextContent();

						else if (eElement.getNodeName().equalsIgnoreCase(
								"EmploymentType"))
							emp_type = eElement.getTextContent();

						else if (eElement.getNodeName().equalsIgnoreCase(
								"EducationRequired"))
							educ = eElement.getTextContent();

						else if (eElement.getNodeName().equalsIgnoreCase(
								"ExperienceRequired")) {
							exp = eElement.getTextContent();}

						else if (eElement.getNodeName().equalsIgnoreCase(
								"JobDetailsURL")) {
							details = eElement.getTextContent();}

						else if (eElement.getNodeName().equalsIgnoreCase(
								"Location")) {
							loc = eElement.getTextContent();}

						else if (eElement.getNodeName().equalsIgnoreCase(
								"Pay")) {
							pay = eElement.getTextContent();
						}
						else if (eElement.getNodeName().equalsIgnoreCase(
								"DID")) {
							did = eElement.getTextContent();
						}
					}
				}
				System.out.println(img + " : " + name + " :" + desc + ":"
						+ title + ":" + emp_type + ":" + educ + ":" + exp + ":"
						+ details + ":" + loc + ":" + pay);
				itemList.add(new ParserObject(img,name, desc, title, emp_type, educ, exp, details, loc, pay,did));
			}
		} catch (Exception e) {
		}
		return itemList;
	}
}
