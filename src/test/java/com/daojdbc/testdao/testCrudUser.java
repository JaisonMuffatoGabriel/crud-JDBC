package com.daojdbc.testdao;

import java.util.List;

import org.junit.Test;

import com.daojdbc.dao.ClientDAO;
import com.daojdbc.entities.Client;

public class testCrudUser {

	// @Test
	public void testSave() {
		Client client = new Client();
		client.setId(1);
		client.setName("**");
		client.setLogin("**");
		client.setPassword("**");

		ClientDAO dao = new ClientDAO();
		dao.save(client);
		System.out.println("Client save successful!!");
	}

	// @Test
	public void testUpdate() {
		Client client = new Client();
		client.setName("**");
		client.setLogin("**");
		client.setPassword("**");
		client.setId(1);

		ClientDAO dao = new ClientDAO();
		dao.update(client);
		System.out.println("Client update successful!!");
	}

	// @Test
	public void testDelete() {
		ClientDAO dao = new ClientDAO();
		dao.delete("**");
		System.out.println("Delete successful!!");
	}

	@Test
	public void testFindByNameLike() {
		ClientDAO dao = new ClientDAO();
		List<Client> list = dao.findByNameLike("**");
		for (Client client : list) {
			System.out.println(client);
			System.out.println("----------------------------------");
		}
	}

	// @Test
	public void testFindById() {
		ClientDAO dao = new ClientDAO();
		Client client = dao.findById(1);
		System.out.println(client);
		System.out.println("--------------------------------------");
	}

	// @Test
	public void testList() {
		ClientDAO dao = new ClientDAO();
		List<Client> list = dao.findAll();
		for (Client client : list) {
			System.out.println(client);
			System.out.println("----------------------------------");
		}
	}

	//@Test
	public void testLogin() {
		ClientDAO dao = new ClientDAO();
		dao.login("**", "**");
	}

}
