package classesConcretes;


import interfaces.Pretraiteur;

import java.util.List;
import java.util.stream.Collectors;

import com.ibm.icu.text.Transliterator;

public class PretraiteurTranslitteration implements Pretraiteur {

    private final Transliterator transliterator;

    public PretraiteurTranslitteration() {
        this.transliterator = Transliterator.getInstance("Any-Latin; Latin-ASCII"); 
        // Convertit tout en alphabet latin et simplifie
    }

    @Override
    public List<String> traiter(List<String> strings) {
        return strings.stream()
            .map(transliterator::transliterate)
            .collect(Collectors.toList());
    }
}
