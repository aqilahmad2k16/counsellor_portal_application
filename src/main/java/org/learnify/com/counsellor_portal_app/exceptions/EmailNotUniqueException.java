package org.learnify.com.counsellor_portal_app.exceptions;

public class EmailNotUniqueException extends RuntimeException {
    public EmailNotUniqueException(String message) {
        super(message);
    }
}
