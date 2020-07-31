package factory;

import data.Task;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TasksTest {

    @Test
    public void should_read_tasks_file() {

        Tasks tasks = new Tasks().initialize();

        Task task = new Task()
                .setProcessingTime(270)
                .setNumberOfCompagnons(1)
                .setId("102")
                .setRegroupement("")
                .setProfessionNeeded("Assemblage")
                .setAreaUsed(Arrays.asList("E27DB",
                        "E28DB",
                        "E29DB",
                        "E30DB",
                        "E31DB",
                        "E32DB"))
                .setSuccessors(Arrays.asList("139", "121", "119", "105"));

        assertThat(tasks.getTaskById("102").get()).isEqualToComparingFieldByField(task);
    }

    @Test
    public void should_retrieve_successors() {

        Tasks tasks = new Tasks().initialize();;

        assertThat(tasks.allTasksFollowing("102").toString()).isEqualTo("");

    }
}
