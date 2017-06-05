package Data;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import edu.kit.informatik.Terminal;



public class Graph {
    
     private Vector<Node> nodeVector;
     private Vector<Edge> edgeVector;
     
     
     public Graph() {
         this.nodeVector = new Vector<Node>();
         this.edgeVector = new Vector<Edge>();
     }
     
     public void createGraph(List<String[]> splitStrings) {
         
        //problem iterator starts at last line for now ignores other lines
         
         Iterator<String[]> itr = splitStrings.iterator();

         
         //list is to small
        
         
         //ammount of lines that have to be read
 
         while(itr.hasNext()) {

             addArrayLine(itr.next());
     
         }
        
         
     }

  //   int c = 0;
     private Node addNode(String name, int identityNumber) {


         
         //maybe if null gets returned send terminal error
         
         Node inputNode = new Node(name,identityNumber);
         //currently no test for existing nodes
         //test if node exsits
         //contains must be more exact
    /**
     * Method that will return the Node that is put into the Graph
     */
    if (! nodeVector.contains(inputNode)) {
           
        
    //    System.out.println("Node created" + " " + c + " " + name + "," + identityNumber);
    //  c++;
        
             nodeVector.addElement(inputNode);
             return inputNode;
        }
    
    return inputNode;
 // throw new IllegalArgumentException("Node is already in Graph");
         
        
         
     }
     
     private Vector<Node> nodeInRelation(Node compareNode, List<Edge> list,Type type) {
         
         Vector<Node> returnList = new Vector<Node>();
         for (Edge edj : list) {


             if(edj.getType() == type) {
       //    System.out.println(edj.getEndNode());
                  returnList.add(edj.getEndNode());

             }
         }
         return returnList;
     }
     
     /**
      * 
      * @param compareNode
      * @param type
      * @returns false if there is no cycle
      * @returns true if there is a cycle
      */
     
     Vector<Node> vectorNodes = new Vector<Node>();
     private boolean testCyclus(Node compareNode, Type type) {
        
         
         Vector<Edge> edgeList = getOutGoingEdges(compareNode,type);
     //    System.out.println("reached");
      //   System.out.println(edgeList);
         while(!edgeList.isEmpty()) {
             
            this.vectorNodes.add(edgeList.firstElement().getEndNode());  

             if(this.vectorNodes.contains(compareNode)) {
                 
                 return true;
             }
             testCyclus(edgeList.remove(0).getEndNode(),type);
         }
         
         
         
         return false;
     }
     
     private Vector<Edge> getOutGoingEdges(Node compareNode, Type type) {
         
         //   throw new IllegalArgumentException("CHeck sort options First);
            
            //maybe get id and stuff elsewhere maybe only name!
            //node shoudnt be implemented here with string and id!!!!!
         
            
            Vector<Edge> returnList = new Vector<Edge>();
            
           
            for (Edge edj : this.edgeVector) {


           //     if(edj.getStartNode().equals(compareNode) | edj.getEndNode().equals(compareNode)) {
                
                if(edj.getStartNode().equals(compareNode) && edj.getType() == type) {
                    returnList.add(edj);
                }

                
            }
    //System.out.println(returnList);
            return returnList;
        
        }
     
     
     private Vector<Node> nodeReturnList;
     private void recursiveTraversal(Node instanceNode, Type type) {

         Vector<Edge> edgeList = getOutGoingEdges(instanceNode,type);
     
         while(!edgeList.isEmpty()) {
             this.nodeReturnList.add(edgeList.firstElement().getEndNode());
             recursiveTraversal(edgeList.remove(0).getEndNode(),type);
         }

     }
    
     private void addEdge(Node node1, Node node2, Type nodeType) {
         
         //why nodeType
         Type typeOpossite = GetArguments.getOpposite(nodeType);
         Edge inputEdge = new Edge(node1, node2, nodeType);
   //  System.out.println(! edgeVector.contains(inputEdge) && !node1.equals(node2) && !testCyclus(node1, nodeType)  && !testCyclus(node1, typeOpossite));

         //checks for ribbons and cycles
      //   if( !(!node1.equals(node2) && !testCyclus(node2, nodeType) && !testCyclus(node1, typeOpossite))) {
         if(!(!(node1.equals(node2)) && !testCyclus(node1, nodeType))){
             Terminal.printLine("Error, sematnic error in the file!");
             System.exit(1);
         }
   //   if (! edgeVector.contains(inputEdge) && !node1.equals(node2) && !testCyclus(node1, nodeType) && !testCyclus(node1, typeOpossite) && !testCyclus(node2, nodeType) && !testCyclus(node2, typeOpossite)) {
  if(! edgeVector.contains(inputEdge)) {
          //make sure counter Edge is implemented or do it constructor
             edgeVector.addElement(inputEdge);
       }
     }
     

     
     
