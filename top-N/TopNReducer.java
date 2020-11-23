import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class TopNReducer extends Reducer<LongWritable, Text, LongWritable, Text> {

	static int topN;

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();

    // If no value set for N, default to 5
    topN = conf.getInt("N", 5);
	}

	@Override
	public void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		long count = (-1) * key.get();
		String filename = null;

		for (Text val : values)
		{
			filename = val.toString();
		}

    if (topN > 0 && !filename.isEmpty()){
      context.write(new LongWritable(count), new Text(filename));
      topN--;
    }
	}
}
