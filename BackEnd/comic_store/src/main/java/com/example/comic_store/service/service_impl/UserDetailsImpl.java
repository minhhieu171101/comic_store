package com.example.comic_store.service.service_impl;

import com.example.comic_store.entity.Role;
import com.example.comic_store.entity.User;
import com.example.comic_store.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String gmail;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user, List<UserRole> userRoles, List<Role> roles) {
        List<GrantedAuthority> authorities = userRoles.stream()
                .filter(userRole -> userRole.getUserId().equals(user.getId()))
                .map(userRole -> roles.stream()
                        .filter(role -> role.getId().equals(userRole.getRoleId()))
                        .findFirst()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getGmail(),
                user.getPassword(),
                authorities);
    }

}
