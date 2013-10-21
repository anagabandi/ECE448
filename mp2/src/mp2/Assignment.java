package mp2;


import java.util.ArrayList;
import java.util.List;

public class Assignment implements Cloneable {
	public int [] meetings;
	public List<Integer> [] timeslots;
	
	public Assignment(int numMeetings, int numTimeslots) {
		meetings = new int[numMeetings + 1];
		timeslots = new List[numTimeslots + 1];
		
		for(int i=1; i < timeslots.length; i++) {
			timeslots[i] = new ArrayList<Integer>();
		}
	}
	
	@Override
	public Assignment clone() {
		Assignment ret = null;
		try {
			ret = (Assignment)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Unable to clone.", e);
		}
		
		ret.timeslots = new List[timeslots.length];
		for(int i = 1; i < ret.timeslots.length; i++) {
			ret.timeslots[i] = new ArrayList<Integer>(timeslots[i]);
		}
		
		ret.meetings = new int[meetings.length];
		for(int i = 1; i < ret.meetings.length; i++) {
			ret.meetings[i] = meetings[i];
		}
		
		return ret;
	}
	
	public void assign(int meeting, int value) {
		this.meetings[meeting] = value;
		this.timeslots[value].add(meeting);
	}
	
	public void remove(int meeting, int value) {
		this.meetings[meeting] = 0;
		this.timeslots[value].remove((Object) value);
	}
	
	public boolean isComplete() {
		for(int i = 1; i < meetings.length; i++) {
			if(meetings[i] == 0) return false;
		}
		return true;
	}
	
	public void printSolution() {
		// TODO Auto-generated method stub
		System.out.println("SOLUTION:");
		for(int i = 1; i < this.meetings.length; i++) {
			System.out.println("Meeting " + i + " is schedule at time " + this.meetings[i]);
		}
	}
}
