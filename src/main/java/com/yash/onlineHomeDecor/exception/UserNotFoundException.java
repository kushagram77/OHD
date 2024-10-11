/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.exception;

import com.yash.onlineHomeDecor.domain.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(){}
}
