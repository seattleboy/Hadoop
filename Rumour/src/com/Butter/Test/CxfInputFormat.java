package com.Butter.Test;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.LineRecordReader;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class CxfInputFormat extends FileInputFormat<LongWritable, Text> {
	@Override
	public RecordReader<LongWritable, Text> getRecordReader(InputSplit split,
			JobConf job, Reporter reporter) throws IOException {
		return new CxfRecordReader(job, (FileSplit) split);
	}

	class CxfRecordReader implements RecordReader<LongWritable, Text> {

		private LineRecordReader in;
		private LongWritable junk = new LongWritable();
		private Text line = new Text();
		private int KEY_LENGTH = 10;

		public CxfRecordReader(JobConf job, FileSplit split) throws IOException {
			in = new LineRecordReader(job, split);
		}

		@Override
		public void close() throws IOException {
			in.close();
		}

		@Override
		public LongWritable createKey() {
			return new LongWritable();
		}

		@Override
		public Text createValue() {

			return new Text();
		}

		@Override
		public long getPos() throws IOException {

			return in.getPos();
		}

		@Override
		public float getProgress() throws IOException {

			return in.getProgress();
		}

		@Override
		public boolean next(LongWritable key, Text value) throws IOException {
			if (in.next(junk, line)) {
				if (line.getLength() < KEY_LENGTH) {
					key.set(Integer.parseInt(line.toString()));
					value.clear();
				} else {
					byte[] bytes = line.getBytes();
					key.set(Integer.parseInt(new String(bytes).substring(0,
							KEY_LENGTH)));
					value = new Text();
				}
				return true;
			} else {
				return false;
			}
		}
	}
}
