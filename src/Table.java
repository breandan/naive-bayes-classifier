import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import quickdt.*;
import org.javatuples.Pair;

public class Table
{
	private ClassificationCounter counter;
	private int[][] ACT; //Attribute classification table
	private Map<Serializable, Integer> classMap;
	private Map<String, Integer> attributeMap; //Index mappings
	public Map<Serializable, Integer> classTotals;
	public Iterable<Instance> data;
	public int sampleSize;
	public int vicinity = 10;
	
	public Table(final Iterable<Instance> trainingData)
	{
		data = trainingData;
		classMap = new HashMap<Serializable, Integer>();
		attributeMap = new HashMap<String, Integer>();
		classTotals = new HashMap<Serializable, Integer>();
		
		counter = ClassificationCounter.countAll(data);
		
		for(Serializable s : counter.allClassifications())
			classTotals.put(s.toString(), counter.getCount(s));
		sampleSize = counter.getTotal();
		
		Iterator<Serializable> ci = counter.allClassifications().iterator();
		for(int i = 0; ci.hasNext(); i++)
			classMap.put(ci.next(), i);
		
		Iterator<String> ai = trainingData.iterator().next().attributes.keySet().iterator();		
		for(int i = 0; ai.hasNext(); i++)
			attributeMap.put(ai.next(), i);
		
		ACT = new int[classMap.size()][attributeMap.size()];
		
	}
	
	public int fetchCP(Serializable classification, String attribute)
	{
		return ACT[classMap.get(classification)][attributeMap.get(attribute)];
	}
	
	public void setCP(Serializable classification, String attribute, int i)
	{
		ACT[classMap.get(classification)][attributeMap.get(attribute)] = i;
	}
	
	public Serializable getClassification(Attributes attributes)
	{
		for(String a : attributes.keySet())
		{
			Pair<ClassificationCounter, Map<Serializable, ClassificationCounter>> 
					attributeCounter = ClassificationCounter.countAllByAttributeValues(data, a);
			
			for(Serializable c : classMap.keySet())
			{
				String att = attributes.get(a).toString();
				
				int count = 1;
				try
				{
					Integer num = Integer.parseInt(att);

					for(int j = num - vicinity; j < num + vicinity; j++)
						if(attributeCounter.getValue1().containsKey(j))
							count += attributeCounter.getValue1().get(j).getCount(c);

					setCP(c, a, count);
				}
				catch(Exception e)
				{					
					ClassificationCounter cc = attributeCounter.getValue1().get(att);
					
					
					if(cc != null && cc.getCount(c) > 0)
						setCP(c, a, cc.getCount(c));
					else
						setCP(c, a, count);
				}				
			}
		}
				
		BigDecimal max = new BigDecimal(0);
		Serializable rc = classTotals.keySet().iterator().next();
		
		for(Serializable c : classTotals.keySet())
		{
			BigDecimal d = new BigDecimal(classTotals.get(c));
			BigDecimal p_c = d.divide(new BigDecimal(sampleSize), 20, 1);
			
			int idx = classMap.get(c);
			for(int i = 0; i < ACT[idx].length; i++)
				p_c = p_c.multiply((new BigDecimal(ACT[idx][i])).divide(d, 20, 1));
						
			if(p_c.compareTo(max) > 0)
			{
				rc = c;
				max = p_c;
			}
		}
			
		return rc;
	}
	
	public String toString()
	{
		String contents = "";
		for(int i = 0; i < ACT.length; i++)
		{
			for(int j = 0; j < ACT[0].length; j++)
				contents += ACT[i][j] + " ";
			contents += "\n";
		}
		
		return "sample size:" + sampleSize + "\n" +
				"totals:" + classTotals.toString() + "\n" +
				"class indices:" + classMap.toString() + "\n" +
				"attribute indices:" + attributeMap.toString() + "\n\n" + 
				"CONTENTS\n" + contents;
	}
}
