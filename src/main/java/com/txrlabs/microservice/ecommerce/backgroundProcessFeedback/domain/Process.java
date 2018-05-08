package com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.txrlabs.microservice.ecommerce.backgroundProcessFeedback.enumeration.ProcessStatus;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String client;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String app;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ProcessStatus status;

    @Column(nullable=false, name="current_step")
    private Integer currentStep;

    @Column(nullable=false, name="total_steps")
    private Integer totalSteps;

    @Column(nullable=false, name="date_created")
    private Date dateCreated;

    @Column(nullable=false, name="last_updated")
    private Date lastUpdated;

    @CreatedBy
    @Column(name="created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name="updated_by")
    private String lastModifiedBy;

    @PrePersist
    protected void onCreate() {
        this.client = SecurityContextHolder.getContext().getAuthentication().getName();
        this.dateCreated = new Date();
        this.lastUpdated = new Date();
    }

    @PreUpdate
    public void updateLastUpdate(){
        this.lastUpdated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public Integer getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(Integer totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(this);
            return jsonInString;
        } catch (JsonProcessingException jpe) {
            return "{}";
        }
    }
}
