package by.teplouhova.webservice;

import by.teplouhova.webservice.emuns.StatusEnum;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Class contains fields  describe  describing Execution, setters and getters
 */

@Entity
public class Execution {

    @Id
    @Column(name = "executionID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long executionId;

    @Column(name = "startTime")
    private Timestamp startTime;

    @Column(name = "endTime")
    private Timestamp endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pipelineId")
    private Pipeline pipeline;

    public long getExecutionId() {
        return executionId;
    }

    public void setExecutionId(long executionId) {
        this.executionId = executionId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
