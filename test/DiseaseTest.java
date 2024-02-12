import org.junit.Before;
import org.junit.Test;

import pandemics.Disease;

import static org.junit.Assert.*;



/** DiseaseTest Class */
public class DiseaseTest {
	
	private Disease disease1;
	
	@Before
	public void before() {
		this.disease1 = new Disease("Covid");
	}
	
	@Test
	public void initializationIsOk() {
		assertEquals(disease1.getDiseaseName(), "Covid");
		assertFalse(disease1.isRemedy());
		assertFalse(disease1.isEradication());
	}
	
	@Test
	public void Getter_and_Setter_DiseaseName() {
		assertEquals(disease1.getDiseaseName(), "Covid");
		disease1.setDiseaseName("Covid-19");
		assertEquals(disease1.getDiseaseName(), "Covid-19");
	}
	
	@Test
	public void Getter_and_Setter_Remedy() {
		assertFalse(disease1.isRemedy());
		disease1.setRemedy(true);
		assertTrue(disease1.isRemedy());
	}
	
	@Test
	public void Getter_and_Setter_Eradication() {
		assertFalse(disease1.isEradication());
		disease1.setEradication(true);
		assertTrue(disease1.isEradication());
	}
	
	
}