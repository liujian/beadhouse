package com.beadhouse.in;

public class SendMessageParam extends BasicIn {

    private String token;

    private int elderUserId;

    private int questId;

    private String defineQuest;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getElderUserId() {
        return elderUserId;
    }

    public void setElderUserId(int elderUserId) {
        this.elderUserId = elderUserId;
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public String getDefineQuest() {
        return defineQuest;
    }

    public void setDefineQuest(String defineQuest) {
        this.defineQuest = defineQuest;
    }
}
