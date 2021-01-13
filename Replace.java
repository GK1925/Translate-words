package translator;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Replace {
	static long before=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	static long start=System.currentTimeMillis();
	private static final String IN_FILE="c:/Users/MANIVANNAN/Downloads/t8.shakespeare.txt";
	private static final String OUT_FILE="C:/Users/MANIVANNAN/Desktop/output.txt";
	
	public static void main(String []args) throws IOException{
		tr t=new tr();
		t.replaceTextFile(IN_FILE, OUT_FILE);
		long after=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long actual=after-before;
		long end=System.currentTimeMillis();
		NumberFormat formatter=new DecimalFormat("#0.00000");
		System.out.println("Memory Usage: "+(double)actual*1024*1024+" mb");
		System.out.println("Running Time: "+formatter.format((end-start)/1000d)+" seconds");
		
	}

}
