package Question_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PropertyTest {

    @Test
    public void testCalculateImpact() {
	Property flat = new Flat(1, "Percy Road", "Guildford", "GU2 7XH", 2, 1);
	flat.addComplaint(new Complaint("Wallpaper is coming off", Severity.LOW));
	assertEquals(1, flat.calculateImpact(), 0);
    }

    @Test
    public void testComplaint() {
	Property flat = new Flat(1, "Percy Road", "Guildford", "GU2 7XH", 2, 1);
	flat.addComplaint(new Complaint("Communal Gardens need clearing", Severity.MEDIUM));
	assertEquals(2, flat.calculateImpact(), 0);
    }

    @Test
    public void testHighSeverity() {
	Property flat = new Flat(1, "Percy Road", "Guildford", "GU2 7XH", 2, 1);
	flat.addComplaint(new Complaint("Broken front door", Severity.HIGH));
	assertEquals(3, flat.calculateImpact(), 0);
    }

}
