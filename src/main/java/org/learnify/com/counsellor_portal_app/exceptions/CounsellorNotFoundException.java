package org.learnify.com.counsellor_portal_app.exceptions;

public class CounsellorNotFoundException extends RuntimeException {
    public CounsellorNotFoundException(String message) {
        super(message);
    }
}
