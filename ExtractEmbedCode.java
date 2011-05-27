import java.util.regex.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

class ExtractEmbedCode {
  public static void main(String[] args) {
    final HashMap<String, String> h = new HashMap<String, String>();
    final Pattern regex = Pattern.compile("embedCode=([\\w_]+)[\\&|\\?|\\$]",
Pattern.CASE_INSENSITIVE);
    String l  = null;
    Matcher m = null;


    System.out.println(h.size());

    // Loop over the arguments and open each file. Then run the
    // regexp against each line and save in a hash the matches
    for (int i=0; i<args.length; i++) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(args[i]));  
        while ((l = br.readLine()) != null) {  
          m = regex.matcher(l);
          if (m.find()) {
            h.put(m.group(1), null);
//            System.out.println(m.group(1));
          }
        } 
        br.close();
      } 
      catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
      }
    }

    System.out.println("RESULT");

    // Print results
    for (Iterator it = h.keySet().iterator(); it.hasNext();) {
      Object key = it.next();
      System.out.println(key.toString());
    }

    System.exit(0);
  }
}
