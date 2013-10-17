package mp2;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DataReader dr = new DataReader("problem1.txt");
		System.out.println("Number of meetings: " + dr.getNumberOfMeetings());
		System.out.println("Number of employees: " + dr.getNumberOfEmployees());
		System.out.println("Number of timeslots: " + dr.getNumberOfTimeSlots());
		
		dr.getEmployeeMeetings();
		
		dr.getTimeMatrix();
	}

}
