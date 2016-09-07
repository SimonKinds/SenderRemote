package io.kindstrom.senderremote.domain.model;

import java.util.List;

public class Group {
    private final int id;
    private final String name;
    private final List<Sender> senders;

    public Group(int id, String name, List<Sender> senders) {
        this.id = id;
        this.name = name;
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
