package models.requests;

import controllers.Controllers;
import models.NetworkData;
import models.responses.Response;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

@JsonTypeName("login")
public class LoginRequest implements Request {
    private String username;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response execute() {
        try {
            String s = NetworkData.objectMapper.writeValueAsString(this);
            NetworkData.printWriter.println(s);
            Response response = NetworkData.objectMapper.readValue(NetworkData.scanner.nextLine(), Response.class);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
