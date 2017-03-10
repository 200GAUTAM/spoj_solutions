import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class ABA12C{
	  private static Reader in;
	  private static PrintWriter out;
	  public static void main(String[] args) throws IOException {
		  in = new Reader();
		  out = new PrintWriter(System.out, true);
		  int T = in.nextInt();
		 
		  while (T-- > 0) {
		      int N = in.nextInt() ;
		      int K = in.nextInt();
		     
		      
		     solve(N,K);
		      
	      
	    }
	  }
	  
	  public static void solve(int N, int K) throws IOException
	  {
		  int[] val = new int[K+1];
		  int[] price = new int[K];
	          for(int i = 0; i<K; i++)
		  {
			price[i] = in.nextInt() ;
		  }
		  val[0] = 0;
	          for(int i = 1; i<=K; i++)
			{
				int min = Integer.MAX_VALUE;
				for(int j=0; j<i; j++)
				{
					min = Math.min(min, price[j]+val[i-j-1]);
				}
				if(min<0)
				{
					val[i] = Integer.MIN_VALUE;
				}else{
						val[i] = min;
					}
			}
			if(val[K]>=0)
	System.out.println(val[K]);
	else
	System.out.println(-1);

	  }
	  
	  public static boolean  ok(double mid, double[] radius, int F)
	  {
		  int fr = 0;
		  if(mid == 0)
		  {
			  return false;
		  }
		  for(int i = 0; i<radius.length; i++)
		  {
			  fr+= (int)(radius[i]/mid);
		  }
		  
		  if(fr >= F+1)
		  {
			  return true;
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
