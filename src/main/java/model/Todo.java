package model;

import java.time.LocalDate;

public class Todo {

	private int task_id;
	private String task_name;
	private String user_id;
	private LocalDate targetDate;
	private boolean status;

	protected Todo(){
	}

	public Todo(int task_id, String task_name, String user_id,
	            LocalDate targetDate, boolean status) {
		this.task_id = task_id;
		this.task_name = task_name;
		this.user_id = user_id;
		this.targetDate = targetDate;
		this.status = status;
	}

	public Todo(String task_name, String user_id, LocalDate targetDate, boolean status) {
		this.task_name = task_name;
		this.user_id = user_id;
		this.targetDate = targetDate;
		this.status = status;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj==null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		return task_id == other.task_id;
// todo check equivalent to above?
//		if(id != other.id)
//			return false;
//		return true;


	}
}
