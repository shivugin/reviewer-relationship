package reviewersrelationshipprinter;

/**
 *
 * @author Shiva
 */
public class ReviewersRelationshipPrinter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ReviewerHierarchyConsoleClient consoleClient = new ReviewerHierarchyConsoleClient();       
        consoleClient.showHierarchy();
    }
    
}
