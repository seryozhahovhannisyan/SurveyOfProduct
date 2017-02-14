package am.pm.dataaccess.model.lcp;

/**
 * Created by Artur on 4/2/2016.
 */
public enum Priority {

    STUDENT         (1, "Student"),
    TOURIST         (2, "Tourist"),
    BUSINESSMEN     (3, "Businessman");

    Priority(int id, String priority) {
        this.id = id;
        this.priority = priority;
    }

    public static Priority valueOf(final int id){
        for(Priority priority : Priority.values()){
            if(priority.getId() == id){
                return priority;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    private int id;
    private String priority;


}
