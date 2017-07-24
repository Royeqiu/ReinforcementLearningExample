package Reinforcement;

/**
 * Created by roye1 on 2017/7/24.
 */
public class Action {
    String actionName;

    public int getActionNum() {
        return actionNum;
    }

    int actionNum;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    Action(String actionName, int actionNum)
    {
        this.actionName=actionName;
        this.actionNum=actionNum;

    }
    public String toString()
    {
        return actionName+" "+actionNum;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass()))
        {
            Action actionObj=(Action)obj;
            if(actionObj.getActionName().equals(this.actionName)&&actionObj.getActionNum()==this.actionNum)
            {
                return true;
            }
        }
        return false;
    }
}
