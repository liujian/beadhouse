package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class RecordQuest implements Serializable {
    int loginUserId;
    int elderUserId;
    int questId;

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
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
}