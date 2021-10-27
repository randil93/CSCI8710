/*
 * @(#) NodeModelContentProvider.java
 *
 */
package model.graph.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.graph.GConnection;
import model.graph.GNode;
import model.graph.GNodeTypeA;
import model.graph.GNodeTypeB;
import model.graph.GNodeTypeC;

public class GModelBuilder {
   private static List<GConnection> connections = new ArrayList<GConnection>();
   private static List<GNode>       nodes       = new ArrayList<GNode>();
   static GModelBuilder             singleton   = null;

   public GModelBuilder() {
   }

   public static GModelBuilder instance() {
      if (singleton == null) {
         singleton = new GModelBuilder();
      }
      return singleton;
   }

   public GModelBuilder build() throws IOException {
      connections.clear();
      nodes.clear();
      // Create nodes
      nodes.add(new GNodeTypeA("n1", "n1-A"));
      nodes.add(new GNodeTypeB("n2", "n2-B"));
      nodes.add(new GNodeTypeC("n3", "n3-3"));
      nodes.add(new GNode("n4", "n4"));
      nodes.add(new GNode("n5", "n5"));

      File file = new File("C:\\Users\\yryas\\eclipse-workspace\\workspaceCSCI8710\\project-ex-1014-type-zest-graph-pathirana\\input-data-zest-1.txt");
      BufferedReader br = new BufferedReader(new FileReader(file));
      // Declaring a string variable
      String st;
      String[] res;
      String[] subStr;
      GNode node1, node2;
      int i=1;
      while ((st = br.readLine()) != null)
      {
    	  res = st.split("[,]", 0);
    	  if(st.contains("-"))
    	  {
    		  if( res[0].contains("-"))
    		  {
    			  subStr = res[0].split("-");
    			  res[0] = subStr[0];
    		  }
    		  if( res[1].contains("-"))
    		  {
    			  subStr = res[1].split("-");
    			  res[1] = subStr[1];
    		  }
    	  }
    	  node1 = getNode(res[0]);
    	  node2 = getNode(res[1]);  
    	  connections.add(new GConnection("e"+i, res[0].charAt(1)+"-"+res[1].charAt(1), node1, node2));
    	  i++;
      }

      // Save the info about the connections in the nodes
      for (GConnection connection : connections) {
         connection.getSource().getConnectedTo().add(connection.getDestination());
      }
      
      br.close();
      return singleton;
   }
   
   public GNode getNode(String node)
   {
	   GNode nodeReturn = null;
	   if(node.equals("n1"))
	   {
		   nodeReturn = nodes.get(0);
	   }
	   else if(node.equals("n2"))
	   {
		   nodeReturn = nodes.get(1);
	   }
	   else if(node.equals("n3"))
	   {
		   nodeReturn = nodes.get(2);
	   }
	   else if(node.equals("n4"))
	   {
		   nodeReturn = nodes.get(3);
	   }
	   else if(node.equals("n5"))
	   {
		   nodeReturn = nodes.get(4);
	   }
	return nodeReturn;
	   
   }

   public List<GNode> getNodes() {
      return nodes;
   }

   public String getConnectionLabel(String srcId, String dstId) {
      for (GConnection iCon : connections) {
         if (iCon.getSource().getId().equals(srcId) && //
               iCon.getDestination().getId().equals(dstId)) {
            return iCon.getLabel();
         }
      }
      return "";
   }
}
