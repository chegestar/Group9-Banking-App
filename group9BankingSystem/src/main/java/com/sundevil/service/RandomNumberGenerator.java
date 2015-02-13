/**
 * 
 */
package com.sundevil.service;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author satyaswaroop
 *
 */
public class RandomNumberGenerator {
	public String generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyz"
                     + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "0123456789!@%$%&^?|~'\"#+="
                     + "\\*/.,:;[]()-_<>";

        final int PW_LENGTH = 10;
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < PW_LENGTH; i++)
            pass.append(chars.charAt(rnd.nextInt(chars.length())));
        return pass.toString();
    }

}
