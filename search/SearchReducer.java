import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SearchReducer extends Reducer<Text, Text, Text, Text> {

	String failString;
	static String searchTerm;

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();

	    // If no value set for N, default to 5
	    searchTerm = conf.get("searchTerm");
	    failString = "No search results for term: " + searchTerm;
	    }

	public void reduce(Text key, Text value, Context context) throws IOException, InterruptedException {
		
      if (searchTerm.equals(key.toString())) {
	  	context.write(key, new Text(value.toString()));
      }
	}
}
