package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.adt.MyIStack;
import domain.adt.MyTuple;
import domain.stmt.IStmt;

public class Repository implements IRepository {
	PrgState myPrgState;
	String logFilePath;
	
	public Repository(String logFilePath) {
		this.logFilePath = logFilePath;
	}
	
	@Override
	public PrgState getCrtPrg() {
		return myPrgState;
	}

	@Override
	public void addPrg(PrgState newPrg) {
		myPrgState = newPrg;
	}
	
	@Override
	public void clearFile() throws IOException {
		PrintWriter logFile;
		logFile = new PrintWriter(new FileWriter(logFilePath));
		logFile.close();
	}

	@Override
	public void logPrgStateExec() throws IOException {
		PrintWriter logFile;
		logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		MyIStack<IStmt> stack = myPrgState.getExeStack();
		MyIDictionary<String,Integer> symTable = myPrgState.getSymTable();
		MyIList<Integer> queue = myPrgState.getOut();
    	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = myPrgState.getFileTable();getClass();
    	MyIDictionary<Integer, Integer> heap = myPrgState.getHeap();
		logFile.println("ExeStack:");
		ArrayList<IStmt> a = new ArrayList<IStmt>(stack.getStack());
		ListIterator<IStmt> li = a.listIterator(a.size());
		while(li.hasPrevious())
		{
			logFile.println("-> " + li.previous().toString());
		}
		logFile.println("SymTable:");
		for(HashMap.Entry<String, Integer> e : symTable.getDictionary().entrySet())
		{
		    logFile.println("-> " + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().toString());
		}
		logFile.println("Out:");
		for(Integer e : queue.getList())
		{
		    logFile.println("-> " + e.toString());
		}
		logFile.println("FileTable:");
		for(HashMap.Entry<Integer, MyTuple<String, BufferedReader>> e : fileTable.getDictionary().entrySet())
		{
		    logFile.println("-> " + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().getFirst());
		}
		logFile.println("Heap:");
		for(HashMap.Entry<Integer, Integer> e : heap.getDictionary().entrySet())
		{
		    logFile.println("-> " + "Key: " + e.getKey().toString() + ", Value: " + e.getValue().toString());
		}
		logFile.println("------------------------------------------");
		logFile.close();
	}

	@Override
	public void serializePrgState() throws IOException {
		FileOutputStream myFile = new FileOutputStream("prgState.ser");
		ObjectOutputStream outStream = new ObjectOutputStream(myFile);
		outStream.writeObject(myPrgState);
		outStream.close();
	}

	@Override
	public void deserializePrgState() throws IOException, ClassNotFoundException {
		FileInputStream myFile = new FileInputStream("prgState.ser");
		ObjectInputStream inStream = new ObjectInputStream(myFile);
		myPrgState = (PrgState) inStream.readObject();
		inStream.close();
	}
}
