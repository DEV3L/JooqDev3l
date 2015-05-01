package com.dev3l.jooq.entity.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.LogManager;

import org.jooq.Condition;

public class AbstractManager {
	// hack to reset jOOQ logging
	static {
		LogManager.getLogManager().reset();
	}

	Collection<Condition> createCollectionFromSingleCondition(final Condition condition) {
		final List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(condition);
		return conditions;
	}
}
