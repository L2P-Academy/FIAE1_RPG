package model;

import java.io.Serializable;

public class SerializationIDs implements Serializable{
	
	// Serialization for all views being implemented here:
	
	private static final long serialVersionUID = 1L;
	public static final long mainMenuViewID = 2L;
	public static final long introViewID = 3L;
	public static final long characterCreationViewID = 4L;
	public static final long characterViewID = 5L;
	public static final long inventoryViewID = 6L;
	public static final long questViewID = 7L;
	public static final long backGroundPanelID = 8L;
	public static final long combatViewID = 9L;
	public static final long mapViewID = 10L;
	public static final long saveGameViewID = 11L;
	public static final long settingsViewID = 12L;
	public static final long journalViewID = 13L;
	
	private SerializationIDs() {
		// empty private constructor to deny Instance creation
	}
	

}
