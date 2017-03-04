import java.io.*;
import java.util.Arrays;

public class Main {
  private static Reader in;
  private static PrintWriter out;
  public static void main(String[] args) throws IOException {
     in = new Reader();
    out = new PrintWriter(System.out, true);
    int T = in.nextInt();
    int stalls[] = null;
    while (T-- > 0) {
      int N = in.nextInt() ;
      int cows = in.nextInt();
      stalls = new int[N];
      for (int i = 0; i < N; i++) {
        stalls[i] = in.nextInt();
      }

      out.println(getMostWork(stalls, cows));
    }
  }

  public static long getMostWork(int stalls[], int cows) {
    Arrays.sort(stalls);
    int n = stalls.length;
    int max = -1;
    int lo = 0, hi = stalls[n - 1] - stalls[0], mid;
    while (lo < hi) {
      mid = lo + (hi-lo+1)/2;
      
	
      if (find(stalls, cows, mid))
	{
		
		lo = mid;	
	}
      else hi = mid-1;
    }
	
    return lo;
   
   

  }

  private static boolean find(int[] stalls, long cows, long mid) {
    long assigned = 1;
    
    long lastpos = stalls[0];
    //first cow is assigned to first stall
    for (int i = 1; i < stalls.length; i++) {
	if(stalls[i]-lastpos >= mid)
	{
		assigned++;
		if(assigned == cows)
		{
			return true;
		}
		lastpos = stalls[i];
	}
    
  }

return false;

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
