package com.Butter.WordSort;

import org.apache.hadoop.io.LongWritable;

/**
 * 比较类
 * @author butter
 *
 */
public class LongComparator extends LongWritable.Comparator{

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// TODO Auto-generated method stub
		return -super.compare(b1, s1, l1, b2, s2, l2);
	}

	@Override
	public int compare(Object a, Object b) {
		// TODO Auto-generated method stub
		return -super.compare(a, b);
	}
	
}
