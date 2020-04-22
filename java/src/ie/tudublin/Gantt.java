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

		for(int i=0;  i<tasks.size(); i++) {

			fill(255);
			x = map(i, 0, tasks.size(), 2 * marg, height - marg);
			text(tasks.get(i).getTask(), marg, x);

			noStroke();
			float color = map(i, 0, tasks.size(), 0, 255);

			fill(color, 255, 255);
			rectStart = map(tasks.get(i).getStart(), 1, 30, leftMarg, width - marg);
			rectEnd = map(tasks.get(i).getEnd(), 1, 30, leftMarg, width - marg);
			rectWidth = (int) (rectEnd - rectStart);

			float s = (rectHeight / 2);
			rect(rectStart, x - s, rectWidth, rectHeight, radius);
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
