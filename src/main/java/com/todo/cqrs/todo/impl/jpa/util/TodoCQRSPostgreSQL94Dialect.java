package com.todo.cqrs.todo.impl.jpa.util;

import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

/**
 * Created by 6/26/17.
 */
public class TodoCQRSPostgreSQL94Dialect extends PostgreSQL94Dialect {
    public TodoCQRSPostgreSQL94Dialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
