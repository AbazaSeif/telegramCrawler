package ir.adventure.observer.client.entity;

import javax.persistence.*;

/**
 * Created by jalil on 11/8/2017.
 */

@Entity
@Table(name = "obs_accessToken")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(String accessHash) {
        this.accessHash = accessHash;
    }
}
