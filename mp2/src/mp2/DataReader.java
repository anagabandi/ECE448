package mp2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReader {
	private List<String> file;

	public DataReader(String fileName) throws Exception {
		file = new ArrayList<String>();
		
		InputStream fis = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));	
        String line;
        while((line = br.readLine()) != null) {
        	file.add(line);
        }
	}
	
	
	
	public int getNumberOfMeetings() {
		int ret = 0;
		String toMatch = "^Number of meetings: ([0-9]+)";
		Pattern  p = Pattern.compile(toMatch);
		
		Matcher m = null;
		for(String l : file) {
			if(l.matches(toMatch)) {
				m = p.matcher(l);
			}
		}
		
		if(m.find()) {
			ret = Integer.parseInt(m.group(1));
		}
		return ret;
	}
	
	public int getNumberOfTimeSlots() {
		String toMatch = "^Number of time slots: ([0-9]+)";
		Pattern  p = Pattern.compile(toMatch);
		int ret = 0;
		
		Matcher m = null;
		for(String l : file) {
			if(l.matches(toMatch)) {
				m = p.matcher(l);
			}
		}
		
		if(m.find()) {
			ret = Integer.parseInt(m.group(1));
		}
		return ret;
	}
	
	public int getNumberOfEmployees() {
		String toMatch = "^Number of employees: ([0-9]+)";
		Pattern  p = Pattern.compile(toMatch);
		int ret = 0;
		
		Matcher m = null;
		for(String l : file) {
			if(l.matches(toMatch)) {
				m = p.matcher(l);
			}
		}
		
		if(m.find()) {
			ret = Integer.parseInt(m.group(1));
		}
		return ret;
	}
	
	public List<Integer>[] getEmployeeMeetings() {
		int count = 0;
		
		for(String l : file) {
			if(l.matches("^[0-9]+: [0-9].*")) {
				count++;
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Integer>[] ret = new List[count + 1];
		for(int i = 1; i < ret.length; i++) {
			ret[i] = new ArrayList<Integer>();
		}
		
		int idx = 1;
		for(String l : file) {
			if(l.matches("^[0-9]+: [0-9].*")) {
				String [] arr = l.split("[^\\d]+");
				for(int j = 1; j < arr.length; j++) {
					ret[idx].add(Integer.parseInt(arr[j])); 
				}
				idx++;
			}
		}
		return ret;
	}
	
	public int [][] getTimeMatrix() {
		int count = 0;
		for(String l : file) {
			if(l.matches("^[0-9]+: {3,4}[0-9].*")) {
				count++;
			}
		}
		
		int [][] ret = new int[count + 1][count + 1];
		
		int idx = 1;
		for(String l : file) {
			if(l.matches("^[0-9]+: {3,4}[0-9].*")) {
				String [] arr = l.split("[^\\d]+");
				for(int j = 1; j < arr.length; j++) {
					ret[idx][j] = Integer.parseInt(arr[j]);
				}
				idx++;
			}
		}

		return ret;
	}
}
