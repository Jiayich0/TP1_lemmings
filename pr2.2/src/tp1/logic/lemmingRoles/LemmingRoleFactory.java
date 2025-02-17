package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class LemmingRoleFactory {
		
	private static final List<LemmingRole> availableRoles 							// Lista de Roles Disponibles
		= Arrays.asList(
			new DownCaverRole(),
			new ParachuterRole(),
			new WalkerRole()
	);
	
	public static LemmingRole parse(String roleWords) {								// Parse(): Recorre la Lista de Roles
		for (LemmingRole role: availableRoles) {									// Si coincide nombre: devuelve Clase
			LemmingRole parsedRole = role.parse(roleWords);							// Si no: devuelve Null
			if (parsedRole != null) {
				return parsedRole;
            }
		}
		return null;
	}
	
	public static String roleHelp() {												// Muestra 'help' de todos los Roles
		StringBuilder roles = new StringBuilder();
		
		for (LemmingRole r: availableRoles) {
			roles.append(Messages.TAB + Messages.LINE_TAB.formatted(r.getHelp()));
		}
		return roles.toString();
	}
}
	