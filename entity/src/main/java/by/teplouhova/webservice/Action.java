package by.teplouhova.webservice;

import by.teplouhova.webservice.emuns.TypeActionEnum;

import javax.persistence.*;

/**
 * Class contains fields  describe  describing action, setters and getters, method doAction()
 */

@Entity
public class Action {

    @Id
    @Column(name = "actionID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actionId;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeActionEnum type;

    @OneToOne(mappedBy = "action", cascade = CascadeType.ALL)
    private Task task;

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }


    public TypeActionEnum getType() {
        return type;
    }

    public void setType(TypeActionEnum type) {
        this.type = type;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void doAction() throws InterruptedException {
    }
}