     /**
      * Method that gets the paredInput to put it into the graph
      * @param parsedStringArray
      */
     private void addArrayLine(String[] parsedStringArray) {
       
      
         //method gets a StringArrayLine with 3 Arguments
         assert parsedStringArray.length < 4;
         
         //idea is that when a node is created the an example node from the same kind is returned
         
         //uninitialized Nodes that become subject and object
         Node node1;
         Node node2;
         //check if subject is valid!!! in another method
      
        String subject = parsedStringArray[0];
        
        
        if(subject.contains("(")) {
       int idx = subject.indexOf("(");
       String stringPoint = subject.substring(idx, subject.length());
       String stringPoint2 = subject.substring(0, idx);
       
      String subjectNoNumber = stringPoint.replaceAll("\\D", "");
      int identNumber = Integer.parseInt(subjectNoNumber);
  //    System.out.println(stringPoint2);
    //  System.out.println(identNumber);
      
   node1 =  addNode(stringPoint2,identNumber);

        } else {
            //use -1 to identify category's
          
            
      node1 = addNode(subject,-1);
        }
            
        String object = parsedStringArray[2];

 

        if(object.contains("(")) {
       int idx = object.indexOf("(");
       String objectPoint = object.substring(idx, object.length());
       String objectPoint2 = object.substring(0, idx); 
      String objectNoNumber = objectPoint.replaceAll("\\D", "");
      int identNumberObject = Integer.parseInt(objectNoNumber);

      
     node2 = addNode(objectPoint2,identNumberObject);

        } else {
          node2 =  addNode(object,-1);
            
        }
        
   //    int indentNumber = Integer.parseInt(subjectNoNumber);


        String keyword = parsedStringArray[1];

        
        Type typeKeyword = GetArguments.convertKeyword(keyword);
        
        // getNodes from vector?
        
        //ribbon check
        
        // typeKeyword not printable
    addEdge(node1,node2, typeKeyword);

    //check in if edge if edge is already existing!!!!!
   addEdge(node2,node1,GetArguments.getOpposite(typeKeyword));

        

            
         
     }
     
     
     
     
     /**
      * to check for recommand command
      * @return
      */
     public Vector<Edge> getEdgeVector() {
        
         return this.edgeVector;
     }
     
     
     public Vector<Node> getNodeVector() {
         
         return this.nodeVector;
     }
     
     
     /**
      * Method to return all Nodes of the list
      * @return
      */
     public String nodeCommand() {
         
         String s = "";
         Iterator<Node> itr = this.nodeVector.iterator();
         
         while(itr.hasNext()) {

             s += itr.next().toString();
             
             if(itr.hasNext()) {
                 s+= ",";       
             }
         }
         
         return s;

     }

     
     
     /**
      * 
      * @return
      */
     public String edgeCommand() {
         

         String s = "";
         Iterator<Edge> itr2 = this.edgeVector.iterator();
         while(itr2.hasNext()) {
            
             s += itr2.next().toString();
             
             if(itr2.hasNext()) {
                 s+= "\n";       
             }
         }
  
         return s;

         
     }
     
     
     public String exportCommand() {
         
         
         String s = "digraph {";

         for(Edge edg : this.edgeVector) {
             
        
             
             s +=  "\n" + edg.getStartNode() + " -> " + edg.getEndNode() + " [label=" + edg.getType() + "]" ; 
             
             
         }
         
         for(Node nod : this.nodeVector) {
             
             if(nod.identNumber == -1) {
                
                 s+= "\n" + nod.name + " [shape=box]";
             }
         }
         
         s += "\n" + "}";
         
         return s;
     }

    public  class Node{
        
        private String name;
        //identNumber should be -1 if category
        protected int identNumber;      

        
         Node(String name, int identNumber) {
            this.name = name;
            this.identNumber = identNumber;
        }
         
         public String toString() {
             
             if(this.identNumber == -1) {
                 
                 String s = this.name;
                 return s;
             } 
             
             String s = this.name + ":" + this.identNumber;
             
             return s;
         }
         
         public int getID() {
             
             return this.identNumber;
         }
         
         public String getName() {
             
             return this.name;
         }
         
         public String exportCommand() {
             
             
             //use edgeList to String
             return null;
         }
        
        
        @Override
        public boolean equals(Object object) { 
         

            //use to make sure that equals compares identNumber and name
            //# task anforderung
            
            if (!(object instanceof Node)) {
                return false;
                }
            //check himself refexivity
                if (object == this) {
                    return true;
                }
                

            Node compareNode = (Node) object;
            
            if(this.name.toLowerCase().equals(compareNode.name.toLowerCase())) {
           
                    return true;
         
            } 
            
            //careful killing my compareNodes categorys

            
            
            if((this.identNumber | compareNode.identNumber) == -1) {
                return false;
            }
            
            //check Number last
            if ((this.identNumber == compareNode.identNumber)){
                //maybe raise error msg
            return true;
            }



            
            return false;
            

                
            
        }
           
        
        // std needed..
            @Override 
            public int hashCode() {
                 throw new IllegalArgumentException();
                 
            }
            
            
            
            
            
            }
        


    //buil up
    
    //graph with elemnts that can be added
    //graph with edges and nodes...

}
    
    
