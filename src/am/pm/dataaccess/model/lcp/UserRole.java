package am.pm.dataaccess.model.lcp;

/**
 * Created by Artur on 4/2/2016.
 */
public enum UserRole {

    USER    (1, "user"),
    ADMIN   (2, "admin");

    UserRole(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public static UserRole valueOf(final int id){
        for(UserRole role : UserRole.values()){
            if(role.getId() == id){
                return role;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    private int id;
    private String role;

}
