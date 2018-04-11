import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Sort1 {

	// given that we don't really know how to deal with a read/write error (IOException)
	// inside the functions, we'll let it pass through
	static List readFromStream(BufferedReader d) throws IOException
	{
		ArrayList lines=new ArrayList();

		while(true) {
			String input=d.readLine();
			if(input==null)
				break;
			lines.add(input);
		}
		return lines;
	}

	static void writeToStream(List l, Writer w1) throws IOException {
		PrintWriter w=new PrintWriter(w1);
		Iterator i=l.iterator();
		while(i.hasNext()) {
			w.println((String)(i.next()));
		}

	}

	static void inPlaceSort(List l) {
		java.util.Collections.sort(l);
	}
	// the default behavior for exceptions is OK for this program
	public static void main(String args[]) throws IOException {
		if(args.length!=2) {
			System.out.println("Usage: java Sort1 inputfile outputfile");
		} else {
			java.io.BufferedReader in=new BufferedReader(new FileReader(args[0]));
			Writer out=new FileWriter(args[1]);

			List l=readFromStream(in);
			inPlaceSort(l);
			writeToStream(l,out);

			in.close();
			out.close();
		}
	}
}