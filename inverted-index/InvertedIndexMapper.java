import java.io.*;
import java.util.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {
  HashMap<String, Integer> map;
  String filename;

  @Override
  public void setup(Context context) throws IOException, InterruptedException {
    map = new HashMap<String, Integer>();
    filename = ((FileSplit) context.getInputSplit()).getPath().toString();

    // The files will be stored in Storage on Google Cloud Platform, so to
    // extract the file name, we want to ignore the full address of our bucket.
    // My input files will be stored at:
    // gs://dataproc-staging-us-east1-284206552440-wehqqljx/testInvertedIndex/input/ (77 chars)
    filename = filename.substring(77);
  }

  @Override
  public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

    // Remove the punctuation from each line and split the wrods into a terms array
    String[] words = value.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

    // For each word from the line, add to the HashMap
    for (String word : words) {

      // If the word is already in the HashMap...
      if (map.containsKey(word)) {

        // Get the value associated with the word
        int newVal = map.get(word);

        // Increment the value and store it in a new variable
        newVal++;

        // Replace the old mapping with the new value
        map.replace(word, newVal);
      }
      // Otherwise, add the word to the HashMap
      else {
        map.put(word, 1);
      }
    }
  }

  @Override
  public void cleanup(Context context) throws IOException, InterruptedException{
    for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
      String word = entry.getKey();
      Integer count = entry.getValue();

      context.write(new Text(word), new Text(filename + ": " + count.toString() + ", "));
    }
  }
}
