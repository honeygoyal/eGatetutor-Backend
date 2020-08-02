package com.tutor.egate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class User2TestCompositeKey implements Serializable {
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "status")
    private String status;
    @Column(name = "test_id")
    private String test_id;


    public User2TestCompositeKey() {
    }

    public User2TestCompositeKey(String user_id, String status, String test_id) {
        this.user_id = user_id;
        this.status = status;
        this.test_id = test_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User2TestCompositeKey)) return false;
        User2TestCompositeKey that = (User2TestCompositeKey) o;
        return getUser_id().equals(that.getUser_id()) &&
                getStatus().equals(that.getStatus()) &&
                getTest_id().equals(that.getTest_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getStatus(), getTest_id());
    }
}