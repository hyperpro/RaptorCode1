
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;


public class Write {
	
	public void WriteCalculate(byte H[][], long Size, int SYMBOL_SIZE, int K)
	{
		
		try {
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(new File("/home/zhangxu/下载/test2.mp3"), true));			
			for (int i=0; i< K-1; i++)
			{
				out.write(H[i], 0, SYMBOL_SIZE);
				
			}
			int temp = (int) (Size-SYMBOL_SIZE*(K-1));
			out.write(Arrays.copyOfRange(H[K-1], 0, temp));
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

// Math.floor(Size/(K*SYMBOL_SIZE)