package com.bookstore.exceptions;

import java.io.Serializable;

/**
 * @author mohammadnaushad
 *
 */
public class ThirdPartyApiException extends RuntimeException implements Serializable{
	
	private static final long serialVersionUID = -3033864417612910879L;
	private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ThirdPartyApiException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s Issue %s '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
    
}