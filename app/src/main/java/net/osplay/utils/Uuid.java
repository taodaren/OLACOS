package net.osplay.utils;

import java.util.UUID;

public class Uuid {

    public static String getUuid() {
        String uuid = UUID.randomUUID().toString();

        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
        //	return uuid.substring(0,8);
    }

    public static void main(String args[]) {
        System.out.println(getUuid());
    }

}

