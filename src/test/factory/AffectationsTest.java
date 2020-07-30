package factory;

import data.Affectation;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;

public class AffectationsTest {

    @Test
    public void should_read_affectations() {
        ArrayList<Affectation> affectations = new Affectations().initialize();

        Affectation affectation = Affectation.newAffectation()
                .taskId("109")
                .compagnonId(0)
                .start(5671)
                .end(5784)
                .build();

        Assertions.assertThat(affectations.get(10)).isEqualToComparingFieldByField(affectation);
    }
}