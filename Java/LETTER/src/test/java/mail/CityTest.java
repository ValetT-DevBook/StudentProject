package mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import mail.contents.*;
import mail.exceptions.NotEnoughMoneyException;
import mail.letters.*;

/**
 * Test for the city class
 */
public class CityTest {

	// Mock objects

	protected class MockContent implements Content {
	}

	protected class MockLetter extends Letter<MockContent> {

		public MockLetter(Inhabitant sender, Inhabitant receiver, MockContent content) {
			super(sender, receiver, content);
		}

		@Override
		public int getCost() {
			return 0;
		}

		@Override
		public void action() {
		}

	}

	protected class MockInhabitant extends Inhabitant {

		protected int nbOpen = 0;

		public MockInhabitant(String name, City city) {
			super(name, city);
		}

		@Override
		public void receivesLetter(Letter<?> Letter) {
			nbOpen++;
		}

	}

	// Attributes

	private City city;
	private MockInhabitant h1;
	private MockInhabitant h2;
	private MockInhabitant h3;

	// Tests' Initialization

	@Before
	public void init() {
		this.city = new City("Athenes");
		this.h1 = new MockInhabitant("Timoleon", this.city);
		this.h2 = new MockInhabitant("Socrate", this.city);
		this.h3 = new MockInhabitant("Aristophane", this.city);
		this.city.addInhabitant(this.h1);
		this.city.addInhabitant(this.h2);
		this.city.addLetter(new MockLetter(this.h1, this.h2, null));
	}

	// Tests

	@Test
	public void inhabitantIsInTheCityTest() {
		assertTrue(this.city.getInhabitant().contains(this.h1));
		assertTrue(this.city.getInhabitant().contains(this.h2));
	}

	@Test
	public void inhabitantIsNotInTheCityTest() {
		assertFalse(this.city.getInhabitant().contains(this.h3));
	}

	@Test
	public void inhabitantIsRemovedTest() {
		this.city.removeInhabitant(this.h1);
		assertFalse(this.city.getInhabitant().contains(this.h1));
	}

	@Test
	public void aLetterIsAddedInTheMailBox() {
		assertEquals(this.city.getMailbox().size(), 1);
	}

	@Test
	public void letterIsDistributedTest() {
		try {
			this.city.distributeLetters();
			assertEquals(1, this.h2.nbOpen);
			assertEquals(this.city.getMailbox().size(), 0);
		} catch (NotEnoughMoneyException e) {
			fail();
		}
	}
	
}