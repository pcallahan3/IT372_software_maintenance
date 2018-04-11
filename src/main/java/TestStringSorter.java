import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.io.*;

import static org.junit.Assert.assertNotEquals;

public class TestStringSorter extends TestCase {

	private ArrayList make123() {
		ArrayList l = new ArrayList();
		l.add("one");
		l.add("two");
		l.add("three");

		return l;
	}

	public void testFindIdxBiggest() {
		StringSorter ss=new StringSorter();
		ArrayList l = make123();

		int i=StringSorter.findIdxBiggest(l,0,l.size()-1);
		assertEquals(i,1);
	}

	public void testFailFindIdxBiggest()
	{
		StringSorter ss=new StringSorter();
		ArrayList arrayList = make123();

		int i=StringSorter.findIdxBiggest(arrayList,1,arrayList.size()-1);
		assertNotEquals("Couldn't find the biggest index", i,1);
	}

	public void testSwap() {
		ArrayList l1= make123();

		//Verify that items are being swapped
		ArrayList l2=new ArrayList();
		l2.add("one");
		l2.add("three");
		l2.add("two");

		//Tests
		StringSorter.swap(l1,1,2);
		Assert.assertEquals("Swap has failed the test", l1,l2);
	}

	public void testFailSwap() {

		ArrayList l1= make123();

		//Verfiy that items aren't being swapped
		ArrayList arrayList = new ArrayList();
		arrayList.add("three");
		arrayList.add("two");
		arrayList.add("one");

		//Tests
		StringSorter.swap(l1,1,2);
		assertNotEquals("Swap has failed the test", l1 ,arrayList);
	}

	public void testReadFromStream() throws IOException{
		Reader in=new FileReader("in.txt");
		StringSorter ss=new StringSorter();
		ArrayList l= make123();

		ss.readFromStream(in);
		assertEquals(l,ss.lines);
	}


	public void testFailReadFromStream() throws IOException{
		Reader in=new FileReader("out.txt");
		StringSorter ss=new StringSorter();
		ArrayList arrayList= make123();

		ss.readFromStream(in);
		assertNotEquals("Data couldnt be read from stream",  arrayList,  ss.lines);
	}



	public void testSort1() {
		StringSorter ss= new StringSorter();
		ss.lines=make123();



		ArrayList l2=new ArrayList();
		l2.add("one");
		l2.add("two");
		l2.add("three");

		assertEquals(l2,ss.lines);
	}

	public void testFailSort1(){

		StringSorter ss= new StringSorter();
		ss.lines=make123();
		ArrayList arrayList = new ArrayList();
		arrayList.add("one");
		arrayList.add("three");
		arrayList.add("two");

		ss.sort();

		Assert.assertEquals("Test failed",ss.lines, arrayList);

	}



	public void testWriteToStream() throws IOException{
		// write out a known value
		StringSorter ss1=new StringSorter();
		ss1.lines=make123();
		Writer out=new FileWriter("test.out");
		ss1.writeToStream(out);
		out.close();

		// then read it and compare
		Reader in=new FileReader("in.txt");
		StringSorter ss2=new StringSorter();
		ss2.readFromStream(in);
		assertEquals(ss1.lines,ss2.lines);
	}


	public void testFailWriteToStream() throws IOException{
		// write out a known value
		StringSorter ss1=new StringSorter();
		ss1.lines=make123();
		Writer out=new FileWriter("test2.out");
		ss1.writeToStream(out);
		out.close();

		// then read it and compare
		Reader in=new FileReader("out.txt");
		StringSorter ss2=new StringSorter();
		ss2.readFromStream(in);
		assertNotEquals("Failed to write to stream", ss1.lines, ss2.lines);
	}


	public void testSort2() throws IOException{
		// write out a known value
		StringSorter ss1=new StringSorter();
		ss1.sort("in.txt","test2.out");

		ArrayList l=new ArrayList();
		l.add("one");
		l.add("three");
		l.add("two");

		// then read it and compare
		Reader in=new FileReader("test2.out");
		StringSorter ss2=new StringSorter();
		ss2.readFromStream(in);
		assertEquals(l,ss2.lines);
	}

	public void testFailSort2() throws IOException{
		// write out a known value
		StringSorter ss1=new StringSorter();
		ss1.sort("in.txt","test2.out");

		ArrayList l=new ArrayList();
		l.add("one");
		l.add("three");
		l.add("two");

		// then read it and compare
		Reader in=new FileReader("test.out");
		StringSorter ss2=new StringSorter();
		ss2.readFromStream(in);
		assertNotEquals("Test failed", l,ss2.lines);
	}

}
