package com.tutor.egate.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "user2test")
public class User2test implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5245883705251150065L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@AttributeOverrides(value =
			{@AttributeOverride(column = @Column(name="user_id"), name = "user_id"),@AttributeOverride(column = @Column(name="status"), name = "status"),
					@AttributeOverride(column = @Column(name="test_id"), name = "test_id")})
	@EmbeddedId
	private User2TestCompositeKey pk;
	@Column(nullable=false, insertable=false, updatable=false)
	private String user_id;
	@Column(nullable=false, insertable=false, updatable=false)
	private String status;
	@Column(nullable=false, insertable=false, updatable=false)
	private String test_id;

	private String test_time;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTest_id() {
		return test_id;
	}

	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTest_time() {
		return test_time;
	}

	public void setTest_time(String test_time) {
		this.test_time = test_time;
	}

	public User2TestCompositeKey getPk() {
		return pk;
	}

	public void setPk(User2TestCompositeKey pk) {
		this.pk = pk;
	}
}
