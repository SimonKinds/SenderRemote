package io.kindstrom.senderremote.domain.model;

public class CommandSendingState {
    private final State state;
    private final Response response;

    public CommandSendingState(State state) {
        this(state, null);
    }

    public CommandSendingState(State state, Response response) {
        this.state = state;
        this.response = response;
    }

    public State getState() {
        return state;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "CommandSendingState{" +
                "state=" + state +
                ", response=" + response +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommandSendingState that = (CommandSendingState) o;

        return state == that.state && (response != null ? response.equals(that.response) : that.response == null);

    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (response != null ? response.hashCode() : 0);
        return result;
    }

    public enum State {
        SENT,
        DELIVERED,
        RESPONSE_RECEIVED
    }

}
