package ie.tudublin;

import processing.data.TableRow;

public class Task {

    private String task;
    private int start;
    private int end;

    public void setTask(String task) {
        this.task = task;
    }
    
    public String getTask() {
        return this.task;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return this.start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getEnd() {
        return this.end;
    }

    public Task(String task, int start, int end) {
        this.task = task;
        this.start = start;
        this.end = end;
    }

    public Task(TableRow tr) {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    public String toString() {
        return "Task name: " + task + ", Start: " + start + ", End: " + end;
    }
}   