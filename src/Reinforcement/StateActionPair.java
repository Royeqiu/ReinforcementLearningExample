package Reinforcement;

/**
 * Created by roye1 on 2017/7/24.
 */
public class StateActionPair {
    Action action;
    State state;
    StateActionPair()
    {
        state=null;
        action=null;
    }
    StateActionPair(State state,Action action)
    {
        this.state =state;
        this.action=action;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public String toString()
    {
        return state.toString()+" "+action.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass()))
        {
            StateActionPair stateActionPairObject=(StateActionPair)obj;
            if(stateActionPairObject.getState().equals(this.state)&&stateActionPairObject.getAction().equals(this.action))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (action.toString()+state.toString()).hashCode();
    }
}
