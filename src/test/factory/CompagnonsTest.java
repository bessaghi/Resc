package factory;


import data.Compagnon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompagnonsTest {

    @Test
    public void should_read_compagnons_file() {

        Compagnons compagnons = new Compagnons().initialize();

        Compagnon compagnon = new Compagnon()
                .setId(6)
                .setTypeH(2)
                .setProfession("Assemblage")
                .setEquipe("EQUIPE STD1");

        assertThat(compagnons.getCompagnonPositionById(6)).isEqualToComparingFieldByField(compagnon);
    }


}