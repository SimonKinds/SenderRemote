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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Sender> getSenders() {
        return senders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id == group.id && (name != null ? name.equals(group.name) : group.name == null && (senders != null ? senders.equals(group.senders) : group.senders == null));

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (senders != null ? senders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
