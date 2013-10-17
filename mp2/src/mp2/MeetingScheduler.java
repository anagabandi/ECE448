package mp2;

import java.util.ArrayList;
import java.util.List;

public class MeetingScheduler {
	private int timeslots;
	
	public void search() {
		
	}
	
	public Assignment recursiveBacktracking(Assignment a, CSP c) {
		
		
		if(c.getMeetings().isEmpty()) return a;
		Assignment result;
		
		int meeting = selectMeeting(c);
		List<Integer> orderedTimeslots = orderTimeslots(meeting, c);
		
		for(Integer value : orderedTimeslots) {
			if(allowed(value, a, c)) {
				a.assign(meeting, value);
				result = recursiveBacktracking(a, c);
				if(!result.failure) return result;
				a.remove(meeting, value);
			}
		}
		a.failure = true;
		return a;
		
	}

	private boolean allowed(Integer value, Assignment a, CSP c) {
		// TODO Auto-generated method stub
		
		
		return false;
	}

	private ArrayList orderTimeslots(int meeting, CSP c) {
		// TODO Auto-generated method stub
		for(t : timeslots)
			updatePossibilities
		
		return new ArrayList<Integer>();
	}

	private int selectMeeting(CSP c) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	constraint1() {
		for all meetings in assignment
			if another meeting at the same timeslot
				check attendees from the other meeting to the current meeting
				return false if any attendees are the same
	}
	
	constraint2() {
		for all the attendees of the current meeting as i
			for all the assigned meetings i is attending as j
				check abs(assigned[j] - assigned[meeting]) > distance[j][meeting]
	}
}
