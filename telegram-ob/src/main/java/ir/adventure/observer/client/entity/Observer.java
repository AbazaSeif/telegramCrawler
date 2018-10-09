package ir.adventure.observer.client.entity;

import javax.persistence.*;

/**
 * Created by jalil on 10/25/2017.
 */
@Entity
@Table(name = "obs_observer")
public class Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private Integer apiId;
    private String name;
    private String code;
    private String apiHash;
    private Boolean enabled;
    private Boolean readyToLoad;

    public Boolean getReadyToLoad() {
        return readyToLoad;
    }

    public void setReadyToLoad(Boolean readyToLoad) {
        this.readyToLoad = readyToLoad;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Observer observer = (Observer) o;

        return id.equals(observer.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApiHash() {
        return apiHash;
    }

    public void setApiHash(String apiHash) {
        this.apiHash = apiHash;
    }
}
