package com.dev3l.jooq.entity.mapper;

import static generated.dev3l.jooq.tables.Author.AUTHOR;

import org.jooq.Record;

import com.dev3l.jooq.entity.AuthorEntity;

public class AuthorMapper {
	private AuthorMapper() {}
	
	public static AuthorEntity map(Record record) {
		return new AuthorEntity(record.getValue(AUTHOR.ID), record.getValue(AUTHOR.FIRST_NAME), record.getValue(AUTHOR.LAST_NAME));
	}
}
