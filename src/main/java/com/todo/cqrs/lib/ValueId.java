package com.todo.cqrs.lib;

import java.util.Objects;

/**
 * Created by 6/21/17.
 */
public class ValueId {

    public final String id;

    public static final String ID_PATTERN =
            "^(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12})$";

    public ValueId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValueId)) return false;
        ValueId valueId = (ValueId) o;
        return Objects.equals(id, valueId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }

}
