package com.daojdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daojdbc.connection.SingleConnection;
import com.daojdbc.entities.Client;

public class ClientDAO {

	private Connection connection = SingleConnection.getConnection();

	public void save(Client client) {
		String sql = "insert into client (id, name, login, password) values (?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, client.getId());
			statement.setString(2, client.getName());
			statement.setString(3, client.getLogin());
			statement.setString(4, client.getPassword());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void update(Client client) {
		String sql = "update client set name=?, login=?, password=? where id =" + client.getId();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, client.getName());
			statement.setString(2, client.getLogin());
			statement.setString(3, client.getPassword());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void delete(String name) {
		String sql = "delete from client where name=?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Client> findByNameLike(String name) {
		List<Client> listUser = new ArrayList<Client>();
		String sql = "select * from client where name like '%" + name + "%'";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(Integer.parseInt(resultSet.getString("id")));
				client.setName(resultSet.getString("name"));
				client.setLogin(resultSet.getString("login"));
				client.setPassword(resultSet.getString("password"));
				listUser.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}

	public Client findById(Integer id) {
		String sql = "select * from client where id=?";
		Client client = new Client();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			client.setId(Integer.parseInt(resultSet.getString("id")));
			client.setName(resultSet.getString("name"));
			client.setLogin(resultSet.getString("login"));
			client.setPassword(resultSet.getString("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}

	public List<Client> findAll() {
		List<Client> clientList = new ArrayList<Client>();
		String sql = "select * from client";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(Integer.parseInt(resultSet.getString("id")));
				client.setName(resultSet.getString("name"));
				client.setLogin(resultSet.getString("login"));
				client.setPassword(resultSet.getString("password"));
				clientList.add(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientList;
	}

	public boolean login(String login, String password) {
		String sql = "select from client where login=? and password=?";
		boolean result = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			result = resultSet.next();
			if (result == true) {
				System.out.println("welcome to system.");
			} else {
				System.out.println("Login not find, try again.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
