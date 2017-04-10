package components;

public class ImagePropertiesComponent implements IComponent{
	private double imageHeight;
	private double imageWidth;
	public ImagePropertiesComponent(double h, double w) {
		imageHeight = h;
		imageWidth = w;
	}
	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return ComponentType.ImageProperties;
	}
	public void setHeight(double h){
		imageHeight=h;
	}
	public double getHeight(){
		return imageHeight;
	}
	public void setWidth(double w){
		imageWidth=w;
	}
	public double getWidth(){
		return imageWidth;
	}
	@Override
	public IComponent newCopy() {
		return new ImagePropertiesComponent(getHeight(), getWidth());
	}

}
