package gameView.commands;

import gameView.UIView;

public abstract class AbstractCommand {
	
	private UIView view;
	
	public AbstractCommand(UIView m) {
		view = m;
	}

	protected UIView getView() {
		return view;
	}
	
	protected abstract void execute();
}
