

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	public static int orand(int arr[],int n){
		int or=arr[0],and=arr[0];
		// System.out.println("Or is "+or);
		// System.out.println("And is "+and);
		for(int i=1;i<n;i++){
			
			or=or|arr[i];
			// System.out.println("Or is "+or);
			and=and&arr[i];
			// System.out.println("And is "+and);
		}
		return or|and;
	}
	public static boolean checkBit(int x,int i){
		if(((x>>i)&1)==1)
			return true;
		return false;
	}
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("ORAnd/input.txt"));
        Reader sc=new Reader();
        int t=sc.nextInt();
		while(t-- >0){
			int n=sc.nextInt();
			int q=sc.nextInt();
			int index[]=new int[32];
			int arr[]=new int[n];
			int or=0;
			for(int i=0;i<n;i++){
				int x=sc.nextInt();
				arr[i]=x;
				or=or | x;
				for(int j=0;j<32;j++){
					if(checkBit(x,j)){
						index[j]++;
					}
				}
			}
			// System.out.println("Index array");
			// for(int j=0;j<32;j++){
			// 	System.out.println(j+" "+index[j]);
			// }
			System.out.println(or);
			while(q-- >0){
				int in=sc.nextInt();
				int val=sc.nextInt();
				int x=arr[in-1];
				arr[in-1]=val;
				for(int j=0;j<32;j++){
					if(checkBit(x,j)){
						index[j]--;
					}
				}
				for(int j=0;j<32;j++){
					if(checkBit(arr[in-1],j)){
						index[j]++;
					}
				}
				or=0;
				for(int j=0;j<32;j++){
					if(index[j]!=0){
						or=or | (1<<j);
					}
				}
				System.out.println(or);

			}

			

		}
    }
}