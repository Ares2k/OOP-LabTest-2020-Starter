package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;

public class Gantt extends PApplet
{	

	public ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table t = loadTable("C:\\Users\\User\\Desktop\\OOP-LabTest-2020-Starter\\java\\data\\tasks.csv", "header");
		
		for(TableRow row:t.rows()) {

			Task task = new Task(row);
			tasks.add(task);
		}
	}

	public void printTasks()
	{
		for(Task t: tasks) {
			System.out.println(t);
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
	}
}
