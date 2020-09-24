package com.project.demo.security.payload.request;

import java.util.Set;

public class SignupRequest {

    private String username;

    private String email;

    private Set<String> authorities;

    private String password;

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.authorities;
    }

    public void setRole(Set<String> authorities) {
        this.authorities = authorities;
    }
}
