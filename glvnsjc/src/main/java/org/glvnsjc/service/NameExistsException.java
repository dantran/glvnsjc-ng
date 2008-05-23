package org.glvnsjc.service;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * @author dtran
 */
public class NameExistsException extends Exception {
    private static final long serialVersionUID = 4050482305178810163L;

    /**
     * Constructor for UserExistsException.
     *
     * @param message exception message
     */
    public NameExistsException(final String message) {
        super(message);
    }
}
