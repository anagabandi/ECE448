package mp2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MeetingScheduler {
        private String filename;
        private int numTimeslots;
        private int numEmployees;
        private int numMeetings;
        private List<Integer> [] employeeToMeetings; // Array of Employees. index is employee id, list is meetings they attend
        private List<Integer> [] meetingsToEmployees;// Array of meetings. index is meeting id, list is employees attending
        
        private int [][] timeMatrix;
        
        
        public int totalAssignmentAttempts = 0;
        
        public MeetingScheduler(String filename) {
                this.filename = filename;
        }
        
        private void setup() {
                DataReader dr;
                try {
                        dr = new DataReader(filename);
                        numEmployees = dr.getNumberOfEmployees();
                        numTimeslots = dr.getNumberOfTimeSlots();
                        numMeetings = dr.getNumberOfMeetings();
                        employeeToMeetings = dr.getEmployeeMeetings();
                        timeMatrix = dr.getTimeMatrix();
                        
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                convertETMtoMTE();
                
                
        }
        
        private void convertETMtoMTE() {
                meetingsToEmployees = new List[numMeetings + 1];
                for(int i = 1; i < meetingsToEmployees.length; i++) {
                        meetingsToEmployees[i] = new ArrayList<Integer>();
                }
                
                for(int i = 1; i < employeeToMeetings.length; i++) {
                        for(Integer meeting : employeeToMeetings[i]) {
                                meetingsToEmployees[meeting].add(i);
                        }
                }
        }
        
        public void search() {
                setup();
                System.out.println("Number of meetings: " + numMeetings);
                System.out.println("Number of employees: " + numEmployees);
                System.out.println("Number of timeslots: " + numTimeslots);
                
                System.out.println();
                System.out.println("Meetings that each employee has to attend");
                for(int i = 1; i < employeeToMeetings.length; i++) {
                        System.out.print(i + ":");
                        System.out.println(employeeToMeetings[i].toString());
                }
                
                System.out.println();
                System.out.println("Employees that have to attend each meeting:");
                for(int i = 1; i < meetingsToEmployees.length; i++) {
                        System.out.print(i + ":");
                        System.out.println(meetingsToEmployees[i].toString());
                }
                
                System.out.println();
                for(int i = 1; i < timeMatrix.length; i++) {
                        for(int j = 1; j < timeMatrix.length; j++) {
                                System.out.print(timeMatrix[i][j] + " ");
                        }
                        System.out.println();
                }            
                Assignment a = recursiveBacktracking(new Assignment(numMeetings, numTimeslots), new CSP(numMeetings, numTimeslots));
                
                System.out.println();
                if(a != null) {
                        a.printSolution();
                        System.out.println();
                        System.out.println("Total number of assignments tried: " + this.totalAssignmentAttempts);
                }
                else
                        System.out.println("Something went wrong...");
                
                
        }
        
        
        

        public Assignment recursiveBacktracking(Assignment a, CSP c) {
                
//                System.out.println("Recursing!");
//                System.out.println(c.getMeetings().size());
                if(a.isComplete()) return a;
                
                Assignment result;
                
                populatePossibilitiesMatrix(a, c);
                
                
                		int meeting= mostConstrainedMeeting(c, a);
                        List<Pair> orderedTimeslots = orderTimeslots(meeting, a.clone(), c.clone()); // orders the values based on constraints
                        //Collections.shuffle(orderedTimeslots);
                        for(int j = 0; j < orderedTimeslots.size(); j++) 
                        {
                                int value = orderedTimeslots.get(j).key;
                                if(c.possibilities[meeting][value]) 
                                { // if the value assigned to the variable does not break any constraints...
                                        a.assign(meeting, value); // add this pair to the assignment
                                        c.removeMeeting(meeting); // and remove the meeting from the csp statement
                                        this.totalAssignmentAttempts++;
                                        
                                        
                                        result = recursiveBacktracking(a.clone(), c.clone()); // recurse into the problem using this meeting set
                                        
                                        if(result != null) 
                                        { // if we get an assignment right, that means somewhere the assignment was finished
                                                return result; // return this to get out of the recursive function.
                                        }
                                        
                                        a.remove(meeting, value); // if we hit the bottom without coming up to a solution, one of the assignments was wrong
                                                                                         // so we need to remove this var/value pair from the assignment
                                        c.addMeeting(meeting);   // and add it back to the csp statement so we can possibly use it again
                                }
                                
                        }
                        return null; // if none of the values work f
        }

        
        private void populatePossibilitiesMatrix(Assignment a, CSP c) {
                // TODO Auto-generated method stub
                for(int i = 1; i <= c.numMeetings; i++) {
                	
                        for(int j = 1; j <= numTimeslots; j++) {
                        	if(a.meetings[i]!=0)
                        		c.possibilities[i][j] = false;
                        	else
                        	{
                                if(allowed(i,  j,  a,  c)) 
                                {
                                        c.possibilities[i][j] = true;
                                } 
                                else 
                                {
                                        c.possibilities[i][j] = false;
                                }
                        	}
                        }
                	
                }
                c.possiblityCount= new ArrayList<Pair>();
                c.possiblityCount.add(new Pair(0, 0));
                for(int i = 1; i <= c.numMeetings; i++) {
                        int count = 0;
                        for(int j = 1; j <= c.numTimeslots; j++) {
                                if(c.possibilities[i][j] == true) count++;
                        }
                        c.possiblityCount.add(new Pair(i, count));
                }
                /*
                for(int k=1; k<a.meetings.length; k++)
                {
                	System.out.println("time assigned for meeting "+ k+ " :"+ a.meetings[k]);
                }
                for(int i = 1; i <= numMeetings; i++) {
                    for(int j = 1; j <= numTimeslots; j++) {
                            System.out.print(c.possibilities[i][j] + " ");
                    }
                    System.out.println();
                }
                */
                
        }

        private boolean allowed(Integer meeting, Integer value, Assignment a, CSP c) {
                // TODO Auto-generated method stub
                boolean c1 = constraint1(meeting, value, a);
                boolean c2 = constraint2(meeting, value, a);
                return c2 && c1;
                
        }

        private List<Pair> orderTimeslots(int meeting, Assignment a, CSP c) {
                // TODO Auto-generated method stub
                //for(t : timeslots)
                        //updatePossibilities
        	
        	  Comparator<Pair> comp = new Comparator<Pair> () {
                  @Override
                  public int compare(Pair n1, Pair n2) {
                          if(n1.value > n2.value){
                                  return +1;
                          }
                          else if(n1.value < n2.value){
                                  return -1;
                          }
                          else {
                                  return 0;
                          }
                  }
          };
          
        		boolean [][] possibilities = new boolean[c.numMeetings + 1][c.numTimeslots + 1];
                List<Pair> list = new ArrayList<Pair>();

        		for(int i = 1; i <= c.numMeetings; i++) {
        			for(int j = 1; j <= c.numTimeslots; j++) {
	                 	possibilities[i][j] = false;
        			}
	        	 }
        		
        		for(int q = 1; q <= c.numTimeslots; q++) {
        			int numFalses = 0;
        			a.assign(meeting, q);
        			for(int i = 1; i <= c.numMeetings; i++) { 	
        				for(int j = 1; j <= numTimeslots; j++) {
        					if(a.meetings[i]!=0) possibilities[i][j] = false;
        					
        					else {
        						if(allowed(i,  j,  a,  c)) {
        							possibilities[i][j] = true;
        							
        						} else {
        							possibilities[i][j] = false;
        							numFalses++;
        						}
        					}
        				}
   	        	 	}
        			list.add(new Pair(q, numFalses));
        			a.remove(meeting, q);
   
        			
        		}
        		
        		Collections.sort(list, comp);
                return list;
        }
        
        private int mostConstrainedMeeting(CSP c, Assignment a) 
        {
        	int minPossib=c.possiblityCount.get(1).value;
        	int keyMin=1;
        	for(int i=1; i< c.possiblityCount.size(); i++)
	        {
        		if(a.meetings[i]==0)
        		{
		            if(c.possiblityCount.get(i).value < minPossib)
		            	minPossib=c.possiblityCount.get(i).value;
		            	keyMin=c.possiblityCount.get(i).key;
        		}
	        }
        	//System.out.println("looking at meeting " +keyMin);
        	return keyMin;
        }
        private Pair[] orderMeetings(CSP c, Assignment a) {
                Comparator<Pair> comp = new Comparator<Pair> () {
                        @Override
                        public int compare(Pair n1, Pair n2) {
                                if(n1.value > n2.value){
                                        return +1;
                                }
                                else if(n1.value < n2.value){
                                        return -1;
                                }
                                else {
                                        return 0;
                                }
                        }
                };
                
              Pair[] list = new Pair[c.possiblityCount.size()];
             
              list[0]=new Pair(0,-5);
              for(int i=1; i< c.possiblityCount.size(); i++)
	          {
	            list[i]=new Pair(c.possiblityCount.get(i).key, c.possiblityCount.get(i).value);
	            if(a.meetings[c.possiblityCount.get(i).key]!=0)
	            	list[i]=null;
	          }
              int count=0;
              int size=0;
              for(int j=0; j<c.possiblityCount.size(); j++)
              {
            	  if(list[j]!=null)
            	  {
            		  size++;
            	  }
              }
              Pair[] newList= new Pair[size];
              for(int j=0; j<c.possiblityCount.size(); j++)
              {
            	  if(list[j]!=null)
            	  {
            		  newList[count]=new Pair(list[j].key, list[j].value);
            	  	  count++;
            	  }
              }
              
                Arrays.sort(newList, comp);
                
                
                return newList;
                
        }
        
        /* for all meetings in assignment
                if another meeting at the same timeslot
                        check attendees from the other meeting to the current meeting
                        return false if any attendees are the same */
        private boolean constraint1(int meeting, int timeslot, Assignment a) {
                for(int i = 1; i < a.meetings.length; i++) {
                        if(a.meetings[i] == timeslot) {
                                if(hasIntersection(meetingsToEmployees[meeting], meetingsToEmployees[i])) return false;
                        }
                }
                return true;
        }
        
        private boolean hasIntersection(List<Integer> list1, List<Integer> list2) {
	        for (Integer t : list1) {
	            if(list2.contains(t)) {
	            	return true;
	            }
	        }
	        return false;
        }
        

        /* for all the attendees of the current meeting as i
        for all the assigned meetings i is attending as j
                check abs(assigned[j] - assigned[meeting]) > distance[j][meeting] */
        private boolean constraint2(int meeting, int timeslot, Assignment a) {
                List<Integer> employeesAtMeeting = meetingsToEmployees[meeting];
                for(Integer i : employeesAtMeeting) 
                {
                        List<Integer> meetingsTheseEmployeesHaveToAttend = employeeToMeetings[i];
                        for(Integer j : meetingsTheseEmployeesHaveToAttend) 
                        {
                                if((a.meetings[j] != 0) && (j != meeting)) 
                                {
                                        if((Math.abs(timeslot - a.meetings[j])) <= timeMatrix[meeting][j]) 
                                        	return false;
                                        

                                }
                        }
                }
                return true;
        }

}