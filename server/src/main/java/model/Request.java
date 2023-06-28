package model;

import io.jsonwebtoken.Jws;

import java.util.Map;

public class Request {
    private String type;
    private String command;
    private Jws token;
    private Map<String, String> parameters;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Jws getToken() {
        return token;
    }

    public void setToken(Jws token) {
        this.token = token;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
