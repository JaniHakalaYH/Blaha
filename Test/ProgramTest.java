import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    Program p = new Program();

    @org.junit.jupiter.api.Test
    public void getNameFromFile() {
        String input = "7512166544, Greger Ganache";
        assert(p.getNameFromFile(input).equalsIgnoreCase("Greger Ganache"));
    }
}