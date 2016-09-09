package test;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import reviewersrelationshipprinter.ReviewerHierarchyBuilder;
import reviewersrelationshipprinter.ReviewerNode;

/**
 *
 * @author Shiva
 */
public class ReviewerHierarchyBuilderTest {
    
    ReviewerHierarchyBuilder builder = new ReviewerHierarchyBuilder();
    
    @After
    public void tearDown() {
        builder.getNodesMap().clear();
    }
    
    @Test
    public void testCreateIfNew(){
        int mapSize = builder.getNodesMap().size();
        ReviewerNode node = builder.getNodeOrCreateIfNew("Yul Brynner (A6)");
        assertTrue("Is the map size incremented in 1?", builder.getNodesMap().size() == mapSize + 1);
        assertEquals("Yul Brynner (A6)", node.getReviewerName());
    }
    
    @Test
    public void testGetNode(){
        ReviewerNode node = new ReviewerNode("Yul Brynner (A6)");
        builder.getNodesMap().put("Yul Brynner (A6)", node);
        
        assertSame(node, builder.getNodeOrCreateIfNew("Yul Brynner (A6)"));
    }
    
    @Test
    public void testGetReviewerName(){
        String line = " Yul Brynner (A6) reviews Waheeda Rehman (A5) ";
        assertEquals("Yul Brynner (A6)", builder.getReviewerName(line));
    }
    
    @Test
    public void testGetRevieweeName(){
        String line = " Yul Brynner (A6) reviews Waheeda Rehman (A5) ";
        assertEquals("Waheeda Rehman (A5)", builder.getRevieweeName(line));
    }
}
