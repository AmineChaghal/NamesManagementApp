package classesConcretes;

import interfaces.ComparateurDeChaines;

public class ComparateurEquals implements ComparateurDeChaines {
    @Override
    public double comparer(String s1, String s2) {
        return s1.equals(s2) ? 1.0f : 0.0f;
    }
}
