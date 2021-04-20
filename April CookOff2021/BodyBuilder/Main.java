package BodyBuilder;

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
	public static void merge(int a[],int b[],int l,int m,int r){
		int n1=m-l+1;
		int n2=r-m;
		int L[]=new int[n1];
		int R[]=new int[n2];
		int L1[]=new int[n1];
		int R1[]=new int[n2];
		for (int i = 0; i < n1; ++i){
            L[i] = a[l + i];
			L1[i]=b[l+i];
		}
        for (int j = 0; j < n2; ++j){
            R[j] = a[m + 1 + j];
			R1[j]=b[m+1+j];
		}
		int i=0,j=0;
		int k=l;
		while(i<n1 && j<n2){
			if(L[i]<=R[j]){
				a[k]=L[i];
				b[k]=L1[i];i++;
			}
			else{
				a[k]=R[j];
				b[k]=R1[j];j++;
			}
			k++;
		}
		while(i<n1){
			a[k]=L[i];
			b[k]=L1[i];
			i++;
			k++;
		}
		while(j<n2){
			a[k]=R[j];
			b[k]=R1[j];
			j++;
			k++;
		}
	}
	public static void mergeSort(int a[],int b[],int l,int r){
		if(l>=r)
			return;
		int mid=l+(r-l)/2;
		mergeSort(a, b, l, mid);
		mergeSort(a, b, mid+1, r);
		merge(a,b,l,mid,r);
	}
	public static void print(int a[],int n){
		for(int i=0;i<n;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BodyBuilder/input.txt"));
        Reader sc=new Reader();
        int t=sc.nextInt();
		while(t-- >0){
			int n=sc.nextInt();
			int r=sc.nextInt();
			long time[]=new long[n+1];
			long tension[]=new long[n+1];
			for(int i=1;i<=n;i++){
				time[i]=sc.nextLong();
			}
			for(int i=1;i<=n;i++){
				tension[i]=sc.nextLong();
			}
			long maxSofar=-1;
			long sum=0;
			// print(time,n+1);
			// print(tension,n+1);
			sum=tension[1];
			maxSofar=sum;
			for(int i=2;i<=n;i++){
				long diff=(time[i]-time[i-1])*r;
				sum-=diff;
				if(sum<0){
					sum=0;
				}
				sum+=tension[i];
				if(maxSofar<sum){
					maxSofar=sum;
				}
			}
			System.out.println(maxSofar);
		}
    }
}