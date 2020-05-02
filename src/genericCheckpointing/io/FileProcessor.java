package genericCheckpointing.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import genericCheckpointing.driver.FileProcessorI;

/**
 * <p>
 * This class contains logic for file read and write operations using
 * BufferedReader and BufferedWriter.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class FileProcessor implements FileProcessorI {

	private String fileName;
	// A reader object used to read contents of a file.
	private BufferedReader fileReader = null;
	// A writer object used to write contents to a file.
	private BufferedWriter fileWriter = null;

	public FileProcessor(String fileNameIn) {
		fileName = fileNameIn;
	}

	/**
	 * <p>
	 * This method contains the logic to read contents of the file. It returns
	 * null when EOF is reached.
	 * </p>
	 */
	@Override
	public String read() {
		try {
			if (fileReader == null) {
				fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			}
			return fileReader.readLine();
		} catch (IOException exception) {
			System.err.println("Error occured while reading a file.");
			exception.printStackTrace();
			close();
			System.exit(1);
		}
		return null;

	}

	/**
	 * <p>
	 * This method contains logic to write the text content to the file.
	 * </p>
	 */
	@Override
	public void write(String content) {
		try {
			if (fileWriter == null) {
//				File file = new File(fileName);
//				if(file.delete()){
//					file.createNewFile();
//				}
				fileWriter = new BufferedWriter(new FileWriter(new File(fileName).getAbsoluteFile()));
			}
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException exception) {
			System.err.println("Error occured while writing to a file.");
			exception.printStackTrace();
			close();
			System.exit(1);
		}
	}

	/**
	 * <p>
	 * This method contains logic to close input and/or output resources.
	 * </p>
	 */
	@Override
	public void close() {
		try {
			if (null != fileReader) {
				fileReader.close();
			}
			if (null != fileWriter) {
				fileWriter.close();
			}
		} catch (IOException exception) {
			System.err.println("Error occured while closing a file.");
			exception.printStackTrace();
			System.exit(1);
		}
	}
}