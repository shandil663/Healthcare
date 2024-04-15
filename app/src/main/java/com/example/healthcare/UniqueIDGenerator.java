package com.example.healthcare;

import java.util.UUID;

public class UniqueIDGenerator {
    public static String generateUniqueID() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
}
