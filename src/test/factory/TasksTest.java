package factory;

import data.Task;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TasksTest {

    @Test
    public void should_read_tasks_file() {

        Tasks tasks = new Tasks().initialize();

        Task task = Task.newTask()
                .processingTime(270)
                .numberOfCompagnons(1)
                .id("102")
                .regroupement("")
                .professionNeeded("Assemblage")
                .build();
        task.setAreaUsed(Arrays.asList("E27DB",
                "E28DB",
                "E29DB",
                "E30DB",
                "E31DB",
                "E32DB"));
        task.setSuccessors(Arrays.asList("139", "121", "119", "105"));

        assertThat(tasks.getTaskById("102")).isEqualToComparingFieldByField(task);
    }

}
