import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class random {
	
	int frames;
	
	public random(int frames) {
		this.frames = frames;
	}
	
	int pageReplace(ArrayList<Integer> list) {
		int faults = 0;
		LinkedList<Integer> pageFrame = new LinkedList<Integer>();
		
		for (int i = 0; i < list.size(); i++) {
			// if frames aren't full, page fault
			if (pageFrame.size()< frames && !pageFrame.contains(list.get(i))) {
				pageFrame.add(list.get(i));
				faults++;
			}
			// if page not found in frames, page fault and randomly replace a page from the list
			else if (!pageFrame.contains(list.get(i))) {
				pageFrame.remove(ThreadLocalRandom.current().nextInt(0, frames));
				pageFrame.add(list.get(i));
				faults++;
			}
		}
		
		return faults;
	}
}
