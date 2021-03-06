package hints;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * To define a map function for your MapReduce job, subclass 
 * the Mapper class and override the map method.
 * The class definition requires four parameters: 
 * @param The data type of the input key - LongWritable
 * @param The data type of the input value - Text
 * @param The data type of the output key - Text
 * @param The data type of the output value - IntWritable
*/
public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	/* 
	 * todo: Define a boolean variable indicate whether to do case sensitive processing.
	 */
	
	Boolean caseSensitive = false;
	
	@Override
	public void setup(Context context
            ) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		caseSensitive = conf.getBoolean("caseSensitive", false);
	}
	 
  /**
   * The map method runs once for each line of text in the input file.
   * The method receives:
   * @param A key of type LongWritable
   * @param A value of type Text
   * @param A Context object.
   */
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    /*
     * Convert the line, which is received as a Text object,
     * to a String object. 
     */
	  
    String line = value.toString();

    /*
     * The line.split("\\W+") call uses regular expressions to split the
     * line up by non-word characters.
     * If you are not familiar with the use of regular expressions in
     * Java code, search the web for "Java Regex Tutorial."
     */
    

    
    for (String word : line.split("\\W+")) {
      if (word.length() > 0) {

    	  /* 
    	   * TODO: check the value of the caseSensitive variable.  If false, convert
    	   * all letters to lower case.  Otherwise, leave them mixed case.  
    	   * Hint: use the String.toLowerCase() method
    	   */
    	  
        String letter = word.substring(0, 1);
        if(!caseSensitive) {
        	letter = letter.toLowerCase();
        }
        /*
         * Call the write method on the Context object to emit a key
         * and a value from the map method.  The key is the 
         * letter (in lower-case) that the word starts with; the value is the 
         * word's length.
         */
        context.write(new Text(letter), new IntWritable(word.length()));
      }
    }
  }
  
	/*
	 * @override
	 * public void setup(Context context) {
	 */
		/*
		 * TODO: Override the setup() method to check the value of the a parameter
		 * called caseSensitive and set a member variable to track whether to do
		 * case sensitive handling of letters.
		 */

	/*
	 * }
	 */
}
