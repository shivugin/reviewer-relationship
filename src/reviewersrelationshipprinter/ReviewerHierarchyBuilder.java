package reviewersrelationshipprinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * ReviewerHierarchyBuilder is the main class of the solution where all the 
 * hierarchical relationships are built.
 * 
 * @author Shiva
 */
public class ReviewerHierarchyBuilder {
    /*
    A list of trees is used instead of a unique tree to cover the case where
    there are more than one root element (More than one reviewer who doesn't
    have any parent reviewer).
    */
    private final List<ReviewerNode> reviewersList = new ArrayList<>();
    /*
    Sort of cache memory for storing the nodes, so we don't have to traverse 
    the trees and subtrees to find a node. It's used to improve search 
    performance in this particular scenario.
    */
    private final Map<String, ReviewerNode> nodesMap = new HashMap<>();

    public void buildHierarchyFromFile(Path filePath) throws IOException {
        
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(filePath)) {
            stream.forEach(this::buildHierarchy);
        }

        for (ReviewerNode reviewerNode : nodesMap.values()) {
            if(reviewerNode.getParent() == null){
                reviewersList.add(reviewerNode);
            }
        }
    }

    public List<ReviewerNode> getReviewersList() {
        return reviewersList;
    }

    /**
     * Method used only for testing purposes, in order to access the nodesMap
     * member. In production this field should be kept private.
     * 
     * @return 
     */
    public Map<String, ReviewerNode> getNodesMap() {
        return nodesMap;
    }

    /*
        All the methods below are public only for testing puposes. In production
        they shoud be kept private, because they are helper methods and used only
        inside the class.
    */ 
    
    public void buildHierarchy(String line) {
        String reviewerName = getReviewerName(line);
        String reviewee_Name = getRevieweeName(line);

        ReviewerNode reviewer = getNodeOrCreateIfNew(reviewerName);
        ReviewerNode reviewee = getNodeOrCreateIfNew(reviewee_Name);
        reviewer.addChild(reviewee);      
    }

    public ReviewerNode getNodeOrCreateIfNew(String reviewer) {
        if (!nodesMap.containsKey(reviewer)) {
            ReviewerNode node = new ReviewerNode(reviewer);
            nodesMap.put(reviewer, node);
            return node;
        }
        return nodesMap.get(reviewer);
    }

    public String getReviewerName(String line) {
        int idx = line.indexOf("reviews");
        return line.substring(0, idx).trim();
    }

    public String getRevieweeName(String line) {
        int idx = line.indexOf("reviews") + 7;
        return line.substring(idx, line.length()).trim();
    }
}
