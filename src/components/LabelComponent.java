package components;

/**
 * Component which is useful for grouping together objects with similar functionality
 * @author Bilva
 *
 */
public class LabelComponent implements IComponent {
	private String Label;
	public LabelComponent(String label){
		Label = label;
	}
	public LabelComponent(){}
	@Override
	public ComponentType getComponentType() {
		return ComponentType.Label;
	}
	public void setLabel(String newLabel){
		Label = newLabel;
	}
	public String getLabel(){
		return Label;
	}
}
