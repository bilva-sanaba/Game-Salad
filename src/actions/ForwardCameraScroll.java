package actions;

import java.util.List;

import entity.IEntity;
import entity.IEntityManager;

public class ForwardCameraScroll implements IAction{

	@Override
	public List<IEntity> executeAction(IEntity player, IEntity npc, IEntityManager myEM) {
		System.out.println("hi");
		return null;
	}

}
