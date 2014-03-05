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

import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

	public static void main(String[] args) 
	{
		int blockSize = 508;
		int fileSize = 0;
		double overHead = 0.02;
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile("/home/zhangxu/下载/test1.mp3","r");
			fileSize = (int) in.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SendPacket sender = new SendPacket(blockSize, in);
		sender.EncodePacket();
		DecodePacket decoder = new DecodePacket(blockSize, fileSize, overHead);
		while (true){ //need modify
			PacketStruct packet = sender.SendMessage();
			decoder.ReceivePacket(packet);
			if (decoder.decodable == true) break;
		}
		decoder.Decode();
		System.out.println("Successful!");
	}
}
		
			