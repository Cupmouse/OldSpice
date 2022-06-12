import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;


public class AquesTalk2 {
	public interface AquesTalkLib extends Library {
		public static final String JNA_LIBRARY_NAME = "C:/Users/nozo/Dropbox/Spice/AquesTalkJNI/nativelib/AquesTalk2.dll";
		public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(AquesTalkLib.JNA_LIBRARY_NAME);
		public static final AquesTalkLib INSTANCE = (AquesTalkLib)Native.loadLibrary(AquesTalkLib.JNA_LIBRARY_NAME, AquesTalkLib.class);
		/**
		 * !	@return	WAV\u30d5\u30a9\u30fc\u30de\u30c3\u30c8\u306e\u97f3\u58f0\u30c7\u30fc\u30bf\u306e\u5148\u982d\u30a2\u30c9\u30ec\u30b9\u3002\u30a8\u30e9\u30fc\u6642\u306fNULL\u304c\u8fd4\u308b<br>
		 * Original signature : <code>char* AquesTalk2_Synthe(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 44</i><br>
		 * @deprecated use the safer methods {@link #AquesTalk2_Synthe(java.lang.String, int, java.nio.IntBuffer, com.sun.jna.Pointer)} and {@link #AquesTalk2_Synthe(com.sun.jna.Pointer, int, com.sun.jna.ptr.IntByReference, com.sun.jna.Pointer)} instead
		 */
		@Deprecated 
		Pointer AquesTalk2_Synthe(Pointer koe, int iSpeed, IntByReference pSize, Pointer phontDat);
		/**
		 * !	@return	WAV\u30d5\u30a9\u30fc\u30de\u30c3\u30c8\u306e\u97f3\u58f0\u30c7\u30fc\u30bf\u306e\u5148\u982d\u30a2\u30c9\u30ec\u30b9\u3002\u30a8\u30e9\u30fc\u6642\u306fNULL\u304c\u8fd4\u308b<br>
		 * Original signature : <code>char* AquesTalk2_Synthe(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 44</i>
		 */
		Pointer AquesTalk2_Synthe(String koe, int iSpeed, IntBuffer pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08EUC NULL\u7d42\u7aef\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Euc(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 46</i><br>
		 * @deprecated use the safer methods {@link #AquesTalk2_Synthe_Euc(java.lang.String, int, java.nio.IntBuffer, com.sun.jna.Pointer)} and {@link #AquesTalk2_Synthe_Euc(com.sun.jna.Pointer, int, com.sun.jna.ptr.IntByReference, com.sun.jna.Pointer)} instead
		 */
		@Deprecated 
		Pointer AquesTalk2_Synthe_Euc(Pointer koe, int iSpeed, IntByReference pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08EUC NULL\u7d42\u7aef\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Euc(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 46</i>
		 */
		Pointer AquesTalk2_Synthe_Euc(String koe, int iSpeed, IntBuffer pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08UTF8 NULL\u7d42\u7aef BOM\u306f\u3064\u3051\u3089\u308c\u306a\u3044\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Utf8(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 48</i><br>
		 * @deprecated use the safer methods {@link #AquesTalk2_Synthe_Utf8(java.lang.String, int, java.nio.IntBuffer, com.sun.jna.Pointer)} and {@link #AquesTalk2_Synthe_Utf8(com.sun.jna.Pointer, int, com.sun.jna.ptr.IntByReference, com.sun.jna.Pointer)} instead
		 */
		@Deprecated 
		Pointer AquesTalk2_Synthe_Utf8(Pointer koe, int iSpeed, IntByReference pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08UTF8 NULL\u7d42\u7aef BOM\u306f\u3064\u3051\u3089\u308c\u306a\u3044\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Utf8(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 48</i>
		 */
		Pointer AquesTalk2_Synthe_Utf8(String koe, int iSpeed, IntBuffer pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08UTF16 NULL\u7d42\u7aef BOM\u306e\u6709\u7121\u306f\u554f\u308f\u306a\u3044\u3000\u30a8\u30f3\u30c7\u30a3\u30a2\u30f3\u306f\u5b9f\u884c\u74b0\u5883\u306b\u5f93\u3046\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Utf16(const unsigned short*, int, int*, void*)</code><br>
		 * <i>native declaration : line 50</i><br>
		 * @deprecated use the safer methods {@link #AquesTalk2_Synthe_Utf16(short[], int, java.nio.IntBuffer, com.sun.jna.Pointer)} and {@link #AquesTalk2_Synthe_Utf16(com.sun.jna.ptr.ShortByReference, int, com.sun.jna.ptr.IntByReference, com.sun.jna.Pointer)} instead
		 */
		@Deprecated 
		Pointer AquesTalk2_Synthe_Utf16(ShortByReference koe, int iSpeed, IntByReference pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08UTF16 NULL\u7d42\u7aef BOM\u306e\u6709\u7121\u306f\u554f\u308f\u306a\u3044\u3000\u30a8\u30f3\u30c7\u30a3\u30a2\u30f3\u306f\u5b9f\u884c\u74b0\u5883\u306b\u5f93\u3046\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Utf16(const unsigned short*, int, int*, void*)</code><br>
		 * <i>native declaration : line 50</i>
		 */
		Pointer AquesTalk2_Synthe_Utf16(short koe[], int iSpeed, IntBuffer pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08\u30ed\u30fc\u30de\u5b57\u8868\u8a18 NULL\u7d42\u7aef\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Roman(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 52</i><br>
		 * @deprecated use the safer methods {@link #AquesTalk2_Synthe_Roman(java.lang.String, int, java.nio.IntBuffer, com.sun.jna.Pointer)} and {@link #AquesTalk2_Synthe_Roman(com.sun.jna.Pointer, int, com.sun.jna.ptr.IntByReference, com.sun.jna.Pointer)} instead
		 */
		@Deprecated 
		Pointer AquesTalk2_Synthe_Roman(Pointer koe, int iSpeed, IntByReference pSize, Pointer phontDat);
		/**
		 * ! @param	koe[in]		\u97f3\u58f0\u8a18\u53f7\u5217\uff08\u30ed\u30fc\u30de\u5b57\u8868\u8a18 NULL\u7d42\u7aef\uff09<br>
		 * Original signature : <code>char* AquesTalk2_Synthe_Roman(const char*, int, int*, void*)</code><br>
		 * <i>native declaration : line 52</i>
		 */
		Pointer AquesTalk2_Synthe_Roman(String koe, int iSpeed, IntBuffer pSize, Pointer phontDat);
		/**
		 * !	@param  wav[in]		AquesTalk_Synthe()\u3067\u8fd4\u3055\u308c\u305f\u30a2\u30c9\u30ec\u30b9\u3092\u6307\u5b9a<br>
		 * Original signature : <code>void AquesTalk2_FreeWave(unsigned char*)</code><br>
		 * <i>native declaration : line 57</i><br>
		 * @deprecated use the safer methods {@link #AquesTalk2_FreeWave(java.nio.ByteBuffer)} and {@link #AquesTalk2_FreeWave(com.sun.jna.Pointer)} instead
		 */
		@Deprecated 
		void AquesTalk2_FreeWave(Pointer wav);
		/**
		 * !	@param  wav[in]		AquesTalk_Synthe()\u3067\u8fd4\u3055\u308c\u305f\u30a2\u30c9\u30ec\u30b9\u3092\u6307\u5b9a<br>
		 * Original signature : <code>void AquesTalk2_FreeWave(unsigned char*)</code><br>
		 * <i>native declaration : line 57</i>
		 */
		void AquesTalk2_FreeWave(ByteBuffer wav);
	}
	
	public static void main(String[] args) {
		IntBuffer pSize = IntBuffer.wrap(new int[100]);
		System.loadLibrary("AquesTalk2");
		AquesTalkLib.INSTANCE.AquesTalk2_Synthe_Utf8("あ", 100, pSize, null);
	}
}
