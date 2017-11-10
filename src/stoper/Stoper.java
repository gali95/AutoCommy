package stoper;

import java.util.ArrayList;

public class Stoper {

	private static ArrayList<StoperSegment> storedOnes;
	private static class StoperSegment {
		
		public String label;
		public double loggedTime;
		public long lastStartTime;
		public boolean isStarted;
		
		public StoperSegment(String label)
		{
			this.label = label;
		}
		
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (obj == null) return false;
		    if (obj == this) return true;
		    if (!(obj instanceof StoperSegment))return false;
		    StoperSegment otherMyClass = (StoperSegment)obj;
		    return(label.equals(otherMyClass.label));
		}
		
	}
	
	static
	{
		storedOnes = new ArrayList<StoperSegment>();
	}
	
	public static boolean Start(String stoperName)
	{
		StoperSegment segment = new StoperSegment(stoperName);
		int location = storedOnes.indexOf(segment);
		if(location == -1)
		{
			segment.lastStartTime = System.nanoTime();
			segment.isStarted = true;
			storedOnes.add(segment);
			return true;
		}
		else
		{
			segment = storedOnes.get(location);
			if(segment.isStarted)
			{
				return false;
			}
			segment.lastStartTime = System.nanoTime();
			segment.isStarted = true;
			return true;
		}
	}
	public static boolean Stop(String stoperName)
	{
		StoperSegment segment = new StoperSegment(stoperName);
		int location = storedOnes.indexOf(segment);
		if(location == -1)
		{
			return false;
		}
		else
		{
			segment = storedOnes.get(location);
			if(!segment.isStarted)
			{
				return false;
			}
			segment.loggedTime += ((double)System.nanoTime() - segment.lastStartTime)/1000000000;
			segment.isStarted = false;
			return true;
		}
	}
	public static double GetTimeSeconds(String stoperName)
	{
		StoperSegment segment = new StoperSegment(stoperName);
		int location = storedOnes.indexOf(segment);
		if(location == -1)
		{
			return 0;
		}
		else
		{
			segment = storedOnes.get(location);
			double retVal = segment.loggedTime;
			if(segment.isStarted)
			{
				retVal += ((double)System.nanoTime() - segment.lastStartTime)/1000000000;
			}
			return retVal;
		}
	}
	
	public static void ResetAll()
	{
		for(StoperSegment segment:storedOnes)
		{	
			segment.isStarted = false;
			segment.loggedTime = 0;
		}
	}
	
	public static String GetAllFlatValsPrintable()
	{
		String retString = "";
		for(StoperSegment printable:storedOnes)
		{	
			retString += printable.label + " - " + GetTimeSeconds(printable.label) + System.lineSeparator();
		}
		retString += System.lineSeparator();
		return retString;
	}
	public static String GetAllPercentageValsPrintable(String baseOfPercentageStoper)
	{
		StoperSegment baseSegment = new StoperSegment(baseOfPercentageStoper);
		int location = storedOnes.indexOf(baseSegment);
		if(location == -1)
		{
			return "basePercentageStoper with that name doesn't exist";
		}
		else
		{
			String retString = "";
			for(StoperSegment printable:storedOnes)
			{
				if(printable.label==baseSegment.label) continue;
				
				retString += printable.label + " - " + GetTimeSeconds(printable.label) / GetTimeSeconds(baseOfPercentageStoper) + System.lineSeparator();
			}
			retString += System.lineSeparator();
			return retString;
		}
	}
	
}
