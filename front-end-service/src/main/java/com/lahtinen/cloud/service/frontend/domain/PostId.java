package com.lahtinen.cloud.service.frontend.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class PostId {

    private final UUID id;

    private PostId(UUID id) {
        this.id = id;
    }

    public static PostId create() {
        return new PostId(UUID.randomUUID());
    }

    public static PostId parse(String id) {
        return new PostId(UUID.fromString(id));
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
