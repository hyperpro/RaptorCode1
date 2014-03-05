
public class DecodePacket {

	public int blockSize, fileSize, N, n, counter;
	public byte[][] erc;
	public int[] ESIs;
	public boolean decodable = false;

	public DecodePacket(int blockSize, int fileSize, double overHead){
		this.blockSize = blockSize;
		this.fileSize = fileSize;
		this.N = (int) Math.floor((double) fileSize/ (double) blockSize);
		this.n = (int) ((1+ overHead)* this.N);
		this.erc = new byte[n][blockSize];
		this.ESIs = new int[n];
		this.decodable = false;
		this.counter = -1;
	}
	
	public int ReceivePacket(PacketStruct packet){
		counter++;
		ESIs[counter] = packet.number;
		erc[counter] = packet.data;
		if (counter == n-1) decodable = true; 
		return 0;
	}
	
	public int Decode(){

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
		byte B[][]=pps1.ppscalculate(N, H, L, Lp, S, Pr);
		Triple Triple1=new Triple();
		int[][] U=Triple1.TripleCalculate(N, Lp, Pr);
		
		
		byte[][] original=new byte[N][blockSize];
		Decoder Decoder1=new Decoder();
		original=Decoder1.DecoderCalculate(N, Lp, B, erc, H, S, blockSize, Pr, U, ESIs, 0);
		Write Write1=new Write();
		Write1.WriteCalculate(original, fileSize, blockSize, N);
		return 0;
	}
	
}
