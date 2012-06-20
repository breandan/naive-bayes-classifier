import java.util.*;
import quickdt.*;

//Author: Breandan Considine

public class NaiveBayesClassifier
{
	private Iterable<Instance> data;
	private Attributes testSample;
	public static int vicinity = 10;
	
	public NaiveBayesClassifier(final Iterable<Instance> trainingData, final Attributes unknown)
	{
		this.data = trainingData;
		testSample = unknown;
	}
	
	public void run()
	{
		int sample_size = 0;
		Iterator<Instance> i = data.iterator();
		HashMap<String, Integer> base_ps = new HashMap<String, Integer>();
		
		//Identify the base probabilities.
		while(i.hasNext())
		{
			String classification = i.next().classification.toString();

			if(base_ps.containsKey(classification))
					base_ps.put(classification, base_ps.get(classification)+1);
			else
				base_ps.put(classification, new Integer(1));
			
			sample_size++;
		}
		
		//Identify the priors.
		HashMap<String, Float> priors = new HashMap<String, Float>();
		
		//Iterate through the data to identify matches.
		for(String s : testSample.keySet())
		{
			//Maps each class's to prior probabilities for a given attribute.
			HashMap<String, Float> attribute_priors = new HashMap<String, Float>();
			i = data.iterator();
			
			//Scores each class's attribute prior, by iterating through the dataset.
			while(i.hasNext())
			{
				Instance is = i.next();
				try
				{
					//System.out.println("Number detected!");
					int num1 = Integer.parseInt(is.attributes.get(s).toString());
					int num2 = Integer.parseInt(testSample.get(s).toString());
					if(num1 > (num2 - vicinity) && num1 < (num2 + vicinity))
					{
						String c = is.classification.toString();
						if(attribute_priors.containsKey(c))
							attribute_priors.put(c, attribute_priors.get(c) + 1F);
						else
							attribute_priors.put(c, 1F);
					}
				}
				catch(Exception e)
				{
					//System.out.println("String detected!");
					String s1 = is.attributes.get(s).toString();
					String s2 = is.attributes.get(s).toString();
					if(s1.equals(s2))
					{
						String c = is.classification.toString();
						if(attribute_priors.containsKey(c))
							attribute_priors.put(c, attribute_priors.get(c) + 1F);
						else
							attribute_priors.put(c, 1F);
					}
				}
			}
			
			//Now, let's go ahead and drop them in (insert or multiply)
			for(String c : attribute_priors.keySet())
			{
				if(priors.containsKey(c))
					priors.put(c, priors.get(c) * (attribute_priors.get(c)/base_ps.get(c))); //In-place multiply.
				else
					priors.put(c, attribute_priors.get(c)/base_ps.get(c)); //Initial insert
			}
		}
		
		//Finally multiply by the base probability p(c)
		for(String c : base_ps.keySet())
			priors.put(c, priors.get(c)*base_ps.get(c)/sample_size);
		
		//Priors are now the final normalized classification probability.
		System.out.println(priors.toString());
		
		float m = 0;
		String p = "?";
		
		for(String s : priors.keySet())
			if(priors.get(s) > m)
			{
				m = priors.get(s);
				p = s;
			}
		
		System.out.println("Prediction: " + p);
	}
}
