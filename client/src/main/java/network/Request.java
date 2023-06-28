package network;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String type;
    private String command;
    private String token;
    private final Map<String, String> parameters;

    public Request(){
        parameters =new HashMap<>();
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void addParameter(String key, String value) {
        parameters.put(key,value);
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
