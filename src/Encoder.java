
/*This file is part of Raptor Code.

    Raptor Code is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Raptor Code is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Raptor Code.  If not, see <http://www.gnu.org/licenses/>. */

/*Implemented by: Hossein Rouhani Zeidanloo
Email address:  H_Rouhani@Hotmail.com */

public class Encoder {
	
	
	public byte[][] EncoderCalculate(int K, int Lp, byte A[][], byte VV[][], int H, int S, int SYMBOL_SIZE, int Pr, int[][] U, int r)
	{
		
		
		int L=K+H+S;
		int[] d=U[0];
		int[] a=U[1];
		int[] b=U[2];
		
		Inverse Inverse1=new Inverse();
		
		byte[][] c=Inverse1.Inversecalculate(A, VV, H, K, S, SYMBOL_SIZE);
		byte[][] zz=new byte[Pr][SYMBOL_SIZE];
		
		Ln Ln1=new Ln();
		
//
//		Triple Triple1=new Triple();
//		int[][] U=Triple1.TripleCalculate(K, Lp);
//		int[] d=U[0];
//		int[] a=U[1];
//		int[] b=U[2];
		
				
		for (int i=0; i<Pr; i++)
		{
			zz[i]=Ln1.Lncalculate(K, c, d[i], a[i], b[i], L, SYMBOL_SIZE);
		}
		
		
		
		
		
		
		
		//long[] TimeEnc=new long[10];
		
		//long[] TimeEnc;
		//TimeEnc[r]=Enctime;  
		
//		if (r==9)
//		{
//			for (int i=0; i<9; i++)
//			{
//				System.out.print("TimeArray:"+ TimeEnc[i]);
//				SS=SS+TimeEnc[i];
//				System.out.print("SS:"+ SS);
//				System.out.println();
//				//System.out.print("TimeEnc:"+ TimeEnc[i]);
//			}
//			System.out.println("Total elapsed time in Encoding is :" + SS );
//		}
		
		//System.out.println("Total elapsed time in Encoding is :" + (endtime1-starttime1) );
		
		return zz;
		
		
	}
}