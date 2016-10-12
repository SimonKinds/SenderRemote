package io.kindstrom.senderremote.domain.model;

public enum CommandSendingState {
        SENT,
        DELIVERED,
        ERROR_NO_SERVICE,
        ERROR_EMPTY_MESSAGE,
        ERROR_UNKNOWN,
        ERROR_RADIO_OFF
}
