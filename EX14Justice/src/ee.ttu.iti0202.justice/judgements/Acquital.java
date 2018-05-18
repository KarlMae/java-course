package ee.ttu.iti0202.justice.judgements;

public class Acquital extends Judgement {

    @Override
    public boolean suspectGuilty() {
        return false;
    }
}
