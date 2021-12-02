package dao;

import model.Todo;
import util.JDBCUtils;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao{

	private static final String INSERT_TODOS_SQL =
			"INSERT INTO todos" +
			"(task_name, FK_user_id_users, target_date, is_done) " +
			"VALUES (?, ?, ?, ?);" ;
	private static final String SELECT_TODO_BY_ID =
			"SELECT task_id, task_name, target_date, is_done" +
					" FROM todos WHERE task_id = ? " +
					"ORDER BY target_date DESC;";
	private static final String SELECT_ALL_TODOS =
			"SELECT * FROM todos WHERE FK_user_id_users = ? " +
					"ORDER BY target_date DESC;";

	private static final String SELECT_TODOS_TODAY =
			"SELECT * FROM todos " +
					"WHERE FK_user_id_users = ? AND (target_date = current_date);";
	private static final String SELECT_TODOS_2DAYS
			= "SELECT * FROM todos " +
				"WHERE FK_user_id_users = ? " +
				"AND (" +
				"target_date  BETWEEN current_date AND " +
				"(ADDDATE(current_date, INTERVAL 1 DAY))" +
				") " +
				"ORDER BY target_date DESC;";

	private static final String DELETE_TODO_BY_ID =
			"DELETE FROM todos WHERE task_id = ? " +
			"ORDER BY target_date DESC;";
	private static final String UPDATE_TODO =
			"UPDATE todos SET task_name = ?, target_date = ?, is_done = ? " +
					"WHERE task_id = ?;";

	public TodoDaoImpl(){}

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		System.out.println(INSERT_TODOS_SQL);
		try(Connection connection = JDBCUtils.getConnection();
		    PreparedStatement preparedStatement =
				    connection.prepareStatement(INSERT_TODOS_SQL)){
			preparedStatement.setString(1, todo.getTask_name());
			//todo check whether the user id works well.
			preparedStatement.setString(2, todo.getUser_id());
			preparedStatement.setDate(3,
					JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(4, todo.isStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			JDBCUtils.printSQLException(e);
		}
	}

	@Override
	public Todo selectTodo(int task_Id, int user_Id) {
		Todo todo = null;
		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(SELECT_TODO_BY_ID)){
			preparedStatement.setLong(1,task_Id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int task_id = resultSet.getInt("task_id");
				String task_name = resultSet.getString("task_name");
				// todo: check the value of user_id
				String user_id= String.valueOf(user_Id);
				LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
				boolean isDone = resultSet.getBoolean("is_done");
				todo = new Todo(task_id, task_name, user_id, targetDate, isDone);
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return todo;
	}


	@Override
	public List<Todo> listTodos(int userId, String action) {

		List<Todo> todos = new ArrayList<>();
		String sqlString;
		switch(action){
			case "/listtoday":
				sqlString = SELECT_TODOS_TODAY;
				break;
			case "/list2days":
				sqlString =SELECT_TODOS_2DAYS;
				break;
			default:
				sqlString = SELECT_ALL_TODOS;
		}

		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(sqlString)){
			preparedStatement.setInt(1, userId);
			System.out.println("selectAllTodos: " + preparedStatement + ", " +
					"action = " + action);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){

				int task_id = resultSet.getInt("task_id");
				String task_name = resultSet.getString("task_name");
				String user_id = String.valueOf(userId);
				LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
				boolean isDone = resultSet.getBoolean("is_done");

				todos.add(new Todo(task_id, task_name, user_id, targetDate,
						isDone));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return todos;
	}

	public List<Todo> searchTodo(int userId, String keyword){

		List<Todo> todos = new ArrayList<>();
		String SEARCH_TODOS =
			"SELECT * FROM todos WHERE FK_user_id_users = " + userId +
					" AND (task_name LIKE '%" + keyword +"%') " +
					"ORDER BY target_date DESC;";

		try(Connection connection = JDBCUtils.getConnection();
		    PreparedStatement preparedStatement =
				    connection.prepareStatement(SEARCH_TODOS)){
			System.out.println("searchTodos: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){

				int task_id = resultSet.getInt("task_id");
				String task_name = resultSet.getString("task_name");
				String user_id = String.valueOf(userId);
				LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
				boolean isDone = resultSet.getBoolean("is_done");

				todos.add(new Todo(task_id, task_name, user_id, targetDate,
						isDone));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return todos;
	}


	@Override
	public boolean deleteTodo(int task_id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(DELETE_TODO_BY_ID)){
			preparedStatement.setInt(1, task_id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(UPDATE_TODO)){

			preparedStatement.setString(1, todo.getTask_name());
			preparedStatement.setDate(2,
					JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(3, todo.isStatus());
			preparedStatement.setLong(4, todo.getTask_id());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
