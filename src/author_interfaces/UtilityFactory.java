package author_interfaces;

import javafx.scene.control.*;

public interface UtilityFactory {
	public Slider makeSlider();
	public Button makeButton();
	public Label makeLabel();
	public TextField makeTextField();
	public Tab makeTab();
	public MenuButton makeMenuButton();
	public ScrollBar makeScrollBar();
}
