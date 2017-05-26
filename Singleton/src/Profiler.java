import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Profiler
{
	//reference to a shared (static) Profiler object
	private static Profiler instance = null;
	private long counter;
	private boolean isEna = true;
	private Duration duration;
	private HashMap<String, ArrayList<Duration>> d = new HashMap<String, ArrayList<Duration>>();
	private HashMap<String, Long>counts = new HashMap<String, Long>();

	//private constructor, can only be called from a method of this class
	public Profiler()
	{
		
		//initialize the Profiler here
	}
	
	//static method that returns a reference to the one Profiler object
	public static Profiler getInstance()
	{
		//if the one instance has not been created yet
		if(instance == null)
		{
			//create the one instance
			instance = new Profiler();
		}
		//else it has
		
		//return a reference to the one instance
		return instance;
	}
	
	//profiler stuff
	public void start(String string)
	{
		if(isEnabled() == true)
		{
			//start a profiler
			duration = new Duration();
			duration.setStartTime(System.nanoTime());
			duration.setStartMessage("");
			duration.setName(string);
			//d.put(string, duration);
								
		}
	 }
	//profiler stuff
	public void stop(String string)
	{
		if(isEnabled() == true)
		{
			//stop a profiler
			duration.setStopTime(System.nanoTime());
			duration.setEndMessage("");
			if(d.get(string) != null)
			{
				d.get(string).add(duration);

			}
			else
			{
				d.putIfAbsent((string), new ArrayList<Duration>());
				d.get(string).add(duration);
			}
			//d.put(string, duration);
		}
		


	}
	
	//profiler stuff
	public void start(String string, String msg)
	{
		if(isEnabled() == true)
		{
			//start a profiler
			duration = new Duration();
			duration.setStartTime(System.nanoTime());
			duration.setStartMessage(msg);
			duration.setName(string);
			//d.put(string, duration);

			//d.put(string, duration);

							
		}
	}
	//profiler stuff
	public void stop(String string, String msg)
	{
			
		//stop a profiler
		if(isEnabled() == true)
		{
			duration.setStopTime(System.nanoTime());
			duration.setEndMessage(msg);
			if(d.get(string) != null)
			{
				d.get(string).add(duration);

			}
			else
			{
				d.putIfAbsent((string), new ArrayList<Duration>());
				d.get(string).add(duration);
			}
			//d.put(string, duration);
		}
			


	}
			
	//profiler stuff
	public void count(String name)
	{
			if(counts.containsKey(name))
			{
				counter++;
				counts.replace(name,counter);
			}
			else
			{
				counts.put(name, counter);
			}
			
			
			//return counts.get(name);
			
			System.out.println("COUNTS:" + counts.get(name));
	
	
	}
	

	public void setEnabled(boolean enabled)
	{
		isEna = enabled;

		
	}
	
	public boolean isEnabled()
	{
		return isEna;
	}
	

	public void generateReport2()
	{
		for(Entry<String, ArrayList<Duration>> e : d.entrySet())
		{
		
			long average = 0;
			String key = e.getKey();
			System.out.println("NAME OF TIMER " + key);
			
			ArrayList<Duration> values = e.getValue();
			long longest = (values.get(0).getStopTime() - values.get(0).getStartTime());
			long shortest = (values.get(0).getStartTime()- values.get(0).getStopTime());

			longest = 0;
			shortest = Long.MAX_VALUE;
			
			for(int i = 0; i < values.size(); i++)
			{
				System.out.println("START: " + values.get(i).getStartTime());
				System.out.println("END: " + values.get(i).getStopTime());
				
				long startTime = values.get(i).getStartTime();
				long endTime = values.get(i).getStopTime();
				long processTime = endTime - startTime;
				System.out.println("DURATION" + processTime);
				
				//longest = processTime;
				
				//shortest = processTime;
				longest = 0;
				shortest = Long.MAX_VALUE;
				
				if(shortest > processTime)
				{
					shortest = processTime;
				}
				if(longest < processTime)
				{
					longest = processTime;
				}
				average = average + processTime;

			}
			average = average / values.size();
			System.out.println("AVG: " + average);
			System.out.println("SHORTEST: " + shortest);
			System.out.println("LONGEST: " + longest);

		

			

		}
		
	}

	
	public void generateReport()
	{
		JFrame report = new JFrame();
		report.setTitle("Tabbed Pane");
	
        JTabbedPane jtp = new JTabbedPane();
        report.getContentPane().add(jtp);
        
        
        
		for(Entry<String, ArrayList<Duration>> e : d.entrySet())
		{
			
	        JPanel jp1 = new JPanel();
	        JLabel label1 = new JLabel();
	        JLabel label2 = new JLabel();
	        JLabel label3 = new JLabel();
	        JLabel label4 = new JLabel();
	        JLabel label5 = new JLabel();
	        JLabel label6 = new JLabel();

	        label1.setText("All the times");
			ArrayList<Duration> values = e.getValue();
			long longest = (values.get(0).getStopTime() - values.get(0).getStartTime());
			long shortest = (values.get(0).getStartTime()- values.get(0).getStopTime());
			long average = 0;
	        for(int i = 0; i < values.size(); i++)
			{
				label1.setText("START: " + values.get(i).getStartTime() + "");
				label2.setText("END: " + values.get(i).getStopTime() + "");
				
				long startTime = values.get(i).getStartTime();
				long endTime = values.get(i).getStopTime();
				long processTime = endTime - startTime;
				label3.setText("DURATION: " + processTime + "");
				longest = 0;
				shortest = Long.MAX_VALUE;
				

				if(shortest > processTime)
				{
					shortest = processTime;
				}
				if(longest < processTime)
				{
					longest = processTime;
				}
				average = average + processTime;

			
			        
			        
			}
	        average = average / values.size();
			label4.setText("AVG: " + average + "");
			label5.setText("SHORTEST: " + shortest+ "");
			label6.setText("LONGEST: " + longest+ "");
	        
	  	  jp1.add(label1);
	  	  jp1.add(label2);
	  	  jp1.add(label3);
	  	  jp1.add(label4);
	  	  jp1.add(label5);
	  	  jp1.add(label6);
	      jtp.addTab(e.getKey(), jp1);
	      
		}
		
		
	      JPanel jp2 = new JPanel();
	     
	      jtp.addTab("Counter", jp2);
	        
	          
	        Object rowData[][] = new Object[d.keySet().size()][6];
    
	      String column[]={"Counts"}; 
	      int index = 0;
	        
			for(Entry<String, ArrayList<Duration>> f : d.entrySet())
			{		
				rowData[index][0] = f.getValue();
				index++;
		
				
			}
	      JTable jt=new JTable(rowData,column);    
	      JScrollPane sp=new JScrollPane(jt);    
	      jp2.add(sp, BorderLayout.SOUTH);          
	  

        
		 report.setSize(300, 150);
	     report.setVisible(true);
	     report.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        

	}
}
