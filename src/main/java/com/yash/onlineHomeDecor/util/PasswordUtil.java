/**
 * @author Darshan soni
 * @version 1.0
 */
package com.yash.onlineHomeDecor.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // Hash a password using BCrypt
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verify a password against a hash
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
