package classesConcretes;

import java.util.Random;

import classesPorteusesDeDonnees.Nom;
import interfaces.ComparateurDeNoms;

public class ComparateurDeNomsRandom implements ComparateurDeNoms {
    private Random random = new Random();

    @Override
    public float comparer(Nom nom1, Nom nom2) {
        return random.nextFloat(); // valeur entre 0 et 1
    }
}