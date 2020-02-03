package mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import mail.contents.Content;
import mail.exceptions.NotEnoughMoneyException;
import mail.letters.Letter;

public class InhabitantTest {

	// Mock Objects

	protected class MockContent implements Content {
	}

	protected class MockLetter extends Letter<MockContent> {

		protected int nbOpen = 0;

		public MockLetter(Inhabitant sender, Inhabitant receiver, MockContent content) {
			super(sender, receiver, content);
		}

		@Override
		public int getCost() {
			return 0;
		}

		@Override
		public void action() {
			nbOpen++;
		}

	}

	// Attributes

	private Inhabitant h;
	private City c;

	// Tests' Initialization

	@Before
	public void init() {
		this.c = new City("TimoLand");
		this.h = new Inhabitant("Timoleon", this.c);
	}

	// Tests

	@Test
	public void debitAccountIsCorrectTest() {
		int count = this.h.getAccount();
		this.h.debit(count);
		assertEquals(0, this.h.getAccount());
	}

	@Test
	public void creditAccountIsCorrectTest() {
		int count = this.h.getAccount();
		this.h.credit(count);
		assertEquals(count * 2, this.h.getAccount());
	}

	@Test
	public void inhabitantOpenTheLetterWhenReceiveTest() {
		try {
			MockLetter let = new MockLetter(this.h, this.h, null);
			this.h.receivesLetter(let);
			assertEquals(1, let.nbOpen);
		} catch(NotEnoughMoneyException e ) {
			fail();
		}
	}
	
	@Test
	public void inhabitantSendsALetterTest() {
		try {
			MockLetter let = new MockLetter(this.h, this.h, null);
			this.h.sendsLetter(let);
			assertEquals(this.c.getMailbox().size(), 1);
		} catch (NotEnoughMoneyException e) {
			fail();
		}
	}
	
	@Test
	public void twoInhabitantAreEqualsTest() {
		Inhabitant h2 = new Inhabitant("Timoleon", this.c);
		assertTrue(this.h.equals(h2));
	}
	
	@Test
	public void twoInhabitantAreNotEqualsTest() {
		Inhabitant h2 = new Inhabitant("Timoleon", this.c);
		Inhabitant h3 = new Inhabitant("Socrate", this.c);
		Inhabitant h4 = new Inhabitant("Timoleon", new City("Sparte"));
		h2.credit(10);
		
		assertFalse(this.h.equals(h2));
		assertFalse(this.h.equals(h3));
		assertFalse(this.h.equals(h4));
	}
}