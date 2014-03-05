import java.io.IOException;
import java.io.RandomAccessFile;


public class SendPacket {
	public int blockSize, N, fileSize;
	public RandomAccessFile in;
	public byte[][] V;
	public byte[][] E;
	public int counter = 0;
	public SendPacket(int BLOCK_SIZE, RandomAccessFile in) {
		this.blockSize = BLOCK_SIZE;
		try {
			this.fileSize = (int) in.length();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.N = (int) Math.ceil((double) this.fileSize / (double) this.blockSize);
		this.in = in;
		this.counter = 0;
		this.V = new byte[N][blockSize];
		for (int i=0;i<N;i++)
			for (int j=0;j<blockSize;j++)
				V[i][j] = 0;
	}
	public int EncodePacket(){

		try {
			for (int i=0;i<N;i++) in.read(V[i]);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Function Func=new Function();
		int X=1;
		while (X*(X-1)<=2*N)
			  X=X+1;
		int S=1;
		while (S<Math.ceil(0.01*N)+X)
			S=S+1;
		while (Func.Isp(S)==0)
			S++;
		int H=1;
		while (Func.Factor(H) / ((Func.Factor(Math.ceil(H/2)))* Func.Factor((H-Math.ceil(H/2)))) <N+S)
			H=H+1;
		int L=0;
		L=N+S+H;
		int Lp=0;
		Lp=L;
		while (Func.Isp(Lp)==0)
			Lp++;
		int Pr=N+(N/4);
		pps pps1=new pps();
		byte A[][]=pps1.ppscalculate(N, H, L, Lp, S, Pr);
		Triple Triple1=new Triple();
		int[][] U=Triple1.TripleCalculate(N, Lp, Pr);
		
		this.E = new byte[Pr][blockSize];
		Encoder Encoder1=new Encoder();
		E=Encoder1.EncoderCalculate(N, Lp, A, V, H, S, blockSize, Pr, U, 1);
		return 0;
	}
	public PacketStruct SendMessage(){
		this.counter++;
		PacketStruct packet = new PacketStruct(E[counter], counter);
		return packet;
	}
}
