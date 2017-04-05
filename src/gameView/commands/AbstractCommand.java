package gameView.commands;
import javafx.stage.Stage;
import gameView.UIView;

public abstract class AbstractCommand {
	
	private UIView view;
	
	public AbstractCommand(UIView m) {
		super();
		view = m;
	}

	protected UIView getView() {
		return view;
	}
	
	public abstract void execute(Stage s);	
	public abstract String getName();
}
 