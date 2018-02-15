package ee.ttu.iti0202.lotr;

public class Person {

    private String race, name;
    private Ring ring;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String isSauron() {

        if (this.ring == null && this.name.equals("Sauron")) {
            return "No, but he's claiming to be";
        } else if (this.ring == null) {
            return "No";
        }

        if (this.name.equals("Sauron") && this.ring.getType().equals("The one") &&
                this.ring.getMaterial().equals("gold")) {
            return "Affirmative";
        } else if (this.name.equals("Sauron") && this.ring.getType().equals("The one") &&
                !this.ring.getMaterial().equals("gold")) {
            return "No, the ring is fake!";
        } else if (this.ring.getType().equals("The one") && this.ring.getMaterial().equals("gold")) {
            return "No, he just stole the ring";
        } else if (this.name.equals("Sauron")) {
            return "No, but he's claiming to be";
        } else {
            return "No";
        }
    }

    public String getRace() {
        return race;
    }


    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }
}
