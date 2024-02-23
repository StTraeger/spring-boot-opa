package de.doubleslash.openpolicyageent.business.exception;

public interface ErrorId {

    /**
     * Get the error code.
     */
    String getCode();

    /**
     * Get the error description.
     */
    String getDescription();

    /**
     * Get the error description and pass params to replacing the placeholder int the description.
     */
    String getDescription(final Object... params);
}
