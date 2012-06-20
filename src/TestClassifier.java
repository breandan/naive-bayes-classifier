import java.util.*;
import quickdt.*;

public class TestClassifier 
{
	public static int sample_size;
	public static int vicinity;
	public static int[][] CPT;
	
	public static void main(String[] args) 
	{
		final HashSet<Instance> instances = new HashSet<Instance>();
	    // A male weighing 168lb that is 55 inches tall, they are overweight
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 75, "weight", 168, "gender", "female").classification("healthy"));
	    instances.add(Attributes.create("height", 74, "weight", 143, "gender", "male").classification("underweight"));
	    instances.add(Attributes.create("height", 49, "weight", 144, "gender", "female").classification("underweight"));
	    instances.add(Attributes.create("height", 83, "weight", 223, "gender", "male").classification("healthy"));
	
	    NaiveBayesClassifier nbc = new NaiveBayesClassifier(instances, Attributes.create("height", 46, "weight", 210, "gender", "male"));
	    nbc.run();
	    
//	    TreeBuilder treeBuilder = new TreeBuilder();
//	    Node tree = treeBuilder.buildTree(instances);
//	    
//	    Leaf leaf = tree.getLeaf(Attributes.create("height", 62, "weight", 201, "gender", "female"));
//	    if (leaf.classification.equals("healthy")) {
//	        System.out.println("They are healthy!");
//	    } else if (leaf.classification.equals("underweight")) {
//	        System.out.println("They are underweight!");
//	    } else {
//	        System.out.println("They are overweight!");
//	    }
	}
	
	public static void buildCPT(HashSet<Instance> instances)
	{
	}
}