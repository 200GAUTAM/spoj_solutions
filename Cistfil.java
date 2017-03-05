import java.io.*;
import java.util.Arrays;

public class Main {
  private static Reader in;
  private static PrintWriter out;
  
  public static void main(String[] args) throws IOException {
     in = new Reader();
    out = new PrintWriter(System.out, true);
    int T = in.nextInt();
     Cubiod cr[] = null;
	double low, high, volume, totalVolume;
    while (T-- > 0) {
      int N = in.nextInt() ;
      low  = 100000000;
      high = -100000000;
      totalVolume =0;
      int base;
      int height;
      int length;
      int breadth;
      cr = new Cubiod[N];
      for (int i = 0; i < N; i++) {
        base = in.nextInt();
	height = in.nextInt();
	length = in.nextInt();
	breadth = in.nextInt();
	cr[i] = new Cubiod(base, height, length*breadth);
	if(high< base+height)
	{
		high = base+height;
	} 
	if(low> base)
	{
		low = base;
	}
	totalVolume += cr[i].height*cr[i].area;
      }
	volume = in.nextInt();
	if(volume> totalVolume)
	{
		out.println("OVERFLOW");
	}

       else{
		b_seearch(cr, low, high, volume);
	}
    }
  }

  public static void b_seearch(Cubiod[] cr, double low, double high, double volume)
  {
	double mid, v;
	while(high- low > 1e-3)
	{
		mid = (low + high)/2;
		v = 0;
		for(int i = 0; i<cr.length; i++)
		{
			if(cr[i].base< mid)
			{
				if(mid >= cr[i].height+cr[i].base)
				{
					v += cr[i].height*cr[i].area;
				}else{
					v += (mid-cr[i].base)*cr[i].area;					
				}
			}
			if(v<volume)
			{
				low = mid;
			}else 
		{
high = mid;
}
		}
	}
		low = Math.round(low * 100);
		out.println(low/100);

  }

  

  
}

class Cubiod{
	int base;	
	int height; 
	long area;
	
	Cubiod(int a, int b, long c)
	{
		base 	=  a;
		height  =  b;
		area    =  c;
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
