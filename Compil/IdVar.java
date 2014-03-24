package Compil;

public class IdVar extends IdVal {
	public static int lastOffset;
	
	
	public IdVar(int t, int offset)
	{
		super(t, offset);
	}
	
	public static IdVar newIdVarParam(int t) {
		return new IdVar(t, lastOffset+=2);
	}
	
	public static IdVar newIdVarLocal(int t) {
		return new IdVar(t, lastOffset-=2);
	}
	
	public boolean estVar()
	{
		return true;
	}

	public String toString(){
		return "IdVar type :" + type + "Offset :" + valOuOffset + " LastOffset :" + lastOffset;
	}

	public String toYVM() {
		String res = "iload "+ valOuOffset;
		return res;
	}
	
	public String toYVMAsm() {
		return "; iload " + valOuOffset + "\npush word ptr[bp" + valOuOffset + "]";
	}
	
	public static void resetOffsets()
	{
		lastOffset = 0;
	}
	
}
