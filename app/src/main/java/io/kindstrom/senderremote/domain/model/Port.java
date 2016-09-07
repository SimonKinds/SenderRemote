package io.kindstrom.senderremote.domain.model;

public class Port {
    private final int id;
    private final int portNumber;
    private final String name;

    public Port(int id, int portNumber, String name) {
        this.id = id;
        this.portNumber = portNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getName() {
        return name;
    }
}
