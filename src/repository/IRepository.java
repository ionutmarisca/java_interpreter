package repository;

import java.io.IOException;
import domain.PrgState;

public interface IRepository {
	void addPrg(PrgState newPrg);
	public void clearFile() throws IOException;
	void logPrgStateExec() throws IOException;
	void serializePrgState() throws IOException;
	void deserializePrgState() throws IOException, ClassNotFoundException;
	PrgState getCrtPrg();
}
