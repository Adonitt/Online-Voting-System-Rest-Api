package org.example.kqz.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.example.kqz.entities.enums.Permissions.*;

@RequiredArgsConstructor
public enum RoleEnum {
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE)),
    USER(Set.of(USER_READ, USER_VOTE));

    @Getter
    private final Set<Permissions> permissions;

    // per me i tregu spring securityu cilat permissions ( authority ) e ka qyky rol

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authority = new ArrayList<>(
                getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(
                                permission.getPermission()))
                        .toList());
        authority.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authority;
    }

}
