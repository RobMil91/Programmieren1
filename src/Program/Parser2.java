package Program;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import Data.Edge;
import Data.FileInputHelper;
import Data.Graph;
import Data.Graph.Node;
import Data.Recommander;




public class Parser2{

    
    private static StringTokenizer tokenizer;
    private static String lookahead;
  
    private static Recommander recom;
    
    private static final String intersectionString = "INTERSECTION";
    private static final String unionString = "UNION";

    
    
    public Parser2(Vector<Node> nodeList, Vector<Edge> edgeList) {
        recom = new Recommander(nodeList, edgeList);
    }
    
    public static class ParseException extends Exception {
        
        ParseException(String message) {
            
            super(message);
        }
        
    }
    int c = 0;
    private void next() throws IOException {
       c++;
        if(tokenizer.hasMoreTokens()) {
        lookahead = tokenizer.nextToken(); 
        }
System.out.println(lookahead + " /token: " + c);
}
    

    
    Vector<Node> parseCommand() throws IOException, ParseException {
        //maybe catch both vectors here
        return null;
    }
       
  //  static Vector<Node> currentList = new Vector<Node>();
    Vector<Node> parseTerm() throws IOException, ParseException {
//89% chance i dont need to use any list here
       
     //    Vector<Node> currentEndList = new Vector<Node>();
         

        if(lookahead.contains(intersectionString)) {
          //jump to token after intersection
           next();
        
        //    currentEndList = recom.intersect(parseTerm(), parseTerm());
           System.out.println("used the intersect!");
           return recom.intersect(parseTerm(), parseTerm());
     
         
    
        }else if(lookahead.contains(unionString)) {
            next();
          //  currentEndList = recom.union(parseTerm(), parseTerm());
            
            System.out.println("used the  union!");
            return recom.union(parseTerm(), parseTerm());
          
            
        }

       /* else {
            currentList = parseFinal();
        }*/
      
        return parseFinal();
       
       }
    
    Vector<Node> parseFinal() throws IOException, ParseException {


        
        int id = 0;
        String stratedgy = null;

        //lookahead has always stand on the stradegy here
            stratedgy = lookahead;
            System.out.println("stratedgy: " + stratedgy);
            //next token has to be the id
            next();
            
            id = Integer.parseInt(lookahead);
           System.out.println("id: " + id);

           //jump to next symbol
next();
    //  System.out.println("used stradegy with: " + stratedgy + "/" + id + " list: " + recom.StratedgySplitter(stratedgy, id));
        return recom.StratedgySplitter(stratedgy, id);
    }
    
    
    public Vector<Node> parse(String line) throws IOException, ParseException {
        
       

        tokenizer = new StringTokenizer(line," (,,)");
        //set on the first symbol
     next(); 
/*
        while(tokenizer.hasMoreTokens()) {
            next();
        }
*/
   
System.out.println(parseTerm());
        return null;
        
    

    }
    
    protected static Graph graph = new Graph();
    public static void main(String[] args) throws IOException {
        FileInputHelper.read(args[0]);
        graph.createGraph(FileInputHelper.splitStrings);
        Recommander recom = new Recommander(graph.getNodeVector(),graph.getEdgeVector());
        Parser2 parser = new Parser2(graph.getNodeVector(), graph.getEdgeVector());
        
        try {
            
        //    parser.parse("    UNION        (      S1      105), S2 106 )");
        //   parser.parse("    INTERSECTION        (      S1      105), S2 106 )");
       // parser.parse(" S2      106"); 
           parser.parse("    INTERSECTION        ( UNION(S3 105,      S1      105), S2 106 )");
        }
        
        catch (ParseException e) {
            System.out.println("Parsing error: " + e.getMessage());
        }

    }
}
