import java.io.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
  
  public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException
  {
    String output = "";

    for (Text value : values) {
      output += value.toString();
    }

    context.write(key, new Text(output));
  }
}
