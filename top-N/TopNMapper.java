import java.io.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class TopNMapper extends Mapper<Object, Text, LongWritable, Text> {

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String[] tokens = value.toString().split("\t");

        String term = tokens[0];
        String[] filenamesWithCount = tokens[1].split(",");
        long count = 0;

        for (String fileWithCount : filenamesWithCount){

          // If string is empty, continue
          if (fileWithCount.isEmpty()){
            continue;
          }

          String[] content = fileWithCount.split(": ");

          // If there are less than 2 arguments, then we're missing either a
          // count or a string, either way it means this "term" can be ignored
          if (content.length < 2) {
            continue;
          }

          // Parse the count from the string
          long thisCount = Long.parseLong(content[1]);

          // Add it to the total count for this word
          count += thisCount;
        }

        count *= -1;
        context.write(new LongWritable(count), new Text(term));
    }
}
