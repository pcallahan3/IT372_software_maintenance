import java.io.*; // for Reader (and subclasses), Writer (and subclasses) and IOException
import java.util.*; // for List, ArrayList, Iterator

public class StringSorter {
	ArrayList lines;

	// given that we don't really know how to deal with a read/write error (IOException)
	// inside the functions, we'll let it pass through
	public void readFromStream(Reader r) throws IOException
	{
		BufferedReader br=new BufferedReader(r);
		lines=new ArrayList();

		while(true) {
			String input=br.readLine();
			if(input==null)
				break;
			lines.add(input);
		}
	}

	public void writeToStream(Writer w) throws IOException {
		PrintWriter pw=new PrintWriter(w);
		Iterator i=lines.iterator();
		while(i.hasNext()) {
			pw.println((String)(i.next()));
		}

	}

	public int addNums(int a, int b)  {

		return a + b;
	}



	// returns the index of the largest element in the list
	static int findIdxBiggest(List l, int from, int to) {
		String biggest= (String) l.get(0);
		int idxBiggest=from;

		for(int i=from+1; i<=to; ++i) {
			if(biggest.compareTo((String) l.get(i))<0) {// it is bigger than biggest
				biggest=(String)l.get(i);
				idxBiggest=i;
			}
		}
		return idxBiggest;
	}

	// assumes i1, i2 are in range
	static void swap(List l, int i1, int i2) {
		Object tmp=l.get(i1);
		l.set(i1, l.get(i2));
		l.set(i2, tmp);
	}


	public void sort() {
		for(int i=lines.size()-1; i>0; --i) {
			int big=findIdxBiggest(lines,0,i);
			swap(lines,i,big);
		}
	}

	/*
	void sort() {
		java.util.Collections.sort(lines);
	}
	*/

	public void sort(String inputFileName, String outputFileName) throws IOException{
			Reader in=new FileReader(inputFileName);
			Writer out=new FileWriter(outputFileName);

			StringSorter ss=new StringSorter();
			ss.readFromStream(in);
			ss.sort();
			ss.writeToStream(out);

			in.close();
			out.close();
	}
}
