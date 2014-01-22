package naftoreiclag.blocksmith.vector;

import java.util.BitSet;

public class Iitexture
{
	private byte[] paletteChoices = new byte[15];
	private byte[] byteMap = new byte[256];
	
	public Iitexture()
	{
		for(int i = 0; i < 256; i ++)
		{
			byteMap[i] = 0;
		}
		
		for(int i = 0; i < 15; i ++)
		{
			paletteChoices[i] = (byte) i;
		}
	}
	
	public byte[] getData()
	{
		byte[] returnVal = new byte[128 + 15];
		
		for(int i = 0; i < 15; i ++)
		{
			returnVal[i] = paletteChoices[i];
		}
		
		for(int i = 0; i < 128; i ++)
		{
			returnVal[i + 15] = byteMap[i * 2];
			returnVal[i + 15] |= byteMap[(i * 2) + 1] << 4;
		}
		
		return returnVal;
	}
	
	public void setPixel(int x, int y, byte c)
	{
		byteMap[x + (y * 16)] = c;
	}
	
	// Make this return whether this data is exactly the same
	public boolean compare(byte[] data)
	{
		return getData().equals(data);
	}
	
	public static byte[] toByteArray(BitSet bits) {
	    byte[] bytes = new byte[bits.length()/8+1];
	    for (int i=0; i<bits.length(); i++) {
	        if (bits.get(i)) {
	            bytes[bytes.length-i/8-1] |= 1<<(i%8);
	        }
	    }
	    return bytes;
	}
}
