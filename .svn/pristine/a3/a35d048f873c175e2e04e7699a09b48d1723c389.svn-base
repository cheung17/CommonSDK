package com.commonsdk.util;

import com.commonsdk.string.StringUtils;

import java.util.Random;

/**
 * Random Utils 随机数生成
 * 
 * 有如下方法
 * 
 * <ul>
 * Shuffling algorithm
 * <li>{@link #shuffle(Object[])} Shuffling algorithm, Randomly permutes the
 * specified array using a default source of randomness</li>
 * <li>{@link #shuffle(Object[], int)} Shuffling algorithm, Randomly permutes
 * the specified array</li>
 * <li>{@link #shuffle(int[])} Shuffling algorithm, Randomly permutes the
 * specified int array using a default source of randomness</li>
 * <li>{@link #shuffle(int[], int)} Shuffling algorithm, Randomly permutes the
 * specified int array</li>
 * </ul>
 * <ul>
 * get random int
 * <li>{@link #getRandom(int)} get random int between 0 and max</li>
 * <li>{@link #getRandom(int, int)} get random int between min and max</li>
 * </ul>
 * <ul>
 * get random numbers or letters
 * <li>{@link #getRandomCapitalLetters(int)} get a fixed-length random string,
 * its a mixture of uppercase letters</li>
 * <li>{@link #getRandomLetters(int)} get a fixed-length random string, its a
 * mixture of uppercase and lowercase letters</li>
 * <li>{@link #getRandomLowerCaseLetters(int)} get a fixed-length random string,
 * its a mixture of lowercase letters</li>
 * <li>{@link #getRandomNumbers(int)} get a fixed-length random string, its a
 * mixture of numbers</li>
 * <li>{@link #getRandomNumbersAndLetters(int)} get a fixed-length random
 * string, its a mixture of uppercase, lowercase letters and numbers</li>
 * <li>{@link #getRandom(String, int)} get a fixed-length random string, its a
 * mixture of chars in source</li>
 * <li>{@link #getRandom(char[], int)} get a fixed-length random string, its a
 * mixture of chars in sourceChar</li>
 * </ul>
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-5-12
 */
public class RandomUtils {

	public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERS = "0123456789";
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * get a fixed-length random string, its a mixture of uppercase, lowercase
	 * letters and numbers 得到一个指定长度的随机字符串，它包含大小写字母和数字
	 * 
	 * @param length
	 *            指定长度
	 * @return 随机字符串
	 * @see RandomUtils#getRandom(String source, int length)
	 */
	public static String getRandomNumbersAndLetters(int length) {
		return getRandom(NUMBERS_AND_LETTERS, length);
	}

	/**
	 * get a fixed-length random string, its a mixture of numbers
	 * 得到一个指定长度的字符串，只由数字组成
	 * 
	 * @param length
	 *            指定长度
	 * @return
	 * @see RandomUtils#getRandom(String source, int length)
	 */
	public static String getRandomNumbers(int length) {
		return getRandom(NUMBERS, length);
	}

	/**
	 * get a fixed-length random string, its a mixture of uppercase and
	 * lowercase letters 得到一个指定长度的随机字符串，由大小写字母组成
	 * 
	 * @param length
	 *            指定长度
	 * @return
	 * @see RandomUtils#getRandom(String source, int length)
	 */
	public static String getRandomLetters(int length) {
		return getRandom(LETTERS, length);
	}

	/**
	 * get a fixed-length random string, its a mixture of uppercase letters
	 * 得到一个随机字符串，由大写字母组成
	 * 
	 * @param length
	 *            指定长度
	 * @return
	 * @see RandomUtils#getRandom(String source, int length)
	 */
	public static String getRandomCapitalLetters(int length) {
		return getRandom(CAPITAL_LETTERS, length);
	}

	/**
	 * get a fixed-length random string, its a mixture of lowercase letters
	 * 得到一个随机字符串，由小写字母组成
	 * 
	 * @param length
	 *            指定长度
	 * @return
	 * @see RandomUtils#getRandom(String source, int length)
	 */
	public static String getRandomLowerCaseLetters(int length) {
		return getRandom(LOWER_CASE_LETTERS, length);
	}

