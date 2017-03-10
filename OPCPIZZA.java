package programs;






import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
class Jobs
{
	int startTime;
	int durationTime;
	int cost;
	Jobs(int startTime, int durationTime, int cost )
	{
		this.startTime = startTime;
		this.durationTime = durationTime;
		this.cost  = cost;
	}
}

class jobComparator implements Comparator<Jobs>
{
	public int compare(Jobs a, Jobs b)
	{
		return a.durationTime < b.durationTime ? -1 : a.durationTime == b.durationTime ? 0 : 1;
	}

	
}

public class PAIRS1{
	  private static Reader in;
	  private static PrintWriter out;
	  public static void main(String[] args) throws IOException {
		  in = new Reader();
		  out = new PrintWriter(System.out, true);
		  int T = in.nextInt();
		  for(int oo = 0; oo<T; oo++){
		  int N = in.nextInt();
		  int M = in.nextInt();
		  long num[] = new long[N];
		  for(int i = 0; i<N; i++)
		  {
		  num[i] = in.nextInt();
		  }
		  //for(int i = 0; i<N; i++)
		  //{
			//  System.out.print(num[i]);
		  //}
		  Arrays.sort(num);
		  //System.out.println("After Sorting");
		  //for(int i = 0; i<N; i++)
		  //{
		//	  System.out.print(num[i]);
		 // }
		  int start = 0;
		  int end = N-1;
		  
		  int ans = 0;
		  while(start<end)
		  {
			  if(num[start]+num[end] < M)
			  {
				  start++;
			  }
			  else if(num[start]+num[end]>M)
			  {
				  end--;
			  }
			  else {
				  int c1 = 1;
				  int c2 = 1;
				  start++;
				   end--;
				   while(start<end && num[start]==num[start-1])
				   {
					   start++;
					   c1++;
				   }
				   while(start<end && num[end] == num[end+1])
				   {
					   c2++;
					   end--;
				   }
				   
				   ans = ans + c1*c2;
				  
				  
			  }
		  }
		  
		  System.out.println(ans);
		  }
	  }
	  
	 
	  
	 public static int b_search(long[] num,int start, int end, long search )
	 {
		 while(start<=end)
		 {
			 int mid = (start+end)/2;
			 if(num[mid]>search)
			 {
				 end = mid-1;
			 }
			 else if(num[mid]<search)
			 {
				 start = mid+1;
			 }
			 else{
				 return mid;
			 }
			 
			 
		 }
		 
		 return -1;
	 }
	
	  


}




/** Faster input **/
class Reader
{
    final private int BUFFER_SIZE = 1 << 16;
    
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    
    public Reader()
    {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
    
    public Reader(String file_name) throws IOException
    {
        din = new DataInputStream(new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }
    
    public String readLine() throws IOException
    {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while( (c=read()) != -1) {
            buf[cnt++] = (byte)c;
            if(c == '\n') break;
        }
        return new String(buf,0,cnt);
    }
    
    public int nextInt() throws IOException
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }
    
    public long nextLong() throws IOException
    {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');
        if (neg) return -ret;
        return ret;
    }
    
    public double nextDouble() throws IOException
    {
        double ret = 0, div = 1;
        byte c = read();
        while(c <= ' ') c = read();
        boolean neg = c == '-';
        if(neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c >= '0' && c <= '9');
        if(c == '.')
            while((c=read()) >= '0' && c <= '9') {
                div *= 10;
                ret = ret + (c - '0') / div;
            }
        if (neg) return -ret;
        return ret;
    }
    
    private void fillBuffer() throws IOException
    {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1;
    }
    
    private byte read() throws IOException
    {
        if (bufferPointer == bytesRead) fillBuffer();
        return buffer[bufferPointer++];
    }
    
    public void close() throws IOException
    {
        if(din == null) return;
        din.close();
    }
}

