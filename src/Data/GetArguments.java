package Data;



// remember that ribbons in the connections have to be found!!!
import java.util.LinkedList;
import java.util.List;

public class GetArguments {
    
    public static List<String[]> splitStrings;
    private static String splitString[];
   
    
    
    //set private final variables Strings for keywords instead of enum class??
    
    //probably use all methods without static implement into graph
    
    //flag first word!
    
    static String[]  splitString(String line) {

        boolean flag = false;
        String[] lineparts = line.split(" ");
         
        String keyword = null;
        int counter = 0;
        
        
        for(String c: lineparts) {
            

            //first set with c>0 it worked too
            //problem that keyword is searched first super disgusting implementation of contains contains contains
            if(c.matches("(contains|contained-in|part-of|has-part|successor-of|predecessor-of)") && flag) {
                keyword = c;
                break;
            }else {
                
                flag = true;
            }
            counter++;
        }
        
        String[] FirstValidPart = new String[lineparts.length];

        String[] object = new String[lineparts.length];
        
       



        for(int i = 0; i <= counter; i++) {
            
            FirstValidPart[i] = lineparts[i];
           
        }
    
        String subjectString = "";
        for(int i = 0; i <= counter - 1; i++) {
            subjectString = subjectString + FirstValidPart[i];
           
        }
     

        
        for(int i = counter + 1; i <= lineparts.length - 1; i++) {
            
            object[i] = lineparts[i];
           
        }
        
        String objectString = "";
        for(int i = 0; i <= lineparts.length - 1; i++) {
            if(object[i] == null) {
                continue;
            }
            objectString = objectString + object[i];
        }
        

        // static list is not overwritable??
        // make the 3 spaces for results
       splitString = new String[3];
       splitStrings = new LinkedList<String[]>();
        
        splitString[0] =subjectString;
        splitString[1] =keyword;
        splitString[2] =objectString;
        
        return splitString;
        //secured twice #badIdea
     //   assert splitString.length <=3;

        // list is filled with the lines that are parsed above
     
      
    //    splitStrings.add(splitString);
        
        
         }
    

    
    /**
     * Convert a String to the Type keyword
     * @param keyword
     * @return
     */
     public static Type convertKeyword(String keyword) {
         
         switch(keyword) {
         
         case "contains": return  Type.CONTAINS; 
        
         case "contained-in": return  Type.CONTAINED_IN; 
        
         case "part-of": return  Type.PART_OF; 
       
         case "successor-of": return  Type.SUCCESSOR_OF; 
         
         case "predecessor-of": return  Type.PREDECESSOR_OF; 
         
         case "has-part" : return Type.HAS_PART;
       

         default: throw new IllegalArgumentException("Error , No keyword");
      
         }
         //maybe add return null
         
     }
     
     static Type getOpposite(Type keyword) {
         
         
         switch(keyword) {
         
         case CONTAINS: return  Type.CONTAINED_IN; 
        
         case CONTAINED_IN: return  Type.CONTAINS; 
        
         case PART_OF: return  Type.HAS_PART; 
       
         case SUCCESSOR_OF: return  Type.PREDECESSOR_OF; 
         
         case PREDECESSOR_OF : return  Type.SUCCESSOR_OF; 
         
         case HAS_PART : return Type.PART_OF;
       

         default: throw new IllegalArgumentException("Error, No Keyword!");
      
         }
     }
     
     public static Boolean stringValid(String str) {
         
         //check if string has only [a-zA-Z0-9]+
         //categoryname has only [a-zA-Z0-9]+
         //flag first word? -> to ensure contains can be a product

         
 
        
         
         
         return str.matches("[a-zA-Z0-9]*");
         
         
     }
     
     public static String convertDigraph() {
         
         return null;
         
     }

}
