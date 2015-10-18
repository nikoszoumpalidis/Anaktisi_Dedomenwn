


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.FieldInfo;



/**
 * This terminal application creates an Apache Lucene index in a folder and adds files into this index
 * based on the input of the user.
 */
public class Directory {

  public static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);

  public IndexWriter writer;
  static String indexLocation;
  static Directory indexer;
  public static ArrayList<File> queue = new ArrayList<File>();
  public static  IndexReader reader;
  public static IndexSearcher searcher;
  public static TopScoreDocCollector collector;
  public static FSDirectory dir;
  public static Document doc;

  public static void main() throws IOException {
    String s;
    String s1;
    String s2;
    String s3;

    indexLocation = null;
    
        s ="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi";
    
  //  indexer = null;
    try {
    //  indexLocation = s;
     // indexer = new Directory(s);

    } catch (Exception ex) {
      System.exit(-1);
    }

    
    
      try {
        
        //s ="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Business";
        //s1="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\User";
        //s2="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Review";
        //s3="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\CheckIn";
        
      //  indexer.indexFileOrDirectory(s);
      //  indexer.indexFileOrDirectory(s1);
      //  indexer.indexFileOrDirectory(s2);
      //  indexer.indexFileOrDirectory(s3);
      } catch (Exception e) {
        System.out.println("Error indexing " + s + " : " + e.getMessage());
      }
    

    //===================================================
    //after adding, we always have to call the
    //closeIndex, otherwise the index is not created
    //===================================================
    //indexer.closeIndex();

    

  }

  
  Directory(String indexDir) throws IOException {
    
    dir = FSDirectory.open(new File(indexDir));
    IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
    writer = new IndexWriter(dir, config);

  }

  /**
   * Indexes a file or directory
   * @param fileName the name of a text file or a folder we wish to add to the index
   * @throws java.io.IOException when exception
   */
  public void indexFileOrDirectory(String fileName) throws IOException {
    
    addFiles(new File(fileName));
    /***********************/
    FieldType type = new FieldType();
    type.setIndexed(true);
    type.setIndexOptions(FieldInfo.IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
    type.setStored(true);
    type.setStoreTermVectors(true);
    type.setTokenized(true);
    type.setStoreTermVectorOffsets(true);
    /*****************/
    int originalNumDocs = writer.numDocs();
    for (File f : queue) {
      FileReader fr = null;
      try {
        doc = new Document();

        fr = new FileReader(f);
        
        doc.add(new TextField("contents", fr));
        
       
        doc.add(new StringField("path", f.getPath(), Field.Store.YES));
        doc.add(new StringField("filename", f.getName(), Field.Store.YES));
       
        //doc.add(field);
        //doc.add(ff);
        writer.addDocument(doc);
       
      } catch (Exception e) {
        System.out.println("Could not add: " + f);
      } finally {
        fr.close();
      }
    }
    
   
     /***/
    /********************************/
    
    int newNumDocs = writer.numDocs();

    queue.clear();
  }

  private void addFiles(File file) {

    if (!file.exists()) {
      System.out.println(file + " does not exist.");
    }
    if (file.isDirectory()) {
      for (File f : file.listFiles()) {
        addFiles(f);
      }
    } else {
      String filename = file.getName().toLowerCase();
      //===================================================
      // Only index text files
      //===================================================
      if (filename.endsWith(".htm") || filename.endsWith(".html") ||
              filename.endsWith(".xml") || filename.endsWith(".txt")) {
        queue.add(file);
      } else {
        System.out.println("Skipped " + filename);
      }
    }
  }

  /**
   * Close the index.
   * @throws java.io.IOException when exception closing
   */
  public void closeIndex() throws IOException {
    writer.close();
  }
}