package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Role;

import java.io.Serializable;
import java.util.Objects;

@Role(Role.Type.FACT)
public class AppUserOld implements Serializable {

    private String ip;
    private Boolean blocked;

    public AppUserOld() {
        super();
    }

    public AppUserOld(String ip, Boolean blocked) {
        this.ip = ip;
        this.blocked = blocked;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUserOld)) return false;
        AppUserOld appUserOld = (AppUserOld) o;
        return Objects.equals(ip, appUserOld.ip) && Objects.equals(blocked, appUserOld.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, blocked);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "ip='" + ip + '\'' +
                ", blocked=" + blocked +
                '}';
    }
}
