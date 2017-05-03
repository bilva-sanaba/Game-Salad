package engines.infinite;

public enum InfiniteEnum {
	Horizontal(new InfiniteHorizontal()),
	Vertical(new InfiniteVertical()),
	None(new Finite());
	
	private IInfiniteAlgorithm myAlgorithm;
	InfiniteEnum(IInfiniteAlgorithm algorithm){
		myAlgorithm = algorithm;
	}
	public IInfiniteAlgorithm get(){
		return myAlgorithm;
	}
}