	/**
	 * get a fixed-length random string, its a mixture of chars in source
	 * 得到一个指定长度的字符串，由第一个参数的字符串组成
	 * 
	 * @param source
	 *            组成字符，string类型
	 * @param length
	 *            指定长度
	 * @return <ul>
	 *         <li>if source is null or empty, return null</li>
	 *         如果第一个参数为null或empty返回null
	 *         <li>else see
	 *         {@link RandomUtils#getRandom(char[] sourceChar, int length)}</li>
	 *         </ul>
	 * 
	 */
	public static String getRandom(String source, int length) {
		return StringUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
	}

	/**
	 * get a fixed-length random string, its a mixture of chars in sourceChar
	 * 得到一个指定长度的字符串，由第一个参数的字符串组成
	 * 
	 * @param sourceChar
	 *            组成字符，char[]数组
	 * @param length
	 *            指定长度
	 * @return <ul>
	 *         <li>if sourceChar is null or empty, return null</li>
	 *         如果第一个参数为null或empty返回null
	 *         <li>if length less than 0, return null</li>
	 *         </ul>
	 */
	public static String getRandom(char[] sourceChar, int length) {
		if (sourceChar == null || sourceChar.length == 0 || length < 0) {
			return null;
		}

		StringBuilder str = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str.append(sourceChar[random.nextInt(sourceChar.length)]);
		}
		return str.toString();
	}

	/**
	 * get random int between 0 and max 得到一个随机 数，在0-max之间
	 * 
	 * @param max
	 *            最大值
	 * @return <ul>
	 *         <li>if max <= 0, return 0</li>
	 *         <li>else return random int between 0 and max</li>
	 *         </ul>
	 */
	public static int getRandom(int max) {
		return getRandom(0, max);
	}

	/**
	 * get random int between min and max 得到一个随机数，在min-max之间
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return <ul>
	 *         <li>if min > max, return 0</li>
	 *         <li>if min == max, return min</li>
	 *         <li>else return random int between min and max</li>
	 *         </ul>
	 */
	public static int getRandom(int min, int max) {
		if (min > max) {
			return 0;
		}
		if (min == max) {
			return min;
		}
		return min + new Random().nextInt(max - min);
	}

	/**
	 * Shuffling algorithm, Randomly permutes the specified array using a
	 * default source of randomness
	 * 
	 * @param objArray
	 * @return
	 */
	public static boolean shuffle(Object[] objArray) {
		if (objArray == null) {
			return false;
		}

		return shuffle(objArray, getRandom(objArray.length));
	}

	/**
	 * Shuffling algorithm, Randomly permutes the specified array
	 * 
	 * @param objArray
	 * @param shuffleCount
	 * @return
	 */
	public static boolean shuffle(Object[] objArray, int shuffleCount) {
		int length;
		if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
			return false;
		}

		for (int i = 1; i <= shuffleCount; i++) {
			int random = getRandom(length - i);
			Object temp = objArray[length - i];
			objArray[length - i] = objArray[random];
			objArray[random] = temp;
		}
		return true;
	}

	/**
	 * Shuffling algorithm, Randomly permutes the specified int array using a
	 * default source of randomness
	 * 
	 * @param intArray
	 * @return
	 */
	public static int[] shuffle(int[] intArray) {
		if (intArray == null) {
			return null;
		}

		return shuffle(intArray, getRandom(intArray.length));
	}

	/**
	 * Shuffling algorithm, Randomly permutes the specified int array
	 * 
	 * @param intArray
	 * @param shuffleCount
	 * @return
	 */
	public static int[] shuffle(int[] intArray, int shuffleCount) {
		int length;
		if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
			return null;
		}

		int[] out = new int[shuffleCount];
		for (int i = 1; i <= shuffleCount; i++) {
			int random = getRandom(length - i);
			out[i - 1] = intArray[random];
			int temp = intArray[length - i];
			intArray[length - i] = intArray[random];
			intArray[random] = temp;
		}
		return out;
	}
}
