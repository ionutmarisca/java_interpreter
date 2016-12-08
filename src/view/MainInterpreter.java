package view;

import java.io.BufferedReader;
import controller.Controller;
import domain.PrgState;
import domain.adt.MyDictionary;
import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.adt.MyIStack;
import domain.adt.MyList;
import domain.adt.MyStack;
import domain.adt.MyTuple;
import domain.exp.ArithExp;
import domain.exp.ConstExp;
import domain.exp.HeapRead;
import domain.exp.VarExp;
import domain.stmt.AssignStmt;
import domain.stmt.CloseRFile;
import domain.stmt.CompStmt;
import domain.stmt.HeapAllocation;
import domain.stmt.HeapWriting;
import domain.stmt.IStmt;
import domain.stmt.IfStmt;
import domain.stmt.OpenRFile;
import domain.stmt.PrintStmt;
import domain.stmt.ReadFile;
import domain.stmt.WhileStmt;
import repository.Repository;

public class MainInterpreter {
	static Repository myFirstRepository, mySecondRepository, myThirdRepository, myLastRepository;
	static Controller myFirstController, mySecondController, myThirdController, myLastController;
	
    public static void main(String[] args) {
    	myFirstRepository = new Repository("firstProgramLog.txt");
    	myFirstController = new Controller(myFirstRepository);
    	mySecondRepository = new Repository("secondProgramLog.txt");
    	mySecondController = new Controller(mySecondRepository);
    	myThirdRepository = new Repository("thirdProgramLog.txt");
    	myThirdController = new Controller(myThirdRepository);
    	myLastRepository = new Repository("lastProgramLog.txt");
    	myLastController = new Controller(myLastRepository);
        IStmt firstProgram = new IfStmt(new ConstExp(10), new CompStmt(new CompStmt(new AssignStmt("v", new ConstExp(5)), new AssignStmt("m", new ConstExp(6))), new PrintStmt(new ArithExp('/', new VarExp("v"), new ConstExp(3)))), new PrintStmt(new ConstExp(100)));
        IStmt secondProgram = new CompStmt(new OpenRFile("f","test.in"), new CompStmt(new ReadFile(new VarExp("f"), "c"), new CompStmt(new PrintStmt(new VarExp("c")), new CompStmt(new IfStmt(new VarExp("c"), new CompStmt(new ReadFile(new VarExp("f"), "c"), new PrintStmt(new VarExp("c"))), new PrintStmt(new ConstExp(0))), new CloseRFile(new VarExp("f"))))));
        IStmt thirdProgram = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new HeapAllocation("v", new ConstExp(20)), new CompStmt(new HeapAllocation("a", new ConstExp(22)), new CompStmt(new HeapWriting("a", new ConstExp(30)), new CompStmt(new PrintStmt(new VarExp("a")), new CompStmt(new PrintStmt(new HeapRead("a")), new AssignStmt("a", new ConstExp(0))))))));
        IStmt lastProgram = new CompStmt(new AssignStmt("v", new ConstExp(6)), new WhileStmt(new ArithExp('-', new VarExp("v"), new ConstExp(4)), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1))))));
        MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
    	MyIDictionary<String, Integer> symTable1 = new MyDictionary<String, Integer>();
    	MyIList<Integer> out1 = new MyList<Integer>();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable1 = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
    	MyIDictionary<Integer, Integer> heap1 = new MyDictionary<Integer, Integer>();
    	PrgState myPrgState1 = new PrgState(exeStack1, symTable1, out1, fileTable1, heap1, firstProgram);
    	myFirstController.addProgram(myPrgState1);
    	/*
    	 * This part of code is used to see if the deserialize works.
	    	try {
				myFirstRepository.serializePrgState();
				myFirstRepository.deserializePrgState();
				System.out.println("Now run the first program to see if it actually works after deserialize. Check the logs!");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
			}
		*/
        MyIStack<IStmt> exeStack2 = new MyStack<IStmt>();
    	MyIDictionary<String, Integer> symTable2 = new MyDictionary<String, Integer>();
    	MyIList<Integer> out2 = new MyList<Integer>();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable2 = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
    	MyIDictionary<Integer, Integer> heap2 = new MyDictionary<Integer, Integer>();
    	PrgState myPrgState2 = new PrgState(exeStack2, symTable2, out2, fileTable2, heap2, secondProgram);
    	mySecondController.addProgram(myPrgState2);
        MyIStack<IStmt> exeStack3 = new MyStack<IStmt>();
    	MyIDictionary<String, Integer> symTable3 = new MyDictionary<String, Integer>();
    	MyIList<Integer> out3 = new MyList<Integer>();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable3 = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
    	MyIDictionary<Integer, Integer> heap3 = new MyDictionary<Integer, Integer>();
    	PrgState myPrgState3 = new PrgState(exeStack3, symTable3, out3, fileTable3, heap3, thirdProgram);
    	myThirdController.addProgram(myPrgState3);
        MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
    	MyIDictionary<String, Integer> symTable4 = new MyDictionary<String, Integer>();
    	MyIList<Integer> out4 = new MyList<Integer>();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable4 = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
    	MyIDictionary<Integer, Integer> heap4 = new MyDictionary<Integer, Integer>();
    	PrgState myPrgState4 = new PrgState(exeStack4, symTable4, out4, fileTable4, heap4, lastProgram);
    	myLastController.addProgram(myPrgState4);
    	TextMenu menu = new TextMenu();
    	menu.addCommand(new ExitCommand("0", "Exit."));
    	menu.addCommand(new RunExample("1", firstProgram.toString(), myFirstController));
    	menu.addCommand(new RunExample("2", secondProgram.toString(), mySecondController));
    	menu.addCommand(new RunExample("3", thirdProgram.toString(), myThirdController));
    	menu.addCommand(new RunExample("4", lastProgram.toString(), myLastController));
    	menu.show();
    }
}
