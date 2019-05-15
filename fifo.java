import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class fifo {
	int frames;
	
	public fifo (int frames) {
		this.frames = frames;
	}
	
	int pageReplace(ArrayList<Integer> list) {
		int faults = 0;
		Queue<Integer> pageFrame = new LinkedList<>();
		
		for (int i = 0; i < list.size(); i++) {
			// if the frames aren't full and aren't already in the list, page fault
			if (pageFrame.size()< frames && !pageFrame.contains(list.get(i))) {
				pageFrame.add(list.get(i));
				faults++;
			}
			// if the page isn't in frames, page fault, remove from front and insert new page at back of list
			else if (!pageFrame.contains(list.get(i))) {
				pageFrame.remove();
				pageFrame.add(list.get(i));
				faults++;
			}
		}
		
		return faults;
	}
}
