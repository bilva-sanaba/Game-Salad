package actions;

import entity.Entity;

public abstract class UserDefinedAction extends AbstractAction  implements IAction {

	public abstract void executeAction(Entity e);

}
