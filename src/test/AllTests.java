package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.adt.MyDictionary;
import domain.adt.MyIDictionary;
import domain.exp.ArithExp;
import domain.exp.ConstExp;
import domain.exp.Exp;
import domain.exp.VarExp;
import exception.MyException;

public class AllTests {

	@Test
	public void ConstExpTest() {
    	MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
    	MyIDictionary<Integer, Integer> heap = new MyDictionary<Integer, Integer>();
		Exp myFirstConstExp = new ConstExp(10);
		try {
			assertEquals(10, myFirstConstExp.eval(symTable, heap));
		} catch (MyException e) {
			fail();
		}
		Exp mySecondConstExp = new ConstExp(12);
		try {
			assertEquals(12, mySecondConstExp.eval(symTable, heap));
		} catch (MyException e) {
			fail();
		}
	}
	
	@Test
	public void ArithExpTest() {
    	MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
    	MyIDictionary<Integer, Integer> heap = new MyDictionary<Integer, Integer>();
		Exp myFirstArithExp = new ArithExp('+', new ConstExp(10), new ConstExp(6));
		try {
			assertEquals(16, myFirstArithExp.eval(symTable, heap));
		} catch (MyException e) {
			fail();
		}
		Exp mySecondArithExp = new ArithExp('*', new ConstExp(5), new ConstExp(3));
		try {
			assertEquals(15, mySecondArithExp.eval(symTable, heap));
		} catch (MyException e) {
			fail();
		}
		Exp myThirdArithExp = new ArithExp('/', new ConstExp(25), new ConstExp(0));
		try {
			myThirdArithExp.eval(symTable, heap);
		} catch (MyException e) {
		}
		Exp myFourthArithExp = new ArithExp('z', new ConstExp(5), new ConstExp(12));
		try {
			myFourthArithExp.eval(symTable, heap);
		} catch (MyException e) {
		}
	}
	
	@Test
	public void VarExpTest() {
    	MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
    	MyIDictionary<Integer, Integer> heap = new MyDictionary<Integer, Integer>();
		Exp myFirstArithExp = new VarExp("x");
		try {
			myFirstArithExp.eval(symTable, heap);
		} catch (MyException e) {
		}
	}
}
