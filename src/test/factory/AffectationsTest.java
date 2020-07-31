package factory;

import data.Affectation;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AffectationsTest {

    @Test
    public void should_read_affectations() {
        Affectations affectations = new Affectations().initialize();

        Affectation affectation = new Affectation()
                .setTaskId("109")
                .setCompagnonId(0)
                .setStart(5671)
                .setEnd(5784);

        Assertions.assertThat(affectations.getAffectationById("109").get()).isEqualToComparingFieldByField(affectation);
    }
}