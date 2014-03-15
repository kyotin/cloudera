package stubs;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Example input line:
 * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
 *
 */
public class ImageCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Pattern p = Pattern.compile("\\.[-a-zA-Z]+?\\s+HTTP");
	String imageType = "";
	
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * TODO implement
     */
	  Matcher m = p.matcher(value.toString());
	  boolean rs = m.find();
	  if(rs) {
		  imageType = m.group(1);
		  context.getCounter("ImageCounter", imageType).increment(1);
	  }
	  
	
	  
	  
	  

  }
}