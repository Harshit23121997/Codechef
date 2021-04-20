package ChefDice;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

class Main{
    static class Reader {
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
			din = new DataInputStream(
				new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					}
					else {
						continue;
					}
				}
				buf[cnt++] = (byte)c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException
		{
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0,
								BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
		}
	}
	static BigInteger four=new BigInteger("4");
	static BigInteger five=new BigInteger("5");
	static BigInteger six=new BigInteger("6");
	static BigInteger seven=new BigInteger("7");

	static BigInteger fourfour=new BigInteger("44");

	static BigInteger sixty=new BigInteger("60");
	static BigInteger sevensix=new BigInteger("76");
	static BigInteger eighteight=new BigInteger("88");
	static BigInteger ninenine=new BigInteger("99");
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("ChefDice/input.txt"));
        Reader sc=new Reader();
        int t=sc.nextInt();
        while(t-- >0){
			long n=sc.nextLong();
			
			if(n==1){
				System.out.println(20);
				continue;
			}
			else if(n==2){
				System.out.println(36);
				continue;
			}
			else if(n==3){
				System.out.println(51);
				continue;
			}
			else if(n==4){
				System.out.println(60);
				continue;
			}
			n=n-4;
			long r1=n%4+4;
			long d1=n/4;
			BigInteger ans=new BigInteger("0");
			String r2=Long.toString(r1);
			String d2=Long.toString(d1);
			BigInteger r=new BigInteger(r2);
			BigInteger d=new BigInteger(d2);
			// System.out.println("r is"+r);
			// System.out.println("d is "+d);
			d=d.multiply(fourfour);
			ans=ans.add(d);
			if(r.compareTo(four)==0){
				ans=ans.add(sixty);
			}
			else if(r.compareTo(five)==0){
				ans=ans.add(sevensix);
			}
			else if(r.compareTo(six)==0){
				ans=ans.add(eighteight);
			}
			else if(r.compareTo(seven)==0){
				ans=ans.add(ninenine);
			}
			System.out.println(ans);
		}
    }
}