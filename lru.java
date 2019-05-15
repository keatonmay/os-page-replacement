import java.util.ArrayList;
import java.util.LinkedList;

public class lru {
	
	int frames;
	
	public lru(int frames) {
		this.frames = frames;
	}
	
	int pageReplace(ArrayList<Integer> list) {
		int faults = 0;
		LinkedList<Integer> pageFrame = new LinkedList<Integer>();
		
		for (int i = 0; i < list.size(); i++) {
			// if current pages in frames are smaller than number of frames, page fault, add number to top of stack
			if (pageFrame.size()< frames && !pageFrame.contains(list.get(i))) {
				pageFrame.addFirst(list.get(i));
				faults++;
			}
			// if page is not found in frames, page fault and add number to top of stack. Victim is bottom of stack, the last page referenced in the frames.
			else if (!pageFrame.contains(list.get(i))) {
				pageFrame.removeLast();
				pageFrame.addFirst(list.get(i));
				faults++;
			}
			// if the page is found in frames, do not page fault, but put number on top of stack to abide by LRU constraint for next page fault.
			else if(pageFrame.contains(list.get(i))) {
				pageFrame.removeFirstOccurrence(list.get(i));
				pageFrame.addFirst(list.get(i));
			}
		}
		
		return faults;
	}
}
