package io.kindstrom.senderremote.domain.model;

public class Response {
    private final String response;

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response1 = (Response) o;

        return response != null ? response.equals(response1.response) : response1.response == null;

    }

    @Override
    public int hashCode() {
        return response != null ? response.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Response{" +
                "response='" + response + '\'' +
                '}';
    }
}
