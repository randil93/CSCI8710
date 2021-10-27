package util;

import java.util.List;

import org.eclipse.zest.core.widgets.GraphNode;

import model.graph.GNode;

public class UtilNode {

   /**
    * 
    * GNode myNode = (GNode) element;
    * List<?> graphNodeList = viewer.getGraphViewer().getGraphControl().getNodes();
    * GraphNode graphNode = UtilNode.getGraphNode(myNode, graphNodeList);
    * if (graphNode != null) {
    * String n1 = myNode.getName();
    * String n2 = ((GNode) graphNode.getData()).getName();
    * System.out.println(n1 + ", " + n2 + ", " + graphNode.getSize());
    * int len = myNode.getName().length();
    * final int CONST_VALUE_WIDTH = 20;
    * final int CONST_VALUE_HEIGHT = 20;
    * System.out.println("\t" + len * CONST_VALUE_WIDTH + "," + CONST_VALUE_HEIGHT);
    * graphNode.setSize(len * CONST_VALUE_WIDTH, CONST_VALUE_HEIGHT);
    * }
    */
   public static GraphNode getGraphNode(GNode gNode, List<?> graphNodeList) {
      String nameGNode = gNode.getName();
      GraphNode resultNode = null;
      for (Object object : graphNodeList) {
         GraphNode node = (GraphNode) object;
         String nameGraphNode = ((GNode) node.getData()).getName();
         if (nameGraphNode.equals(nameGNode)) {
            resultNode = node;
            break;
         }
      }
      return resultNode;
   }

}
