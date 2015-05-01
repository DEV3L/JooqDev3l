package com.dev3l.jooq.entity.manager.test;

import java.sql.DriverManager;
import java.util.List;

import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dev3l.jooq.entity.AuthorEntity;
import com.dev3l.jooq.entity.manager.AuthorManager;

public class AuthorManagerIT {
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";
	private static final String URL = "jdbc:mysql://localhost:3306";

	private AuthorManager authorManager = null;

	@Before
	public void setup() throws Exception {
		authorManager = new AuthorManager(DSL.using(DriverManager.getConnection(URL, USER_NAME, PASSWORD), SQLDialect.MYSQL));
	}

	@Test
	public void testCreate() {
		AuthorEntity author = new AuthorEntity(null, "first_name", "last_name");
		author = authorManager.create(author);
		Assert.assertNotNull(author);
		Assert.assertNotNull(author.getId());
	}

	@Test
	public void testRead() {
		testCreate();
		
		final List<AuthorEntity> authors = authorManager.read(null);

		Assert.assertNotNull(authors);
		Assert.assertTrue(authors.size() > 0);

		final AuthorEntity author = authorManager.get(authors.get(0).getId());

		Assert.assertNotNull(author);
		Assert.assertNotNull(author.getId());
		Assert.assertTrue(author.getId() == authors.get(0).getId());
	}

	@Test
	public void testUpdate() {
		AuthorEntity author = new AuthorEntity(null, "first_name", "last_name");
		author = authorManager.create(author);
		Assert.assertNotNull(author);
		Assert.assertNotNull(author.getId());

		author.setFirstName("name_first");
		author.setLastName("name_last");

		Assert.assertTrue(authorManager.update(author) > 0);

		author = authorManager.get(author.getId());

		Assert.assertEquals(author.getFirstName(), "name_first");
		Assert.assertEquals(author.getLastName(), "name_last");
	}

	@Test
	public void testDelete() {
		AuthorEntity author = new AuthorEntity(null, "first_name", "last_name");
		author = authorManager.create(author);
		Assert.assertNotNull(author);
		Assert.assertNotNull(author.getId());

		Assert.assertTrue(authorManager.delete(author.getId()) > 0);

		author = authorManager.get(author.getId());

		Assert.assertNull(author);
	}
}
