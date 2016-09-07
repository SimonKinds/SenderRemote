package io.kindstrom.senderremote.domain.model;

import java.util.List;

public class Group {
    private final List<Sender> senders;

    public Group(List<Sender> senders) {
        this.senders = senders;
    }

    public List<Sender> getSenders() {
        return senders;
    }

    @Override
    public String toString() {
        return "Group{" +
                "senders=" + senders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return senders != null ? senders.equals(group.senders) : group.senders == null;

    }

    @Override
    public int hashCode() {
        return senders != null ? senders.hashCode() : 0;
    }
}
