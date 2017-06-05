package Program;

import edu.kit.informatik.Terminal;

/**
 * Class CommandBox for reading commands from main
 * @author robin
 *@version 0.8
 */
public class CommandBox {
    
    void readCommand(String str) {
        
        
        
        switch(str) {
        
        case "nodes":  ;
        break;
        case "recommend": 
            //create parser!
            
            ;
        break;
        case "edges": ;
        break;
        case "export": ;
        break;
        case "quit": return;

        

        default: throw new IllegalArgumentException("Error, command unkown");
        
        }
    }
    

}
