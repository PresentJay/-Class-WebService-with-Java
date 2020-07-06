import org.w3c.dom.*;
import java.util.HashMap;
import java.io.InputStream;

public class DataObject {

	private HashMap<String,String> objects;
	private Element rootNode;

	public DataObject(Node rootNode) {
		objects = new HashMap<String,String>();
		Node node = rootNode;

		if(rootNode.getChildNodes().getLength()==0)
			return;

		if(rootNode.getFirstChild().getNodeType()==Node.ELEMENT_NODE && rootNode.getChildNodes().getLength()!=0) {
			node=rootNode.getFirstChild();
			while(node.getNodeType()!=Node.ELEMENT_NODE && node.getChildNodes().getLength()>0)
				node=node.getNextSibling();
		}

		while(node!=null) {
			if(node.getNodeType()==Node.ELEMENT_NODE && node.getChildNodes().getLength()>0)
				objects.put(node.getNodeName(),node.getFirstChild().getNodeValue());

			if(node.getNodeName().equals("return"))
				break;

			node=node.getNextSibling();
		}
	}

	public String getValue(String key) {
		return objects.get(key);
	}
}
