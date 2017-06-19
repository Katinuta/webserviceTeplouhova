package by.teplouhova.webservice.emuns;

import by.teplouhova.webservice.Action;
import by.teplouhova.webservice.typeaction.impl.CompletedTypeAction;
import by.teplouhova.webservice.typeaction.impl.DelayedTypeAction;
import by.teplouhova.webservice.typeaction.impl.PrintTypeAction;
import by.teplouhova.webservice.typeaction.impl.RandomTypeAction;

/**
 * Enum types of action
 */

public enum TypeActionEnum {

    PRINT {
        {
            this.action = new PrintTypeAction();
        }
    },
    RANDOM {
        {
            this.action = new RandomTypeAction();
        }
    },
    COMPLETED {
        {
            this.action = new CompletedTypeAction();
        }
    },
    DELAYED {
        {
            this.action = new DelayedTypeAction();
        }
    };

    Action action;

    TypeActionEnum() {
    }

    ;

    public Action getAction() {
        return action;
    }
}
