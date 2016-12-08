package domain;

import java.io.BufferedReader;
import java.io.Serializable;

import domain.adt.MyIDictionary;
import domain.adt.MyIList;
import domain.adt.MyIStack;
import domain.adt.MyTuple;
import domain.stmt.IStmt;

public class PrgState implements Serializable {
	private static final long serialVersionUID = 1L;
	MyIStack<IStmt> exeStack;
	MyIDictionary<String, Integer> symTable;
	MyIList<Integer> out;
	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable;
	MyIDictionary<Integer, Integer> heap;
	IStmt originalProgram;
	 
	PrgState() {}
	 
	public PrgState(MyIStack<IStmt> stack, MyIDictionary<String,Integer> symTable, MyIList<Integer> out, MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable, MyIDictionary<Integer, Integer> heap, IStmt program){
		this.exeStack = stack;
		this.symTable = symTable;
		this.out = out;
		this.originalProgram = program;
		this.fileTable = fileTable;
		this.heap = heap;
		exeStack.push(program);
	}
	 
	public MyIStack<IStmt> getExeStack() {
		return this.exeStack;
	}
	 
	public void setExeStack(MyIStack<IStmt> exeStack) {
		this.exeStack = exeStack;
	}

	public MyIDictionary<String, Integer> getSymTable() {
		 return this.symTable;
	}
	
	public void setSymTable(MyIDictionary<String, Integer> symTable) {
		this.symTable = symTable;
	}
	 
	public MyIList<Integer> getOut() {
		return this.out;
	}
	
	public void setOut(MyIList<Integer> out) {
		this.out = out;
	}
    
	public MyIDictionary<Integer, MyTuple<String, BufferedReader>> getFileTable() {
		return this.fileTable;
	}
	
	public void setFileTable(MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable) {
		this.fileTable = fileTable;
	}
	
	public MyIDictionary<Integer, Integer> getHeap() {
		return this.heap;
	}
	
	public void setHeap(MyIDictionary<Integer, Integer> heap) {
		this.heap = heap;
	}
	 
	public IStmt getOriginalProgram() {
		return this.originalProgram;
	}
	 
	public void setOriginalProgram(IStmt originalProgram) {
		this.originalProgram = originalProgram;
	}
	
	public String toString() {
		return "ExeStack:\n" + exeStack.toString() + "Sym Table:\n" + symTable.toString() + "Print output:\n" + out.toString() + "FileTable:\n" + fileTable.toString() + "Heap:\n" + heap.toString();
	}
}