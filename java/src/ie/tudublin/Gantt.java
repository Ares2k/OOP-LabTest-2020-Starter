package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;

public class Gantt extends PApplet
{	

	public ArrayList<Task> tasks = new ArrayList<Task>();
	private float leftMarg;
	private float marg;
	
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

	public void displayTasks() {

		int days = 30;
		float rectHeight = 50;
		float rectStart, rectEnd, rectWidth;
		float radius = 6;
		float c;
		float x;

		stroke(255);
		fill(255);
		textAlign(CENTER);

		for(int i=1; i<=days; i++) {

			c = map(i, 1, 30, leftMarg, width - marg);
			line(c, marg, c, height - marg);
			text(i, c, marg * 0.8f);
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
		leftMarg = width / 6;
		marg = width / 20;
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		colorMode(HSB);
		background(0);
		displayTasks();
	}
}
