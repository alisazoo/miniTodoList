package dao;

import model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface TodoDao {

	void insertTodo(Todo todo) throws SQLException;

	Todo selectTodo(int taskId, int userId);

	List<Todo> listTodos(int user_id, String action);

	boolean deleteTodo(int id) throws SQLException;

	boolean updateTodo(Todo todo) throws SQLException;

}
