package classesConcretes;

import interfaces.Pretraiteur;
import org.apache.commons.codec.language.Soundex;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PretraiteurSoundex implements Pretraiteur {

	private final Soundex soundex = new Soundex();

	@Override
	public List<String> traiter(List<String> partiesDuNom) {
		if (partiesDuNom == null) {
			return new ArrayList<>(); // Ou lever une exception
		}
		return partiesDuNom.stream().map(partie -> {
			if (partie == null || partie.trim().isEmpty()) {
				return ""; // Soundex ne gère pas bien les chaînes vides
			}
			try {
				// L'implémentation Soundex d'Apache Commons Codec
				// peut retourner une chaîne comme "R163" ou "0000" si non encodable.
				return soundex.encode(partie);
			} catch (IllegalArgumentException e) {
				// Gérer le cas où une chaîne ne peut pas être encodée (par exemple, contient
				// des chiffres pour certaines implémentations)
				// Pour l'instant, retourner une chaîne vide ou un code d'erreur spécifique.
				// L'implémentation standard de Soundex d'Apache Commons Codec est assez
				// robuste.
				System.err.println("Erreur d'encodage Soundex pour: " + partie + " - " + e.getMessage());
				return ""; // Ou un code d'erreur comme "Z000"
			}
		}).collect(Collectors.toList());
	}
}
