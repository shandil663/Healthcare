package healers.data.solutions;

import java.util.UUID;

public class UniqueIDGenerator {
    public static String generateUniqueID() {
        UUID uniqueID = UUID.randomUUID();
        return uniqueID.toString();
    }
}
