import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {
    private BodyMassIndex bmi;

    @BeforeEach
    public void init() {
        bmi = new BodyMassIndex(64, 112);
    }
    @Test
    public void testBmiScore() {
        double answer = bmi.score();
        assertEquals(19.2, answer);
    }

    @Test
    public void testBmiCategoryUnder() {
        String answer = bmi.category(18);
        assertEquals("Under Weight", answer);
    }
    @Test
    public void testBmiCategoryNormal() {
        String answer = bmi.category(20);
        assertEquals("Normal Weight", answer);
    }
    @Test
    public void testBmiCategoryOver() {
        String answer = bmi.category(27);
        assertEquals("Overweight", answer);
    }
    @Test
    public void testBmiCategoryObese() {
        String answer = bmi.category(30);
        assertEquals("Obese", answer);
    }

}