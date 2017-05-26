
public class Duration 
{
	private long startTime;
	private long stopTime;
	private String startMessage;
	private String endMessage;
	private long totalTime;

	private String name;

	public Duration()
	{
		setStartTime(0);
		setStopTime(0);
		setStartMessage("");
		setEndMessage("");
	}
	
	public void setName(String n)
	{
		
		name = n;
		
	}
	public String getName()
	{
		return name;
	}
	public void setStartTime(long start)
	{
		startTime = start;
		//System.out.println(startTime);
	}

	public long getStartTime()
	{
		return startTime;
	}
		
	
	public void setStopTime(long stop)
	{
		stopTime = stop;
		//System.out.println(stopTime);	
	}

	public long getStopTime()
	{
		return stopTime;
	}

	public void setStartMessage(String startMes)
	{
		startMessage = startMes;
	}
	
	public String getStartMessage()
	{
		return startMessage;
	}

	public void setEndMessage(String endMes)
	{
		endMessage = endMes;
	}
		
	public String getEndMessage()
	{
		return endMessage;
	}
	
	public long getTotalTime()
	{   
		totalTime = getStopTime() - getStartTime();
		
		return totalTime;
	}


	
	
}
