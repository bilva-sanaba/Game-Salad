package engines.infinite;

public enum InfiniteEnum {
	Horizontal(new ComplexInfiniteLoopAlgorithm(0,0)),
	Vertical(new ComplexInfiniteLoopAlgorithm(.5,.5)),
	None(new Finite());
	
	private IInfiniteAlgorithm myAlgorithm;
	
	InfiniteEnum(IInfiniteAlgorithm algorithm){
		myAlgorithm = algorithm;
	}
	public IInfiniteAlgorithm get(){
		return myAlgorithm;
	}
}