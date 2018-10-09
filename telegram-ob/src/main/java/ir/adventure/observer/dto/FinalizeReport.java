package ir.adventure.observer.dto;

/**
 * Created by jalil on 12/23/2017.
 */
public class FinalizeReport {

    private Long messages;
    private Long views;
    private Long deleted;
    private Boolean isSuccess;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public FinalizeReport(Long messages, Long views, Long deleted, Boolean isSuccess) {
        this.messages = messages;
        this.views = views;
        this.deleted = deleted;
        this.isSuccess = isSuccess;
    }

    public Long getMessages() {
        return messages;
    }

    public void setMessages(Long messages) {
        this.messages = messages;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getDeleted() {
        return deleted;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }
}
