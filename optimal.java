import java.util.ArrayList;
import java.util.LinkedList;

public class optimal {
	
	int frames;
	
	public optimal(int frames) {
		this.frames = frames;
	}
	
	int pageReplace(ArrayList<Integer> list) {
		int faults = 0;
		LinkedList<Integer> pageFrame = new LinkedList<Integer>();
		LinkedList<Integer> nextUse = new LinkedList<Integer>();
		int index = 0;
		
		for (int i = 0; i < list.size(); i++) {
			// if frames are empty, page fault and add to list. find the next occurrence of this page in the reference string and save that index to use for future page faults.
			if (pageFrame.size()< frames && !pageFrame.contains(list.get(i))) {
				pageFrame.add(list.get(i));
				nextUse.add(getNextIndex(i, list.get(i), list));
				faults++;
			}
			// if page from ref string is not found, page fault
			else if (!pageFrame.contains(list.get(i))) {
				int highest = nextUse.get(0);
				// find the page currently in frames that has the greatest nextUse index. This page is replaced.
				for(int j=0; j<nextUse.size();j++) {
					if(nextUse.get(j) > nextUse.get((j+1)%nextUse.size()) && nextUse.get(j) > highest) {
						highest = nextUse.get(j);
						index = j;
					}
				}
				
				pageFrame.remove(index);
				nextUse.remove(index);
				pageFrame.add(list.get(i));
				nextUse.add(getNextIndex(i, list.get(i), list));
				faults++;
			}
			// if page is in frames, do not page fault, but update next use index of the page
			else if (pageFrame.contains(list.get(i))) {
				index = pageFrame.indexOf(list.get(i));
				nextUse.set(index, getNextIndex(i, list.get(i), list));
			}
		}
		
		return faults;
	}
	// this function returns the next reference to a page in a reference string
	int getNextIndex(int i, int match, ArrayList<Integer> a) {
		int j;
		for(j = i+1; j < a.size(); j++) {
			if(a.get(j) == match) {
				return j;
			}
		}
		return j;
	}
}
