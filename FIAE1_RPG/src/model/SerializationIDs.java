package model;

import java.io.Serializable;

public class SerializationIDs implements Serializable{
	
	// Serialization for all views being implemented here:
	
	private static final long serialVersionUID = 1L;
	private static final long mainMenuViewID = 2L;
	private static final long introViewID = 3L;
	private static final long characterCreationViewID = 4L;
	private static final long characterViewID = 5L;
	private static final long inventoryViewID = 6L;
	private static final long QuestViewID = 7L;
	
	private SerializationIDs() {
		// empty private constructor to deny Instance creation
	}
	

}
