package Data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import Data.Graph.Node;


public class Recommander extends Graph {
    
    Vector<Edge> allEdgeVectors;
 //   Collection<Edge> edgeCollection;
    Vector<Node> allNodeVectors;
    private Vector<Node> nodeReturnList;
    
    public Recommander(Vector<Node> listAllNodes, Vector<Edge> listAllEdge) {
        
        this.allEdgeVectors = listAllEdge;
        this.allNodeVectors = listAllNodes;
        this.nodeReturnList = new Vector<Node>();
    //   this.edgeCollection = new ArrayList<Edge>(listAllEdge);
    }
    
    
    private Node getNodetoID(int id) {
        for(Node node: this.allNodeVectors) {
            
          if(node.identNumber == id) {
              return node;
          }
        }
      //  throw new IllegalArgumentException("Error, No such node");
        return null;
        
    }

    /**
     * has to be specified differently for real product
     * @param name
     * @param id
     * @return
     */
    
    //maybe unusefull
    
    //better to search for sorting options first
    // with type now!!
    private Vector<Edge> getOutGoingEdges(Node compareNode, Type type) {
        
     //   throw new IllegalArgumentException("CHeck sort options First);
        
        //maybe get id and stuff elsewhere maybe only name!
        //node shoudnt be implemented here with string and id!!!!!
     
        
        Vector<Edge> returnList = new Vector<Edge>();
        
       
        for (Edge edj : this.allEdgeVectors) {


       //     if(edj.getStartNode().equals(compareNode) | edj.getEndNode().equals(compareNode)) {
            
            if(edj.getStartNode().equals(compareNode) && edj.getType() == type) {
                returnList.add(edj);
            }

            
        }
//System.out.println(returnList);
        return returnList;
    
    }
    /**
     * 
     * @param compareNode
     * @param list
     * @param type
     * @return returns null in case there is no relation like that
     */
    private Vector<Node> nodeInRelation(Node compareNode, List<Edge> list,Type type) {
    
    
  //  public List<Node> nodeInRelation(String name, int id,  List<Edge> list,Type type ){
        
        //should be assured that only one relationship from node to node with the same kind can exists -> Graph equals input!
        //can go through full list right?
      //  Node compareNode = new Node(name, id);
        Vector<Node> returnList = new Vector<Node>();
        for (Edge edj : list) {


            if(edj.getType() == type) {
      //    System.out.println(edj.getEndNode());
                 returnList.add(edj.getEndNode());

            }
         
            
            /*
             *       if(edj.getType() == type) {
          
                
                //not nice to go through
                if(edj.getStartNode().equals(compareNode)) {
                    System.out.println(edj.getEndNode());
                 
                    return edj.getEndNode();
                } else {
                    
                    System.out.println(edj.getStartNode());
                    return edj.getStartNode();
                }
            }
             */
            
        }
       // throw new IllegalArgumentException("Error,Node doesnt have this relation");
       return returnList;
 
        
    }
    
    
    
// need to ensure that Graph and Recommander work over main
    
