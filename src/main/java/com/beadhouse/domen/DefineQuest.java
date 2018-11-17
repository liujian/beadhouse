package com.beadhouse.domen;

import java.io.Serializable;
import java.util.Date;

public class DefineQuest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1;
    private Integer defineQuestId;
    private String defineQuest;
    private Integer createUserId;
    private Date createDate;

    public Integer getDefineQuestId() {
        return defineQuestId;
    }

    public void setDefineQuestId(Integer defineQuestId) {
        this.defineQuestId = defineQuestId;
    }

    public String getDefineQuest() {
        return defineQuest;
    }

    public void setDefineQuest(String defineQuest) {
        this.defineQuest = defineQuest;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
