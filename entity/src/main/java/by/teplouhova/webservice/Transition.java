package by.teplouhova.webservice;

import javax.persistence.*;

/**
 * Class contains fields  describe  describing Transition, setters and getters
 */

@Entity
public class Transition {

    @Id
    @Column(name = "transitionId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transitionId;

    @Column(name = "begin")
    private String begin;

    @Column(name = "end")
    private String end;

    @ManyToOne(optional = false)
    @JoinColumn(name="pipelineId")
    private Pipeline pipeline;

    public long getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(long transitionId) {
        this.transitionId = transitionId;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Transition(String begin, String end, Pipeline pipeline) {
        this.begin = begin;
        this.end = end;
        this.pipeline = pipeline;
    }
}
