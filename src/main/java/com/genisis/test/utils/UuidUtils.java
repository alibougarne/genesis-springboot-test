package com.genisis.test.utils;

import java.util.UUID;

public class UuidUtils {
    /**
     * check uuid validity.
     *
     * @param id uuid string input
     * @return UUID
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public static UUID checkUUID(String id) {
        if (id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            return UUID.fromString(id);
        } else {
            id = id.replaceAll("(.{8})(.{4})(.{4})(.{4})(.+)", "$1-$2-$3-$4-$5");
            UUID uuid = UUID.fromString(id);
            return uuid;
        }
    }
}
