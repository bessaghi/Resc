package factory;


import data.Compagnon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CompagnonsTest {

    @Test
    public void should_read_compagnons_file() {

        Compagnons compagnons = new Compagnons().initialize();

        Compagnon compagnon = Compagnon.newCompagnon()
                .id(6)
                .typeH(2)
                .profession("Assemblage")
                .equipe("EQUIPE STD1")
                .build();

        assertThat(compagnons.getCompagnonPositionById(6)).isEqualToComparingFieldByField(compagnon);
    }


}