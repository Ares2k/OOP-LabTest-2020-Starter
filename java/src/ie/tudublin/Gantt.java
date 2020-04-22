package ie.tudublin;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;
import java.util.ArrayList;

public class Gantt extends PApplet
{	

	//declaring member variables
	public ArrayList<Task> tasks = new ArrayList<Task>();
	private int leftSidePressed = -1;
	private int rightSidePressed = -1;
	private float leftMarg;
	private float marg;
	int days = 30;
	
	public void settings()
	{
		size(800, 600);
	}

	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");
		
		for(TableRow row: t.rows()) {

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

		float rectHeight = 30;
		float rectStart, rectEnd, rectWidth;
		float fontSize = 0.85f;
		float radius = 5;

		textAlign(CENTER);
		stroke(255);
		fill(255);

		for(int i=1; i<=days; i++) {

			//set every 2nd line to slightly darker 
			if(i % 2 == 0) {

				stroke(102);

			} else {

				stroke(255);
			}

			float c = map(i, 1, 30, leftMarg, width - marg);
			line(c, marg, c, height - marg);
			text(i, c, marg * fontSize);
		}

		for(int i=0;  i<tasks.size(); i++) {

			fill(255);
			float x = map(i, 0, tasks.size(), 2 * marg, height - marg);
			text(tasks.get(i).getTask(), marg, x);

			noStroke();
			float color = map(i, 0, tasks.size(), 0, 255);

			fill(color, 255, 255);
			rectStart = map(tasks.get(i).getStart(), 1, days, leftMarg, width - marg);
			rectEnd = map(tasks.get(i).getEnd(), 1, days, leftMarg, width - marg);
			rectWidth = (rectEnd - rectStart);

			float s = (rectHeight / 2);
			rect(rectStart, x - s, rectWidth, rectHeight, radius);
		}
	}
	
	private void hoverMouse() {
		
		float rectHeight = 30;
		float x1, x2, y1, y2;
		float size = 20;

		for(int i=0; i<tasks.size(); i++) {
			
			float halfHeight = rectHeight / 2;
			
			Task task = tasks.get(i);
			x1 = map(task.getStart(), 1, 30, leftMarg, width - marg);
			x2 = map(task.getEnd(), 1, 30, leftMarg, width - marg);
			
			y1 = map(i, 0, tasks.size(), 2 * marg, height - marg) - halfHeight;
			y2 = rectHeight + y1;

			if((mouseX > x1 - size && mouseX < x1 + size) || (mouseX < x2 + size && mouseX > x2 - size)) {
				cursor(HAND);
  			 } else {
				cursor(ARROW);
   			}
		}
	}

	public void mousePressed()
	{
		println("Mouse pressed");
		
		float rectHeight = 30;
		float x1, x2, y1, y2;
		float size = 20;

		for(int i=0; i<tasks.size(); i++) {
			
			float halfHeight = rectHeight / 2;
			
			Task task = tasks.get(i);
			x1 = map(task.getStart(), 1, 30, leftMarg, width - marg);
			x2 = map(task.getEnd(), 1, 30, leftMarg, width - marg);
			
			y1 = map(i, 0, tasks.size(), 2 * marg, height - marg) - halfHeight;
			y2 = rectHeight + y1;

			if(mouseY >= y1 && mouseY <= y2) {

				if(mouseX > x1 - size && mouseX < x1 + size) {
					
					rightSidePressed = -1;
					leftSidePressed = i;

				} else if (mouseX < x2 + size && mouseX > x2 - size) {

					rightSidePressed = i;
					leftSidePressed = -1;
				}
			}
		}
	}

	public void mouseDragged()
	{
		println("Mouse dragged");

		int date;

		if(leftSidePressed > -1) {
			
			Task task = tasks.get(leftSidePressed);
			date = (int) map(mouseX, 0, width, 0, days);

			if(task.getEnd() - date >= 1 && date > 0 && date < task.getEnd()) {
				task.setStart(date);
			}

		} else if (rightSidePressed > -1) {

			Task task = tasks.get(rightSidePressed);
			date = (int) map(mouseX, 0, width, 0, days);

			if(date - task.getStart() >= 1 && date <= days && date > task.getStart()) {

				task.setEnd(date);
			}
		}
	}

	//reset mouse position when button is released
	public void mouseReleased() {

		leftSidePressed = -1;
		rightSidePressed = -1;
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
		hoverMouse();
	}
}
