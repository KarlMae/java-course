package ee.ttu.iti0202.lotr;

public class Ring {

    private String type;
    private String material;

    public Ring(String type, String material) {
        this.type = type;
        this.material = material;
    }

    public String getMaterial() {
        return this.material;
    }

    public String getType() {
        return this.type;
    }

}
