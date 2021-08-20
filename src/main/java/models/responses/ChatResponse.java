package models.responses;

import models.trimmed.TrimmedChat;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("chat")
public class ChatResponse implements Response<TrimmedChat>{

    private TrimmedChat trimmedChat;

    public ChatResponse(TrimmedChat trimmedChat) {
        this.trimmedChat = trimmedChat;
    }

    public ChatResponse() {
    }

    @Override
    public TrimmedChat unleash() {
        return trimmedChat;
    }

    public TrimmedChat getTrimmedChat() {
        return trimmedChat;
    }

    public void setTrimmedChat(TrimmedChat trimmedChat) {
        this.trimmedChat = trimmedChat;
    }
}
