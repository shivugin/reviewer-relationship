package reviewersrelationshipprinter;

import java.nio.file.Path;

/**
 * GenericReviewerHierarchyClient is a base helper class to implement a client
 * for the ReviewerHierarchyBuilder to show or transform its hierarchical data.
 * 
 * @author Shiva
 * @param <T> It's used to set the return type of the showHierarchy() method.
 * For example it can return a String to show in any UI or a JSON object to be
 * produced by a service.
 */
public abstract class GenericReviewerHierarchyClient<T>{
    protected ReviewerHierarchyBuilder hierarchyBuilder = new ReviewerHierarchyBuilder(); 
    
    /**
     * The subclass must have to implement how the file path is obtained.
     * @return 
     */
    protected abstract Path getFilePathFromClient();
    
    /**
     * The subclass have the flexibility to return the hierarchical information
     * in different ways, depending on the projects needs.
     * @return 
     */
    protected abstract T showHierarchy();
}
