/*
 * This file is generated by jOOQ.
 */
package io.github.dominys.dizvoter.data.access.impl.jooq.tables;


import io.github.dominys.dizvoter.data.access.impl.jooq.DefaultSchema;
import io.github.dominys.dizvoter.data.access.impl.jooq.Indexes;
import io.github.dominys.dizvoter.data.access.impl.jooq.Keys;
import io.github.dominys.dizvoter.data.access.impl.jooq.tables.PollVoteOptions.PollVoteOptionsPath;
import io.github.dominys.dizvoter.data.access.impl.jooq.tables.records.PollsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Polls extends TableImpl<PollsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>polls</code>
     */
    public static final Polls POLLS = new Polls();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PollsRecord> getRecordType() {
        return PollsRecord.class;
    }

    /**
     * The column <code>polls.id</code>.
     */
    public final TableField<PollsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>polls.name</code>.
     */
    public final TableField<PollsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>polls.create_time</code>.
     */
    public final TableField<PollsRecord, LocalDateTime> CREATE_TIME = createField(DSL.name("create_time"), SQLDataType.LOCALDATETIME(0).nullable(false), this, "");

    private Polls(Name alias, Table<PollsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Polls(Name alias, Table<PollsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>polls</code> table reference
     */
    public Polls(String alias) {
        this(DSL.name(alias), POLLS);
    }

    /**
     * Create an aliased <code>polls</code> table reference
     */
    public Polls(Name alias) {
        this(alias, POLLS);
    }

    /**
     * Create a <code>polls</code> table reference
     */
    public Polls() {
        this(DSL.name("polls"), null);
    }

    public <O extends Record> Polls(Table<O> path, ForeignKey<O, PollsRecord> childPath, InverseForeignKey<O, PollsRecord> parentPath) {
        super(path, childPath, parentPath, POLLS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class PollsPath extends Polls implements Path<PollsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> PollsPath(Table<O> path, ForeignKey<O, PollsRecord> childPath, InverseForeignKey<O, PollsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private PollsPath(Name alias, Table<PollsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public PollsPath as(String alias) {
            return new PollsPath(DSL.name(alias), this);
        }

        @Override
        public PollsPath as(Name alias) {
            return new PollsPath(alias, this);
        }

        @Override
        public PollsPath as(Table<?> alias) {
            return new PollsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.POLLS_POLLS_CREATE_TIME_IDX);
    }

    @Override
    public Identity<PollsRecord, Long> getIdentity() {
        return (Identity<PollsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PollsRecord> getPrimaryKey() {
        return Keys.KEY_POLLS_PRIMARY;
    }

    private transient PollVoteOptionsPath _pollVoteOptions;

    /**
     * Get the implicit to-many join path to the
     * <code>dizvoter.poll_vote_options</code> table
     */
    public PollVoteOptionsPath pollVoteOptions() {
        if (_pollVoteOptions == null)
            _pollVoteOptions = new PollVoteOptionsPath(this, null, Keys.OPTIONS_TO_POLL_FK.getInverseKey());

        return _pollVoteOptions;
    }

    @Override
    public Polls as(String alias) {
        return new Polls(DSL.name(alias), this);
    }

    @Override
    public Polls as(Name alias) {
        return new Polls(alias, this);
    }

    @Override
    public Polls as(Table<?> alias) {
        return new Polls(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Polls rename(String name) {
        return new Polls(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Polls rename(Name name) {
        return new Polls(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Polls rename(Table<?> name) {
        return new Polls(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Polls where(Condition condition) {
        return new Polls(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Polls where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Polls where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Polls where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Polls where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Polls where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Polls where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Polls where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Polls whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Polls whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}