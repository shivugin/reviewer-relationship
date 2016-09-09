package reviewersrelationshipprinter;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A concrete implementation of GenericReviewerHierarchyClient to show the
 * hierarchical data through the command line interface.
 * @author Shiva
 */
public class ReviewerHierarchyConsoleClient extends GenericReviewerHierarchyClient<String> {
    private static final Logger LOGGER = Logger.getLogger( ReviewerHierarchyConsoleClient.class.getName() );

    @Override
    protected Path getFilePathFromClient() {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter the file name: ");
        String fileName = scanner.next();
        
        Path filePath = null;
        
        try {
            filePath = Paths.get(fileName);
        } catch (InvalidPathException ex) {
            System.err.println("Error: Invalid file name.");
//            LOGGER.log(Level.SEVERE, ex.toString(), ex);
            System.exit(0);
        }
        
        return filePath;
    }

    @Override
    public String showHierarchy() {
        try {
            hierarchyBuilder.buildHierarchyFromFile(getFilePathFromClient());
        } catch (IOException ex) {
            System.err.println("Error: Incorrect file name or Incorrect File Location.");
//            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        
        for (ReviewerNode node : hierarchyBuilder.getReviewersList()) {
            traverse(node, 0);
        }

        return null;
    }
    
    private void traverse(ReviewerNode node, int identationLevel){
        printNodeInfo(node, identationLevel);
        for (ReviewerNode child : node.getChildren()) {
            traverse(child, identationLevel + 1);
        }
    }
    
    private void printNodeInfo(ReviewerNode node, int identationLevel){
        String identation = "";
        for (int i = 0; i < identationLevel; i++) {
            identation += "| ";
        }
        System.out.println(identation + node.getReviewerName());
    }

}
