package service.Interface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IDataService <T> {
	void UploadData() throws IOException;
	List<T> getData() throws FileNotFoundException;
	T getByID(int ID) throws FileNotFoundException;
}
