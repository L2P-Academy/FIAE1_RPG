package test.java;

import controller.CharacterController;
import controller.CombatController;
import controller.SoundController;
import model.NpcModel;
import model.NpcModel.NpcCategory;
import model.PlayerCharacterModel;
import view.CombatView;

public class CombatControllerTest {

	private CombatController combatController;
	private PlayerCharacterModel character;
	private SoundController soundController;
	private CombatView combatView;
	
//	@BeforeAll
	public void setUpCombatTest() {
		character = new PlayerCharacterModel(99, "HansTest", 1, 1, "m", 1, 10, 100, 1, 100, 100, 100, 100);
		soundController = new SoundController();
		combatView = new CombatView(soundController, new CharacterController(character, soundController));
		combatController = new CombatController(character, soundController, combatView);
	}
	
//	@Test
	public void testPlayerAttack() {
		NpcModel npc = new NpcModel(99, 99, 100, 5, 10, 1, 55, 1, NpcCategory.MOB, "Übungspuppe");
		combatController.selectNpc(npc);
		combatController.playerAttack(npc);
//		assertEquals(80, npc.getHp());
	}
}
