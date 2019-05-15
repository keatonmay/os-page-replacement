import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class page_replacement {

	public static void main(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Program takes 2 arguments: file name and integer number of frames.");
		}
		
		String fname = args[0];
		int frames = Integer.parseInt(args[1]);
		int[] pageFaults = new int[4];
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<Integer> reference = new ArrayList<Integer>();
		
		fifo f = new fifo(frames);
		optimal o = new optimal(frames);
		random r = new random(frames);
		lru l = new lru(frames);
		
		readByLine(fname, lines);
		
		for (int i=0;i < lines.size();i++) {
			splitline(lines.get(i), reference);
			
			pageFaults[0] += f.pageReplace(reference);
			pageFaults[1] += o.pageReplace(reference);
			pageFaults[2] += r.pageReplace(reference);
			pageFaults[3] += l.pageReplace(reference);
			
			reference.clear();
		}
		
		System.out.println("Average number of page faults for FIFO: "+pageFaults[0]/lines.size());
		System.out.println("Average number of page faults for Optimal: "+pageFaults[1]/lines.size());
		System.out.println("Average number of page faults for Random page replacement: "+pageFaults[2]/lines.size());
		System.out.println("Average number of page faults for LRU: "+pageFaults[3]/lines.size());
	}
	//This method saves one line at a time from the file name as a string. If there are 10 lines in the file, there will be 10 strings in ArrayList a
	public static void readByLine (String fileName, ArrayList<String> a) {
		
		try {
			File file = new File(fileName);
			Scanner s = new Scanner(file);
			s.useDelimiter("\\n");
			while (s.hasNext()) {
				a.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// This method parses integers from the lines read in.
	public static void splitline(String a, ArrayList<Integer> b) {
		String[] split = a.split(" ");
		for (String s : split) {
			b.add(Integer.parseInt(s));
		}
	}

}
