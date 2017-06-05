package Data;



public class Edge extends Graph {

private Node from;
private Node to;
private Type type;


    /**
     * Constructor
     * @param nodeOne
     * @param nodeTwo
     * @param type
     */
    Edge(Node from, Node to, Type type) {
        
        this.from = from;
        this.to = to;
        this.type = type;
      
    }
    
    
    public Type getType() {
        
        return this.type;
        
    }
    
    public Node getStartNode() {
        
        return this.from;
    }
    
    public Node getEndNode() {
        
        return this.to;
    }
    

    
    // has to be on the end of code!
    @Override
    public boolean equals(Object object) { 
     

        //use to make sure that equals compares identNumber and name
        //# task anforderung
        
        if (!(object instanceof Edge)) {
            return false;
            }
        //check himself refexivity
            if (object == this) {
                return true;
            }
            

        Edge compareEdge = (Edge) object;
        

        //seemingly works
        if((this.from.equals(compareEdge.from)) & (this.to.equals(compareEdge.to)) & (this.type == compareEdge.type)) {
            
            return true;
        }
        
        
        return false;
        

            
        
    }
    
    public String toString() {
       
        String s = "";
        s+= this.from + "-" + "[" + this.type + "]" + "->" + this.to;
        
        return s;
    }
    
    
    
    
    

    
}
