package com.beadhouse.in;

public class AnswerQuestParam extends BasicIn {
    private String token;
    private Integer chatId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
}
