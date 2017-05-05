package actions;

import java.io.File;
import java.util.List;

import exceptions.FileInputException;
import exceptions.NotEnoughInputsException;
import exceptions.NumericInputException;
import gamedata.GameDataFactory;

public abstract class AbstractAction implements IAction{
	private GameDataFactory gdf;
	public AbstractAction(){
		gdf = new GameDataFactory();
	}
	protected GameDataFactory getGameDataFactory(){
		return gdf;
	}
	
	
	public double parseDouble(String doubleToParse) throws NumericInputException {
		if (doubleToParse == null || doubleToParse.equals("")) {
			throw new NumericInputException();
		}
		for (int i=0;i<doubleToParse.length();i++) {
			if (!Character.isDigit(doubleToParse.charAt(i))) {
				throw new NumericInputException();
			}
		}
		return Double.parseDouble(doubleToParse);
	}
	
	
	public String validateFile(String filePath) throws FileInputException{
		if (filePath==null || filePath.equals("") ){
			//Conditional removed because music would not play even if file did not cause errors when
			//a clip was made of it. 
			
			
//			|| !(new File(filePath).exists())) {
//		}

			throw new FileInputException(filePath);
		}
		return filePath;
	}
	
	public List<String> validateList(List<String> inputsToAction, int numNecessaryInputs) throws NotEnoughInputsException {
		if (inputsToAction.size() == numNecessaryInputs) {
			boolean allNotNull = true;
			for (String input : inputsToAction) {
				System.out.println(input);
				allNotNull = input!=null;
			}
			if (allNotNull == true) {
				return inputsToAction;
			}
		}
		throw new NotEnoughInputsException(numNecessaryInputs, inputsToAction.size());
	}	

}
