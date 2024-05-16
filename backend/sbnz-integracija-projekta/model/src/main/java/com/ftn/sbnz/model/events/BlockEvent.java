package com.ftn.sbnz.model.events;

import com.ftn.sbnz.model.models.AppUser;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.util.Objects;

@Role(Role.Type.EVENT)
@Expires("15m")
public class BlockEvent {

    private AppUser user;

    public BlockEvent() {
    }

    public BlockEvent(AppUser user) {
        this.user = user;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockEvent)) return false;
        BlockEvent that = (BlockEvent) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return "BlockEvent{" +
                "user=" + user +
                '}';
    }
}
