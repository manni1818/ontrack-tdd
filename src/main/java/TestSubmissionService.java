
import java.util.*;

public class TestSubmissionService {

    private final Map<String, Set<String>> studentTaskMap;

    public TestSubmissionService() {
        this.studentTaskMap = new HashMap<>();
    }

    // Submit a task for a student
    public boolean submitTask(String studentId, String taskId) {
        if (studentId == null || taskId == null || studentId.isEmpty() || taskId.isEmpty()) {
            return false;
        }

        // Check for duplicate submission
        Set<String> tasks = studentTaskMap.getOrDefault(studentId, new HashSet<>());
        if (tasks.contains(taskId)) {
            return false; // Duplicate
        }

        tasks.add(taskId);
        studentTaskMap.put(studentId, tasks);
        return true;
    }

    // Retrieve all submitted tasks for a student
    public List<String> getSubmittedTasks(String studentId) {
        if (studentId == null || !studentTaskMap.containsKey(studentId)) {
            return Collections.emptyList();
        }

        return new ArrayList<>(studentTaskMap.get(studentId));
    }
}
