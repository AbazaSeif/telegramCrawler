package ir.adventure.observer.dto;

/**
 * Created by User on 4/4/2018.
 */
public class JoinResponse {
    private String message;
    private Boolean success;
    private int chatId;

    public JoinResponse(int chatId, String message, Boolean success) {
        this.message = message;
        this.success = success;
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
