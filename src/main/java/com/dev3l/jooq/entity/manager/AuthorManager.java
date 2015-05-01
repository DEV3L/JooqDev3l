package com.dev3l.jooq.entity.manager;

import static generated.dev3l.jooq.tables.Author.AUTHOR;
import generated.dev3l.jooq.tables.records.AuthorRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectJoinStep;

import com.dev3l.jooq.entity.AuthorEntity;
import com.dev3l.jooq.entity.mapper.AuthorMapper;

public class AuthorManager {
	private static final Logger logger = LogManager.getLogger();

	private DSLContext dslContext = null;

	public AuthorManager(final DSLContext dslContext) {
		this.dslContext = dslContext;
	}

	public AuthorEntity create(final AuthorEntity author) {
		if (author == null) {
			logger.warn("Cannot insert null author");
			return null;
		}

		final AuthorRecord authorRecord = dslContext.insertInto(AUTHOR, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME).values(author.getFirstName(), author.getLastName()).returning()
				.fetchOne();

		return AuthorMapper.map(authorRecord);
	}

	public List<AuthorEntity> read(final Collection<Condition> conditions) {
		final SelectJoinStep<Record> selectJoinStep = dslContext.select().from(AUTHOR);

		if ((conditions != null) && !conditions.isEmpty()) {
			selectJoinStep.where(conditions);
		}

		final Result<Record> results = selectJoinStep.fetch();
		final List<AuthorEntity> authors = new ArrayList<AuthorEntity>();

		for (final Record record : results) {
			authors.add(AuthorMapper.map(record));
		}

		return authors;
	}

	public List<AuthorEntity> read() {
		return read(null);
	}

	public AuthorEntity get(final Integer id) {
		final List<AuthorEntity> authors = read(createCollectionFromSingleCondition(AUTHOR.ID.equal(id)));

		if ((authors == null) || (authors.size() != 1)) {
			logger.warn("Author record not found for id: " + id);
			return null;
		}

		return authors.get(0);
	}

	public Integer update(final AuthorEntity author) {
		if (author == null) {
			logger.warn("Cannot update null author");
			return null;
		}

		return dslContext.update(AUTHOR).set(AUTHOR.FIRST_NAME, author.getFirstName()).set(AUTHOR.LAST_NAME, author.getLastName()).execute();
	}
	
	public Integer delete(final Integer id) {
		return dslContext.delete(AUTHOR).where(AUTHOR.ID.equal(id)).execute();
	}

	private Collection<Condition> createCollectionFromSingleCondition(final Condition condition) {
		final List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(condition);
		return conditions;
	}
}
