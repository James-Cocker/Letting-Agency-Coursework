package Question_1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TenantTest {

	@Test
	public void testTenant() {
		Tenant t = new Tenant("James", "Cocker", 18, TenantType.STUDENT);
	}
	
	@Test
	public void testTenantNullPointerException() {
		try {
			Tenant t = new Tenant(null, "Cocker", 18, TenantType.STUDENT);
	        fail("Expected exception was not thrown");
	    } catch (Exception e) {
	        assertNotNull(e);
	    }
		
		try {
			Tenant t = new Tenant(null, "Cocker", 18, null);
	        fail("Expected exception was not thrown");
	    } catch (Exception e) {
	        assertNotNull(e);
	    }
	}

	@Test
	public void testGetAge() {
		Tenant t = new Tenant("James", "Cocker", 18, TenantType.STUDENT);
		assertEquals(18, t.getAge());
	}

	@Test
	public void testGetType() {
		Tenant t = new Tenant("James", "Cocker", 18, TenantType.STUDENT);
		assertEquals(TenantType.STUDENT, t.getType());
	}

	@Test
	public void testGetName() {
		Tenant t = new Tenant("James", "Cocker", 18, TenantType.STUDENT);
		assertEquals("James Cocker", t.getName());
	}
}
