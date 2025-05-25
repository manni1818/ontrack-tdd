import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestSubmissionServiceTest {

    private TestSubmissionService service;

    @Before
    public void setUp() {
        service = new TestSubmissionService();
    }

    @Test
public void testSubmitTaskWithValidInput() {
    boolean result = service.submitTask("student123", "task456");
    assertTrue("Submission should succeed for valid inputs", result);
}


    @Test
    public void testSubmitTaskWithEmptyInput() {
        boolean result = service.submitTask("", "");
        assertFalse("Submission should fail for empty input", result);
    }

    @Test
    public void testSubmitTaskWithNullInput() {
        boolean result = service.submitTask(null, null);
        assertFalse("Submission should fail for null input", result);
    }

    @Test
    public void testDuplicateTaskSubmissionFails() {
        service.submitTask("student1", "taskA");
        boolean result = service.submitTask("student1", "taskA");
        assertFalse("Duplicate submission should fail", result);
    }

    @Test
    public void testRetrieveSubmittedTasks() {
        service.submitTask("studentX", "task1");
        service.submitTask("studentX", "task2");

        List<String> submitted = service.getSubmittedTasks("studentX");

        assertEquals(2, submitted.size());
        assertTrue(submitted.contains("task1"));
        assertTrue(submitted.contains("task2"));
    }

    @Test
    public void testRetrieveSubmittedTasksForUnknownStudent() {
        List<String> submitted = service.getSubmittedTasks("unknown");
        assertTrue("Should return empty list for unknown student", submitted.isEmpty());
    }
}
