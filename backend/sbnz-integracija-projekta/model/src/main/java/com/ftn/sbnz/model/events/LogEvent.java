package com.ftn.sbnz.model.events;

import com.ftn.sbnz.model.models.AppUserOld;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;
import java.util.Objects;

@Role(Role.Type.EVENT)
@Expires("10h")
public class LogEvent implements Serializable {

    private AppUserOld user;
    private Boolean good;

    public LogEvent() {
        super();
    }

    public LogEvent(AppUserOld user, Boolean good) {
        this.user = user;
        this.good = good;
    }

    public AppUserOld getUser() {
        return user;
    }

    public void setUser(AppUserOld user) {
        this.user = user;
    }

    public Boolean getGood() {
        return good;
    }

    public void setGood(Boolean good) {
        this.good = good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogEvent)) return false;
        LogEvent logEvent = (LogEvent) o;
        return Objects.equals(user, logEvent.user) && Objects.equals(good, logEvent.good);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, good);
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "user=" + user +
                ", valid=" + good +
                '}';
    }
}
