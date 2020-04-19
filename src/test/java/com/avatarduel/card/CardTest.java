/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.card;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author indraf
 */
public class CardTest {
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Card.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElement method, of class Card.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        Element expResult = Element.WATER;
        Element result = instance.getElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Card.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        CardType expResult = CardType.CHARACTER;
        CardType result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Card.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        String expResult = "Kartu Langka";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Card.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        String expResult = "Ini Kartu Langka";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class Card.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
                        .setImagePath("Ini Path")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        String expResult = "Ini Path";
        String result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Card.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        instance.setIsSummoned(false);
        boolean expResult = false;
        boolean result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSummonable method, of class Card.
     */
    @Test
    public void testGetSummonable() {
        System.out.println("getSummonable");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        instance.setIsSummonable(false);
        boolean expResult = false;
        boolean result = instance.getSummonable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsSummonable method, of class Card.
     */
    @Test
    public void testSetIsSummonable() {
        System.out.println("setIsSummonable");
        boolean b = false;
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        instance.setIsSummonable(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsSummoned method, of class Card.
     */
    @Test
    public void testGetIsSummoned() {
        System.out.println("getIsSummoned");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        instance.setIsSummoned(false);
        boolean expResult = false;
        boolean result = instance.getIsSummoned();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsSummoned method, of class Card.
     */
    @Test
    public void testSetIsSummoned() {
        System.out.println("setIsSummoned");
        boolean b = false;
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        instance.setIsSummoned(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPower method, of class Card.
     */
    @Test
    public void testGetPower() {
        System.out.println("getPower");
        Card instance = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();
        int expResult = 2;
        int result = instance.getPower();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CardImpl extends Card {

        public CardImpl() {
            super(null);
        }

        public int getPower() {
            return 0;
        }

        public String getStatsAsString() {
            return "";
        }

        public String getAtkAsString() {
            return "";
        }

        public String getDefAsString() {
            return "";
        }

        public String getPowAsString() {
            return "";
        }
    }
    
}
