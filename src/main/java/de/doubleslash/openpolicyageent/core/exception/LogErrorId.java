package de.doubleslash.openpolicyageent.core.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LogErrorId implements ErrorId {

    BEER_ALREADY_EXISTS("BEER_01", "Duplicate beer entries are not allowed. Entry already exists with id '%s'."),
    BEER_NOT_FOUND("BEER_02", "Beer with id '%s' not found."),

    BREWERY_DOES_NOT_MATCH("BEER_03", "Brewery of user does not match brewery of the beer to insert.");

    private final String code;

    private final String description;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getDescription(final Object... params) {
        return String.format(this.description, params);
    }
}