  //  public List<Node> stratedgyOne(Node referenceNode) {
    
    
    //ensure Node defenitly cant be category
    private Vector<Node> stratedgy1(int id) {
        Node compareNode = getNodetoID(id);
        
        //has to be built to perform well when there are more than one contained in relationships on one node
        
        //reference product is never part of this  & and never a category
        Type containType = GetArguments.convertKeyword("contained-in");
        
        Vector<Edge> edgeList = getOutGoingEdges(compareNode,containType);
  
        Vector<Node> productList = new Vector<Node>();
        
      //  Node compareNode = new Node("" ,-1);
  
        if(edgeList.isEmpty()) {
           throw new IllegalArgumentException("Error,Node doesnt have this relation");
          //  return null;
        }
         
        //ensure that the upper category is a category
     //   if(inputNode.getID() & nodeInRelation( name,  id,  edgeList,containType).getID() == -1)-------------important
        
        Vector<Node> potentialNodes = new Vector<Node>();
        Vector<List<Edge>> edgesToProducts = new Vector<List<Edge>>();
        
        for(Edge edj : edgeList) {
            
            
            //ignore products!
            
            //only use categorys
            if(edj.getEndNode().getID() == -1) {
         //       System.out.println(edj.getEndNode());
                potentialNodes = nodeInRelation(edj.getEndNode(),edgeList,containType);
          //      System.out.println(potentialNodes);
                Type containsType = GetArguments.convertKeyword("contains");
                edgesToProducts.add(getOutGoingEdges(edj.getEndNode(),containsType));
                
            }
        }
    /*  if(nodeInRelation( name,  id,  edgeList,containType).getID() == -1) {
           //found my upper category
          
          
           returnNode = nodeInRelation( name,  id,  edgeList,containType);
           System.out.println("mother: " + returnNode.toString());
           Type containsType = GetArguments.convertKeyword("contains");
           List<Edge> edgesToProducts = getOutGoingEdges(returnNode.getName(),returnNode.getID(),containsType);
*/
        
        //ribbon over edges list
           for (List<Edge> edj : edgesToProducts) {

        
               //ribbon over inital edjes
               for(Edge idj : edj) {
                   if(idj.getEndNode().getID() != -1) {
                       
                       //make sure endNode isnt in the product list
                       if(idj.getEndNode().equals(compareNode)) {
                           continue;
                       }
                       productList.add(idj.getEndNode());
                   }
                   
                   
               }

               }

         
   //   System.out.println("ProductList: " + productList);
      
       
        return productList;
    }
    

    

    
    //stratdegy 2 has problems with same node in it
    
    
    //maybe built one mayor stratedgy converter?
    private Vector<Node> stratedgy2(int id) {
        Node compareNode = getNodetoID(id);
     //   System.out.println(compareNode);
            Type predecessorType = GetArguments.convertKeyword("predecessor-of");
        
        
       recursiveTraversal(compareNode,predecessorType);
        
     //   System.out.println("product" + id +  "" +nodeReturnList);
        
      // nodeReturnList.remove(compareNode);
        return nodeReturnList;
    }
    
    private Vector<Node> stratedgy3(int id) {
        Node compareNode = getNodetoID(id);
     //   System.out.println(compareNode);
            Type successorType = GetArguments.convertKeyword("successor-of");
        
        
       recursiveTraversal(compareNode,successorType);
     //   System.out.println(nodeReturnList);
       
       
        return nodeReturnList;
    }
    
//since going this graph is basically undirected it doesnt matter if you go through preccessor or successors
    private void recursiveTraversal(Node instanceNode, Type type) {

        Vector<Edge> edgeList = getOutGoingEdges(instanceNode,type);
    
        while(!edgeList.isEmpty()) {
            this.nodeReturnList.add(edgeList.firstElement().getEndNode());
            recursiveTraversal(edgeList.remove(0).getEndNode(),type);
        }
     //   if(!getOutGoingEdges(instanceNode,type).isEmpty()) {
         //   returnNode = edgeList.get(0).getEndNode();
        //   this.nodeReturnList.add(edgeList.firstElement().getEndNode());
        
           
        //    recursiveTraversal(edgeList.firstElement().getEndNode(),type);
        //    System.out.println(returnNode);
            
       // }

     //  System.out.println(nodeReturnList);

    }
   

    
    public Vector<Node> StratedgySplitter(String stradegy, int id) {
        
        // should return error for an empty list i guess??
        switch(stradegy) {
        
        case "S1": return  stratedgy1(id); 
        case "S2": return  stratedgy2(id);
        case "S3": return  stratedgy3(id);
      

        default: throw new IllegalArgumentException("Error, stradegy type or id unsuported!");
     
      //  return null;
        }
    }
    
    
    public Vector<Node> intersect(Vector<Node> vectorProducts1, Vector<Node> vectorProducts2) {
        
        Vector<Node> nodeReturnList = new Vector<Node>();
        
        for(Node node: vectorProducts1) {
            
            if (vectorProducts2.contains(node)) {
                
                nodeReturnList.add(node);
         
            }

        }
     //   System.out.println("intersect Nodes" + nodeReturnList);
        return nodeReturnList;
    }
    
    public Vector<Node> union(Vector<Node> vectorProducts1, Vector<Node> vectorProducts2) {
        
        Vector<Node> nodeReturnList = new Vector<Node>();
        
        
        for(Node node: vectorProducts1) {
            
            if (!nodeReturnList.contains(node)) {
                
                nodeReturnList.add(node);
         
            }
            for(Node node2: vectorProducts2) {
                
                if (!nodeReturnList.contains(node2)) {
                    
                    nodeReturnList.add(node2);
             
                }

  //      nodeReturnList.addAll(vectorProducts1);
   //     nodeReturnList.addAll(vectorProducts2);

        
    }
            

        }
     //   System.out.println(nodeReturnList);
        return nodeReturnList;
 
    
  
}
}
