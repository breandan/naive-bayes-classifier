import java.io.Serializable;
import java.util.HashSet;

import quickdt.Attributes;
import quickdt.Instance;

import com.google.common.collect.Sets;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCases extends TestCase 
{
	public static NaiveBayesClassifier nbc;
	public static Table tbl;

	public void testFrequency() throws Exception 
	{
		final HashSet<Instance> instances = Sets.newHashSet();
	    // A male weighing 168lb that is 55 inches tall, they are overweight
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 75, "weight", 168, "gender", "female").classification("healthy"));
	    instances.add(Attributes.create("height", 74, "weight", 143, "gender", "male").classification("underweight"));
	    instances.add(Attributes.create("height", 49, "weight", 144, "gender", "female").classification("underweight"));
	    instances.add(Attributes.create("height", 83, "weight", 223, "gender", "male").classification("healthy"));
	
	    nbc = new NaiveBayesClassifier(instances);
	    tbl = nbc.buildTable();
	    
	    assertEquals("overweight", tbl.getClassification(Attributes.create("height", 54, "weight", 209, "gender", "male")).toString());
	}
	
	public void testExact() throws Exception 
	{
		final HashSet<Instance> instances = Sets.newHashSet();
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 75, "weight", 168, "gender", "female").classification("healthy"));
	    instances.add(Attributes.create("height", 74, "weight", 143, "gender", "male").classification("underweight"));
	    instances.add(Attributes.create("height", 49, "weight", 144, "gender", "female").classification("underweight"));
	    instances.add(Attributes.create("height", 83, "weight", 223, "gender", "male").classification("healthy"));
	
	    nbc = new NaiveBayesClassifier(instances);
	    tbl = nbc.buildTable();
	    
	    assertEquals("underweight", tbl.getClassification(Attributes.create("height", 49, "weight", 144, "gender", "female")).toString());
	}
	
	public void testApproximate() throws Exception 
	{
		final HashSet<Instance> instances = Sets.newHashSet();
	    // A male weighing 168lb that is 55 inches tall, they are overweight
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 75, "weight", 168, "gender", "female").classification("healthy"));
	    instances.add(Attributes.create("height", 74, "weight", 143, "gender", "male").classification("underweight"));
	    instances.add(Attributes.create("height", 49, "weight", 144, "gender", "female").classification("underweight"));
	    instances.add(Attributes.create("height", 83, "weight", 223, "gender", "male").classification("healthy"));
	
	    nbc = new NaiveBayesClassifier(instances);
	    tbl = nbc.buildTable();
	    
	    assertEquals("underweight", tbl.getClassification(Attributes.create("height", 48, "weight", 143, "gender", "female")).toString());
	}
	
	public void testBoundaryCondition() throws Exception 
	{
		final HashSet<Instance> instances = Sets.newHashSet();
	    // A male weighing 168lb that is 55 inches tall, they are overweight
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 55, "weight", 210, "gender", "male").classification("overweight"));
	    instances.add(Attributes.create("height", 75, "weight", 168, "gender", "female").classification("healthy"));
	    instances.add(Attributes.create("height", 74, "weight", 143, "gender", "male").classification("underweight"));
	    instances.add(Attributes.create("height", 49, "weight", 144, "gender", "female").classification("underweight"));
	    instances.add(Attributes.create("height", 83, "weight", 223, "gender", "male").classification("healthy"));
	
	    nbc = new NaiveBayesClassifier(instances);
	    tbl = nbc.buildTable();
	    
	    assertEquals("healthy", tbl.getClassification(Attributes.create("height", 55, "weight", 221, "gender", "female")).toString());
	}
}
