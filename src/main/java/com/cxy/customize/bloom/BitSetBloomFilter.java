package com.cxy.customize.bloom;



import com.cxy.customize.core.util.HashMethodsUtil;
import java.io.*;
import java.util.BitSet;

/**
 * 此方式使用BitSet存储。<br>
 * Hash算法定死了顺序，只需指定个数既可(4表示使用前5个算法)
 */
public class BitSetBloomFilter implements BloomFilter{
	private BitSet bitSet;
	private int bitSetSize;
	private int addedElements;
	private int hashFunctionNumber;

	/**
	 * 构造一个布隆过滤器，过滤器的最大容量为c * n 个bit.
	 * 
	 * @param c 当前过滤器预先开辟的最大包含记录,通常要比预计存入的记录多一倍.
	 * @param n 当前过滤器预计所要包含的记录.
	 * @param k 哈希函数的个数，等同每条记录要占用的bit数.
	 */
	public BitSetBloomFilter(int c, int n, int k) {
		this.hashFunctionNumber = k;
		this.bitSetSize = (int) Math.ceil(c * k);
		this.addedElements = n;
		this.bitSet = new BitSet(this.bitSetSize);
	}

	/**
	 * 通过文件初始化过滤器.
	 * 
	 * @param path 文件path
	 * @param charset 字符集
	 * @throws IOException IO异常
	 */
	public void init(String path, String charset) throws IOException {
		File file = new File(path);
		try(
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fileInputStream);
			BufferedReader reader = new BufferedReader(isr);){
			String nextLine;
			while(true) {
				nextLine = reader.readLine();
				if(nextLine == null) {
					break;
				}
				this.add(nextLine);
			}
		}catch (IOException e){
			e.printStackTrace();
		}

	}
	
	@Override
	public boolean add(String str) {
		//存在不添加了
		if (contains(str)) {
			return false;
		}
		int[] positions = createHashes(str, hashFunctionNumber);
		for (int i = 0; i < positions.length; i++) {
			int position = Math.abs(positions[i] % bitSetSize);
			bitSet.set(position, true);
		}
		return true;
	}
	
	/**
	 * 判定是否包含指定字符串
	 * @param str 字符串
	 * @return 是否包含，存在误差
	 */
	@Override
	public boolean contains(String str) {
		int[] positions = createHashes(str, hashFunctionNumber);
		for (int i : positions) {
			int position = Math.abs(i % bitSetSize);
			//没找到，肯定不在bitSet里
			if (!bitSet.get(position)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @return 得到当前过滤器的错误率.todo
	 */
	public double getFalsePositiveProbability() {
		// (1 - e^(-k * n / m)) ^ k
		return Math.pow((1 - Math.exp(-hashFunctionNumber * (double) addedElements / bitSetSize)), hashFunctionNumber);
	}

	/**
	 * 将字符串的字节表示进行多哈希编码.
	 * 
	 * @param str 待添加进过滤器的字符串字节表示.
	 * @param hashNumber 要经过的哈希个数.
	 * @return 各个哈希的结果数组.
	 */
	public static int[] createHashes(String str, int hashNumber) {
		int[] result = new int[hashNumber];
		for(int i = 0; i < hashNumber; i++) {
			result[i] = hash(str, i);
		}
		return result;
	}

	/**
	 * 计算Hash值
	 * @param str 被计算Hash的字符串
	 * @param k Hash算法序号
	 * @return Hash值
	 */
	public static int hash(String str, int k) {
		switch (k) {
			case 0:
				return HashMethodsUtil.rsHash(str);
			case 1:
				return HashMethodsUtil.jsHash(str);
			case 2:
				return HashMethodsUtil.elfHash(str);
			case 3:
				return HashMethodsUtil.bkdrHash(str);
			case 4:
				return HashMethodsUtil.apHash(str);
			case 5:
				return HashMethodsUtil.djbHash(str);
			case 6:
				return HashMethodsUtil.sdbmHash(str);
			case 7:
				return HashMethodsUtil.pjwHash(str);
			default:
				return 0;
		}
	}
}