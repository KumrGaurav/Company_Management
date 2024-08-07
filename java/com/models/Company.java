package com.models;
import java.util.Date;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "companyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "activeFlag")
    private Integer activeFlag;

    private int modifiedBy;

    @Column(name = "modifiedAt")
    private Date modifiedAt;
    
    private Set<User> users;

    private User id;
    
    public Company() {
        super();
    }

    public Company(Integer companyId, String companyName, Integer activeFlag) {
        super();
        this.companyId = companyId;
        this.companyName = companyName;
        this.activeFlag = activeFlag;
    }

    @Override
    public String toString() {
        return "Company [id=" + companyId + ", name=" + companyName + ", flag=" + activeFlag + "]";
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    public User getId() {
		return id;
	}

	public void setId(User id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setUserName(String firstName) {
		// TODO Auto-generated method stub
		
	}

  
}
