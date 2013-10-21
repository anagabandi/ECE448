package mp2;

import java.util.ArrayList;
import java.util.List;

public class CSP implements Cloneable{
	private List<Integer> meetings;
	public boolean [][] possibilities;
	public List<Pair> possiblityCount;
	
	public int numMeetings;
	public int numTimeslots;
	
	public CSP() {
		this.meetings = new ArrayList<Integer>();
	}
	
	public CSP(int numMeetings, int numTimeslots) {
		this.meetings = new ArrayList<Integer>();
		for(int i = 1; i <= numMeetings; i++) {
			this.meetings.add(i);
		}
		this.numMeetings = numMeetings;
		this.numTimeslots = numTimeslots;
		
		possibilities = new boolean[numMeetings + 1][numTimeslots + 1];
		possiblityCount = new ArrayList<Pair>();
	}

	public List<Integer> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Integer> meetings) {
		this.meetings = meetings;
	}
	
	public void removeMeeting(int meetingToRemove) {
		this.meetings.remove((Object) meetingToRemove);
	}
	
	public void addMeeting(int meetingToAdd) {
		this.meetings.add(meetingToAdd);
	}
	
	@Override
	public CSP clone() {
		CSP ret = null;
		try {
			ret = (CSP)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Unable to clone.", e);
		}
		
		ret.meetings = new ArrayList<Integer>(this.meetings);
		
		ret.possibilities = new boolean[this.numMeetings + 1][this.numTimeslots + 1];
		ret.possiblityCount = new ArrayList<Pair>();
		
		return ret;
	}
	
}








