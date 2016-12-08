package controller;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import domain.PrgState;
import domain.adt.MyIStack;
import domain.stmt.IStmt;
import exception.MyException;
import repository.IRepository;
import repository.Repository;

public class Controller {
	IRepository myRepository;
	
	Controller() {}
	
	public Controller(Repository myRepository) {
		this.myRepository = myRepository;
	}
	
	public void addProgram(PrgState newPrg) {
		myRepository.addPrg(newPrg);
	}
	
	public PrgState oneStep(PrgState state) throws MyException {
		MyIStack<IStmt> stack = state.getExeStack();
		if(stack.isEmpty()) throw new MyException("Reached the end of the program!");
		IStmt crtStmt = stack.pop();
		return crtStmt.execute(state);
	}
	
	Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
			return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey()))
			 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	} 
	
	public void allStep(){
		PrgState prg = myRepository.getCrtPrg();
		try{
			myRepository.clearFile();
			while(true){
				oneStep(prg);
				prg.getHeap().setDictionary(conservativeGarbageCollector(
						 prg.getSymTable().getDictionary().values(),
						 prg.getHeap().getDictionary()));
				myRepository.logPrgStateExec();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
