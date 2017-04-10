package gameView;

public interface ICommandUIView extends ICommandView {

	public void authorGame();

	public void loadGame(String filepath);
	
	public void restart();

}
