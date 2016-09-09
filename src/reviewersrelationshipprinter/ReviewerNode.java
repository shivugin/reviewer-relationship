package reviewersrelationshipprinter;

import java.util.ArrayList;
import java.util.List;

/**
 * ReviewerNode represents the tree structure used to model the hierarchical
 * relationship between reviewers and reviewees. It doesn't have all the tree 
 * functionalities due to solution simplicity.
 * 
 * @author Shiva
 */
public class ReviewerNode{
    private final String reviewerName;
    private final List<ReviewerNode> children = new ArrayList<>();
    private ReviewerNode parent;
    
    public ReviewerNode(ReviewerNode parent, String reviewerName){
        this.parent = parent;
        this.reviewerName = reviewerName;
    } 
    
    public ReviewerNode(String reviewerName){
        this(null, reviewerName);
    }
    
    public ReviewerNode addChild(String reviewerChildName){
        ReviewerNode child = new ReviewerNode(this, reviewerChildName);
        children.add(child);
        return child;
    }
    
    public ReviewerNode addChild(ReviewerNode child){
        children.add(child);
        child.setParent(this);
        return child;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public List<ReviewerNode> getChildren() {
        return children;
    }

    public ReviewerNode getParent() {
        return parent;
    }       

    public void setParent(ReviewerNode parent) {
        this.parent = parent;
    }
}
