package Program;
import java.io.IOException;

import edu.kit.informatik.Terminal;
import Data.FileInputHelper;
import Data.GetArguments;
import Data.Graph;
import Data.Recommander;
import Data.Type;
//import Parser.Parser.ParseException;
public class Main {

    
    private static  CommandBox reader = new CommandBox();
    protected static Graph graph = new Graph();
       
    
    public static void main(String[] args) throws IOException {
        //check if input file is empty
        
        try {
       FileInputHelper.read(args[0]);
       
        }catch(ArrayIndexOutOfBoundsException e) {
            
            Terminal.printLine("Error, Syntax Error of the input File!");
            System.exit(1);
        }
        //from now on GetArguments has in his static list the values
  
        //create Graph before commands or after? # currently is done first # dumb idea

        
        // graph gets splitString List
        graph.createGraph(FileInputHelper.splitStrings);
//   Terminal.printLine(graph.nodeCommand());
      Terminal.printLine(graph.edgeCommand());
        Recommander recom = new Recommander(graph.getNodeVector(),graph.getEdgeVector());
      
      //  System.out.println(graph.exportCommand());
      //  recom.getEdgeToNode("CentOS5", 105);
        
        Type typeHere = GetArguments.convertKeyword("predecessor-of");
   //     recom.nodeInRelation("CentOS5",105, recom.getOutGoingEdges("CentOS5", 105), typeHere);
        
        
       // recom.stratedgy1("Centos5", 105);
     //   recom.stratedgy3(201);
 //       recom.StratedgySplitter("S1",105 );
  //      recom.StratedgySplitter("S2", 105);
        

    //   recom.intersect(recom.StratedgySplitter("S1",105 ), recom.StratedgySplitter("S2",105 ));
        
      // recom.union(recom.StratedgySplitter("S1",105 ), recom.StratedgySplitter("S2",105 ));
        
     /*
        Parser parser = new Parser();
        try {
            parser.parse("INTERSECTION(S1 105,S2 105)");
        }
        
        catch (ParseException e) {
            System.out.println("Parsing error: " + e.getMessage());
        }
       
           
 */
        
        
        String command = Terminal.readLine();
        
        reader.readCommand(command);
    }



}
