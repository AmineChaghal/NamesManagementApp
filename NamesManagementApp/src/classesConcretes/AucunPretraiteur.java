package classesConcretes;

import java.util.List;

import interfaces.Pretraiteur;

public class AucunPretraiteur implements Pretraiteur {
    @Override
    public List<String> traiter(List<String> input) {
        return input; // ne modifie rien
    }
}

