package com.ftn.sbnz.model.models;

import org.kie.api.definition.type.Role;

import java.util.Objects;

@Role(Role.Type.FACT)
public class Alarm {

    private AppUserOld user;
    private Boolean checked;

    public Alarm() {
    }

    public Alarm(AppUserOld user, Boolean checked) {
        this.user = user;
        this.checked = checked;
    }

    public AppUserOld getUser() {
        return user;
    }

    public void setUser(AppUserOld user) {
        this.user = user;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alarm)) return false;
        Alarm alarm = (Alarm) o;
        return Objects.equals(user, alarm.user) && Objects.equals(checked, alarm.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, checked);
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "user=" + user +
                ", checked=" + checked +
                '}';
    }
}
