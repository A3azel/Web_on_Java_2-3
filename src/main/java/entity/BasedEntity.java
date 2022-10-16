package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class BasedEntity implements Serializable {
    private int ID;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public BasedEntity(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime;
    }
}
