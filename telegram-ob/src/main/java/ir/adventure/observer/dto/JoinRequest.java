package ir.adventure.observer.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by User on 4/4/2018.
 */
public class JoinRequest {
    @NotNull
    private String username;
    @NotNull
    private Boolean pub;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPub() {
        return pub;
    }

    public void setPub(Boolean aPublic) {
        pub = aPublic;
    }
}
