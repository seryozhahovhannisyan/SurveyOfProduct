package am.pm.dataaccess.model;

/**
 * Created by Artur on 4/2/2016.
 */
public class ProductType {

    /*OUTSIDE         (1, "Outside"),
    SHOPS           (2, "Shops"),
    TRANSPORT       (3, "Transport");*/

    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
