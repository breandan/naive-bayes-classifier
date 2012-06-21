import java.util.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import quickdt.*;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.json.simple.*;

import quickdt.scorers.Scorer1;

import com.google.common.collect.*;
public class TestClassifier 
{	
	public static void main(String[] args) throws Exception 
	{
//		final BufferedReader br = new BufferedReader(new InputStreamReader((new GZIPInputStream(new FileInputStream(
//				new File(new File(System.getProperty("user.dir")), "mobo1.txt.gz"))))));
//
//		final List<Instance> instances = Lists.newLinkedList();
//
//		int count = 0;
//		while (true) {
//			count++;
//			final String line = br.readLine();
//			if (line == null) {
//				break;
//			}
//			final JSONObject jo = (JSONObject) JSONValue.parse(line);
//			final Attributes a = new Attributes();
//			a.putAll((JSONObject) jo.get("attributes"));
//			instances.add(new Instance(a, (String) jo.get("output")));
//		}
//
//		System.out.println("Read " + instances.size() + " instances");
//
//		NaiveBayesClassifier nbc = new NaiveBayesClassifier(instances);
//		Table t = nbc.buildTable();
//		t.getClassification(instances.get(99).attributes);
		
		
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
	
	    NaiveBayesClassifier nbc = new NaiveBayesClassifier(instances);
	    Serializable r = nbc.buildTable().getClassification(Attributes.create("height", 75, "weight", 100, "gender", "male"));
	    if (r.equals("healthy")) {
	        System.out.println("They are healthy!");
	    } else if (r.equals("underweight")) {
	        System.out.println("They are underweight!");
	    } else {
	        System.out.println("They are overweight!");
	    }
	    
//	    
////	    TreeBuilder treeBuilder = new TreeBuilder();
////	    Node tree = treeBuilder.buildTree(instances);
////	    
////	    Leaf leaf = tree.getLeaf(Attributes.create("height", 62, "weight", 201, "gender", "female"));
////	    if (leaf.classification.equals("healthy")) {
////	        System.out.println("They are healthy!");
////	    } else if (leaf.classification.equals("underweight")) {
////	        System.out.println("They are underweight!");
////	    } else {
////	        System.out.println("They are overweight!");
////	    }
	}
	
	public static void buildCPT(HashSet<Instance> instances)
	{
	}
}