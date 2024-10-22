/*
 * This file is generated by jOOQ.
 */
package io.github.dominys.dizvoter.data.access.impl.jooq.tables.records;


import io.github.dominys.dizvoter.data.access.impl.jooq.tables.Polls;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PollsRecord extends UpdatableRecordImpl<PollsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>polls.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>polls.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>polls.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>polls.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>polls.create_time</code>.
     */
    public void setCreateTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>polls.create_time</code>.
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PollsRecord
     */
    public PollsRecord() {
        super(Polls.POLLS);
    }

    /**
     * Create a detached, initialised PollsRecord
     */
    public PollsRecord(Long id, String name, LocalDateTime createTime) {
        super(Polls.POLLS);

        setId(id);
        setName(name);
        setCreateTime(createTime);
        resetChangedOnNotNull();
    }
}