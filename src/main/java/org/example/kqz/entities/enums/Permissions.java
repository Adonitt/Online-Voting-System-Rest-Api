package org.example.kqz.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"), // post put delete
    USER_READ("user:read"),
    USER_VOTE("user:vote");

    @Getter
    private final String permission;

}
