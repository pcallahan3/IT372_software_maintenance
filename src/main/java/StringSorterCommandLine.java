import java.io.IOException;

public class StringSorterCommandLine {
	public static void main(String args[]) throws IOException {
		if(args.length!=2) {
			System.out.println("Usage: java Sort1 inputfile outputfile");
		} else {
			StringSorter ss=new StringSorter();
			ss.sort(args[0],args[1]);
		}
	}
}