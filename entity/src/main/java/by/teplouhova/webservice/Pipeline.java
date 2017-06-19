package by.teplouhova.webservice;

import javax.persistence.*;
import java.util.List;

/**
 * Class contains fields  describe  describing Pipeline, setters and getters
 */

@Entity
public class Pipeline {
    @Id
    @Column(name = "pipelineID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pipelineId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pipeline")
    private List<Execution> executionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pipeline", fetch = FetchType.EAGER)
    private List<Transition> transitions;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pipeline")
    private List<Task> tasks;

    public long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Execution> getExecutionsList() {
        return executionsList;
    }

    public void setExecutionsList(List<Execution> executionsList) {
        this.executionsList = executionsList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
