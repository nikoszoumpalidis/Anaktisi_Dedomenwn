import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader ;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import org.apache.lucene.index.IndexWriterConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.text.DefaultHighlighter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.util.Version;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.search.highlight.Highlighter;



    public class Project extends javax.swing.JFrame {
    private String s;
    public boolean prox = false;
    
    static int busLength;
    static int revLength;
    static int useLength;
    static int checLength;

    static int busCount=0;
    static int revCount=0;
    static int userCount=0;
    static int checCount=0;
    static int Field = 0;
    static int FieldB = 0;
    static int FieldU = 0;
    static int FieldR = 0;
    static int FieldC = 0;

    static boolean flagOpen = false;
    static boolean flagSearch = false;
    static ArrayList<BufferedReader> BusinessBr = new ArrayList();
    static ArrayList<BufferedReader> checkBr = new ArrayList();
    static ArrayList<BufferedReader> ReviewBr = new ArrayList();
    static ArrayList<BufferedReader> UserBr = new ArrayList();

    static ArrayList<Integer> BusinessMost = new ArrayList();
    static ArrayList<Integer> UserMost = new ArrayList();
    static ArrayList<Integer> ReviewMost = new ArrayList();

    /** Creates new form Project */
    public Project() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        SearchButton = new javax.swing.JButton();
        Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        result = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        suggestTxt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        SelectFile = new javax.swing.JTextField();
        Select = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        notify = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        businessR = new javax.swing.JRadioButton();
        usersR = new javax.swing.JRadioButton();
        reviewR = new javax.swing.JRadioButton();
        checkR = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        SearchButton.setBackground(new java.awt.Color(255, 255, 255));
        SearchButton.setFont(new java.awt.Font("Tahoma", 1, 11));
        SearchButton.setText("Search");
        SearchButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        Search.setHighlighter(null);
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchKeyPressed(evt);
            }
        });

        result.setColumns(20);
        result.setRows(5);
        jScrollPane1.setViewportView(result);

        jLabel2.setText("Docs");

        jCheckBox2.setBackground(new java.awt.Color(51, 255, 255));
        jCheckBox2.setText("Enable proximity");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        suggestTxt.setColumns(20);
        suggestTxt.setRows(5);
        jScrollPane2.setViewportView(suggestTxt);

        jLabel1.setText("Suggest");

        Select.setBackground(new java.awt.Color(255, 255, 255));
        Select.setFont(new java.awt.Font("Tahoma", 1, 11));
        Select.setText("Select");
        Select.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        jLabel3.setText("Select a file to view");

        notify.setBackground(new java.awt.Color(51, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton1.setText("Show Most Popular Docs");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Γκόγκος Κώστας            Ζούμπος Μιχάλης ");

        jLabel5.setText("input query");

        jLabel6.setText("Field");

        businessR.setBackground(new java.awt.Color(102, 255, 255));
        buttonGroup1.add(businessR);
        businessR.setSelected(true);
        businessR.setText("Business");

        usersR.setBackground(new java.awt.Color(153, 255, 255));
        buttonGroup1.add(usersR);
        usersR.setText("Users");

        reviewR.setBackground(new java.awt.Color(153, 255, 255));
        buttonGroup1.add(reviewR);
        reviewR.setText("Reviews");

        checkR.setBackground(new java.awt.Color(153, 255, 255));
        buttonGroup1.add(checkR);
        checkR.setText("checkIn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel3)
                .addContainerGap(768, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(businessR)
                                    .addComponent(usersR)
                                    .addComponent(reviewR)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel6)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(174, 174, 174)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox2)
                                            .addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(SelectFile)
                                        .addGap(18, 18, 18)
                                        .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(notify, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                                    .addComponent(jLabel4)))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                        .addGap(53, 53, 53))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkR)
                        .addContainerGap(888, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(notify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(businessR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usersR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(reviewR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkR)
                            .addComponent(jCheckBox2))
                        .addGap(31, 31, 31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        jMenu1.add(Open);

        jMenuItem2.setText("Close");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        dispose();
        int i=Project.EXIT_ON_CLOSE;
}//GEN-LAST:event_jMenu1ActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
       
            flagOpen=true;
            //Deleting existing File in search Directory
            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file5 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business\\write.lock");
            File file6 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business\\segments.gen");
            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file7 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user\\write.lock");
            File file8 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user\\segments.gen");

            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file9 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review\\write.lock");
            File file10 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review\\segments.gen");

            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file11 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check\\write.lock");
            File file12 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check\\segments.gen");

            //Proximity directories


            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file13 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business\\write.lock");
            File file14 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business\\segments.gen");
            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file15 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user\\write.lock");
            File file16 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user\\segments.gen");

            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file17 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review\\write.lock");
            File file18 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review\\segments.gen");

            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file19 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check\\write.lock");
            File file20 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check\\segments.gen");



            //Most recent directories
            System.out.println("BusinessM");
             for(int i = 0; i<6 ;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\business\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\business\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\business\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\business\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file21 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\write.lock");
            File file22 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\business\\segments.gen");

            System.out.println("UserM");
            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file23 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user\\write.lock");
            File file24 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user\\segments.gen");
            System.out.println("ReviewM");
            for(int i = 0;i<6;i++){
                File file1 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\_"+i+".cfe");
                File file2 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\_"+i+".cfs");
                File file3 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\_"+i+".si");
                File file4 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\segments_"+i);
                if(file1.exists() && file1.delete()){
                    System.out.println(file1.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file2.exists() && file2.delete()){
                    System.out.println(file2.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file3.exists() && file3.delete()){
                    System.out.println(file3.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
                if(file4.exists() && file4.delete()){
                    System.out.println(file4.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            }
            File file25 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\write.lock");
            File file26 = new File("C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review\\segments.gen");


            ///

            if(file5.exists() && file5.delete()){
                System.out.println(file5.getName() + " is deleted!");
    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file6.exists() && file6.delete()){
                System.out.println(file6.getName() + " is deleted!");
    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file7.exists() && file7.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file8.exists() && file8.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file9.exists() && file9.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file10.exists() && file10.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
             if(file11.exists() && file11.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
             if(file12.exists() && file12.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file13.exists() && file13.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file14.exists() && file14.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file15.exists() && file15.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file16.exists() && file16.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file17.exists() && file17.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file18.exists() && file18.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file19.exists() && file19.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file20.exists() && file20.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file21.exists() && file21.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file22.exists() && file22.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file23.exists() && file23.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file24.exists() && file24.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file25.exists() && file25.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }
            if(file26.exists() && file26.delete()){

    	    }else{
    		System.out.println("Delete operation is failed.");
            }


            
            //End of Deleting existing file
            
            //String Input= FieldTxt.getText();
            /*if(businessR.isSelected()){
                for(int i = 0;i<15 ;i++){
                    BufferedReader br = new BufferedReader(new FileReader("Docs\\Business\\bus"+i+".txt"));
                    String B=br.readLine();
                    business.file.add(B);
                }
            }
            if(checkR.isSelected()){
                for(int i = 0;i<15 ;i++){
                    BufferedReader br = new BufferedReader(new FileReader("Docs\\CheckIn\\checkin"+i+".txt"));
                    String B=br.readLine();
                    checkIn.file.add(B);
                }
            }
            if(reviewR.isSelected()){
                for(int i = 0;i<15 ;i++){
                    BufferedReader br = new BufferedReader(new FileReader("Docs\\Review\\review"+i+".txt"));
                    String B=br.readLine();
                    review.file.add(B);
                }
            }
            if(usersR.isSelected()){
                for(int i = 0;i<15 ;i++){
                    BufferedReader br = new BufferedReader(new FileReader("Docs\\User\\user"+i+".txt"));
                    String B=br.readLine();
                    Users.file.add(B);
                }
            }*/
           /* System.out.println("Bussines");
              new ParserNew(BusinessBr, 1);
            System.out.println("check in");
              new ParserNew(checkBr,2);
            System.out.println("review");
              new ParserNew(ReviewBr,3);
            System.out.println("users");
              new ParserNew(UserBr,4);
            */
            notify.setText("Ready for Searching");
            
    }//GEN-LAST:event_OpenActionPerformed

    public static void showDoc(String[] parts,String path) throws FileNotFoundException, IOException {
        javax.swing.text.Highlighter h =  BoltResult.BoltText.getHighlighter();
        h.removeAllHighlights();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
       
        while((line=br.readLine())!=null){
            BoltResult.BoltText.append(""+line+"\n");
        }
        for (int i=0; i<parts.length; i++){

            String s1=BoltResult.BoltText.getText().toLowerCase();
           
            int point =s1.indexOf(parts[i].toLowerCase());
            while (point >= 0){
                int length = parts[i].length();
                try {
                    h.addHighlight(point, point + length , DefaultHighlighter.DefaultPainter);
                } catch (BadLocationException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                point =BoltResult.BoltText.getText().indexOf(parts[i], point + length);
            }
        }
        
    }

    private void Search(String text) throws ParseException {
            
          //  Field = FieldTxt.getText();
            if(businessR.isSelected()){
                
                try {
                    Directory.indexer = null;
                    if(prox==false){
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\business";
                    }else{
                         s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\business";
                    }
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Business\\";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    FieldB = 1;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(usersR.isSelected()){
                //Directory.indexLocation = null;
               
                try {
                    Directory.indexer = null;
                    if(prox==false){
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\user";
                    }else{
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\user";
                    }
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\User";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    FieldU = 1;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if(checkR.isSelected()){
                //Directory.indexLocation = null;
               
                try {
                    Directory.indexer = null;
                    if(prox==false){
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\check";
                    }else{
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\check";
                    }
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\CheckIn";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    FieldC = 1;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            else if(reviewR.isSelected()){
                //Directory.indexLocation = null;
                
                try {
                    Directory.indexer = null;
                    if(prox==false){
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Directories\\review";
                    }else{
                        s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesP\\review";
                    }
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Review";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    FieldR = 1;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            
            if(prox==false){
            try {
                flagSearch = true;
                result.setText("");
                suggestTxt.setText("");
                Directory.reader = DirectoryReader.open(FSDirectory.open(new File(Directory.indexLocation)));
                Directory.searcher = new IndexSearcher(Directory.reader);
                Directory.collector = TopScoreDocCollector.create(10, true);
                s = "";
                s = text;
                Query q = new QueryParser(Version.LUCENE_48, "contents", Directory.analyzer).parse(s);
                Directory.searcher.search(q, Directory.collector);
                ScoreDoc[] hits = Directory.collector.topDocs().scoreDocs;
                System.out.println("Found " + hits.length + " hits.");
                if (hits.length != 0) {
                    result.setText("");
                    suggestTxt.setText("");
                    for (int i = 0; i < hits.length; ++i) {
                        int docId = hits[i].doc;
                        Document d = Directory.searcher.doc(docId);
                        result.append((i + 1) + ". " + d.get("path") + "\n");
                        String a = d.get("path");
                        System.out.println(a);
                        if (a.contains("user.txt")) {
                            userCount++;
                        }
                        if (a.contains("business.txt")) {
                            busCount++;
                        }
                        if (a.contains("checkin.txt")) {
                            checCount++;
                        }
                        if (a.contains("review.txt")) {
                            revCount++;
                        }
                    }
                } else {
                    //Spell Checker
                    result.setText("");
                    suggestTxt.setText("");
                    String[] parts = s.split(" ");
                    try {
                        RAMDirectory directory = new RAMDirectory();
                        SpellChecker spellChecker = new SpellChecker(directory);
                        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
                        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
                        InputStreamReader inputstream = new InputStreamReader(new FileInputStream(new File("suggest.txt")), "UTF-8");
                        PlainTextDictionary dictionary = new PlainTextDictionary(inputstream);
                        spellChecker.indexDictionary(dictionary, config, true);
                        int sNumber = 6;
                        for (int i = 0; i < parts.length; i++) {
                            String[] suggestions = spellChecker.suggestSimilar(parts[i], sNumber);
                            if (suggestions != null && suggestions.length > 0) {
                                for (String word : suggestions) {
                                    suggestTxt.append("Did you mean:" + word + "\n");
                                }
                            } else {
                                suggestTxt.append("No suggestions found for word: " + parts[i]);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }



            } else{//Proximity
            try {
                //Proximity
                String text1="\"";
                text1+=text;
                text1+="\"";
                text1+="~200";
                text =text1;
                flagSearch = true;
                result.setText("");
                suggestTxt.setText("");
                Directory.reader = DirectoryReader.open(FSDirectory.open(new File(Directory.indexLocation)));
                Directory.searcher = new IndexSearcher(Directory.reader);
                Directory.collector = TopScoreDocCollector.create(15, true);
                s = "";
                s = text;
                Query q = new QueryParser(Version.LUCENE_48, "contents", Directory.analyzer).parse(s);
                Directory.searcher.search(q, Directory.collector);
                ScoreDoc[] hits = Directory.collector.topDocs().scoreDocs;
                System.out.println("Found " + hits.length + " hits.");
                if (hits.length != 0) {
                    result.setText("");
                    suggestTxt.setText("");
                    for (int i = 0; i < hits.length; ++i) {
                        int docId = hits[i].doc;
                        Document d = Directory.searcher.doc(docId);
                        result.append((i + 1) + ". " + d.get("path") + "\n");
                        String a = d.get("path");
                        System.out.println(a);
                       
                    }
                }
                else {
                    //Spell Checker
                    result.setText("");
                    suggestTxt.setText("");
                    String[] parts = s.split(" ");
                    try {
                        RAMDirectory directory = new RAMDirectory();
                        SpellChecker spellChecker = new SpellChecker(directory);
                        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_48);
                        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
                        InputStreamReader inputstream = new InputStreamReader(new FileInputStream(new File("suggest.txt")), "UTF-8");
                        PlainTextDictionary dictionary = new PlainTextDictionary(inputstream);
                        spellChecker.indexDictionary(dictionary, config, true);
                        int sNumber = 6;
                        for (int i = 0; i < parts.length; i++) {
                            String[] suggestions = spellChecker.suggestSimilar(parts[i], sNumber);
                            if (suggestions != null && suggestions.length > 0) {
                                for (String word : suggestions) {
                                    suggestTxt.append("Did you mean:" + word + "\n");
                                }
                            } else {
                                suggestTxt.append("No suggestions found for word: " + parts[i]);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }


    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        if(flagOpen!=true){
            new ErrorMesage().setVisible(true);
        }
        else{
            if(Project.Search.getText().isEmpty()&&prox==false){
                new ErrorSearch().setVisible(true);
            }
            else if(prox==true && Project.Search.getText().split(" ").length<2){
                
            }
            else{
                try {
                    Search(Project.Search.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_SearchButtonActionPerformed



    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        prox = !prox;
        System.out.println(prox);
    }//GEN-LAST:event_jCheckBox2ActionPerformed


    public void openDocs(String F){
        try {
            new BoltResult().setVisible(true);
            BoltResult.BoltText.setText("");
            String path = F ;
            BoltResult.BoltLink.setText(path);
            String[] query = Search.getText().split(" ");
            showDoc(query, path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        if(flagOpen!=true || flagSearch!=true){
            new ErrorMesage().setVisible(true);
        }
        else{
          
//           Field = FieldTxt.getText();
           String Fi = null;
           if(businessR.isSelected()){
                Fi="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Business\\"+SelectFile.getText()+".txt";
            }
           if(usersR.isSelected()){
                 Fi="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\User\\"+SelectFile.getText()+".txt";
            }
           if(checkR.isSelected()){
                 Fi="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\CheckIn\\"+SelectFile.getText()+".txt";
            }
           if(reviewR.isSelected()){
                 Fi="C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Review\\"+SelectFile.getText()+".txt";
            }
            File f=new File(Fi);

            if(SelectFile.getText().isEmpty() || !f.exists()){
                new Errordoc().setVisible(true);
            }
            else
                openDocs(Fi);
        }

    }//GEN-LAST:event_SelectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       result.setText("");
          
       ArrayList<String> elemen = new ArrayList();

       String s = null;
       if(businessR.isSelected()){
                if(Field != 1){
                try {
                    Directory.indexer = null;
                    s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\business";
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Business\\";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    Field = 1;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        else if(usersR.isSelected()){
                //Directory.indexLocation = null;
                if(Field != 2){
                try {
                    Directory.indexer = null;
                    s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\user";
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\User\\";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    Field = 2;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
            
            else if(reviewR.isSelected()){
                //Directory.indexLocation = null;
                if(Field != 4){
                try {
                    Directory.indexer = null;
                    s = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\DirectoriesM\\review";
                    Directory.indexLocation = s;
                    Directory.indexer = new Directory(s);
                    String st = "C:\\Users\\Kostas\\Documents\\NetBeansProjects\\Anaktisi\\Docs\\Review\\";
                    Directory.indexer.indexFileOrDirectory(st);
                    Directory.indexer.closeIndex();
                    Field = 4;
                } catch (IOException ex) {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
           
            if(businessR.isSelected() || reviewR.isSelected()){
               float mi =  (float) 0.5;

            for(float star= 5;star >0.0;star -= mi){
            try {
                flagSearch = true;
                result.setText("");
                suggestTxt.setText("");
                Directory.reader = DirectoryReader.open(FSDirectory.open(new File(Directory.indexLocation)));
                Directory.searcher = new IndexSearcher(Directory.reader);
                Directory.collector = TopScoreDocCollector.create(10, true);
               if(businessR.isSelected() || reviewR.isSelected() ){
                    s = "stars +"+star;
                 }
                

                System.out.println(star);
                Query q = new QueryParser(Version.LUCENE_48, "contents", Directory.analyzer).parse(s);
                Directory.searcher.search(q, Directory.collector);
                ScoreDoc[] hits = Directory.collector.topDocs().scoreDocs;
                System.out.println("Found " + hits.length + " hits.");

                if (hits.length != 0) {
                    for (int j = 0; j < hits.length; ++j) {
                        int docId = hits[j].doc;
                        Document d = Directory.searcher.doc(docId);
                        System.out.println("------------>"+d.get("path"));
                        elemen.add(d.get("path"));
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
           }


            }

       //User Most
            else{

                int mi = 1;

                for(float star = 4;star >=0;star --){
                    for(int sec =  10;sec > 0 ;sec--){
                       try {
                        flagSearch = true;
                        result.setText("");
                        suggestTxt.setText("");
                        Directory.reader = DirectoryReader.open(FSDirectory.open(new File(Directory.indexLocation)));
                        Directory.searcher = new IndexSearcher(Directory.reader);
                        Directory.collector = TopScoreDocCollector.create(10, true);
                        float temp_star =star + (float) sec/10;
                        if(usersR.isSelected()){
                             s = "average_stars +"+temp_star+"*";
                        }

                        System.out.println(temp_star);
                        Query q = new QueryParser(Version.LUCENE_48, "contents", Directory.analyzer).parse(s);
                        Directory.searcher.search(q, Directory.collector);
                        ScoreDoc[] hits = Directory.collector.topDocs().scoreDocs;
                        System.out.println("Found " + hits.length + " hits.");

                        if (hits.length != 0) {
                            for (int j = 0; j < hits.length; ++j) {
                                int docId = hits[j].doc;
                                Document d = Directory.searcher.doc(docId);
                                System.out.println("------------>"+d.get("path"));
                                elemen.add(d.get("path"));
                            }
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
                


            }
       
       for(int i=0;i<elemen.size();i++){
           result.append((i + 1) + ". " + elemen.get(i) + "\n");
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       System.exit(-1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed
        //Search(Project.Search.getText());
    }//GEN-LAST:event_SearchKeyPressed

    private Integer findDist(ArrayList<String> file, String S) {
        Integer dist = 1;
        Integer findet = 0;
        Integer lastFind = 0;
        Integer firstFind = 0;
        boolean flagFind = false;
        String[] sSplit=S.split(" ");
        for(int i = 0;i<file.size();i++){
            String[] lineSplit = file.get(i).split(" ");
            for(int j = 0;j<lineSplit.length;j++){
                dist++;
                for(int k=0;k<sSplit.length;k++){
                     if(lineSplit[j].equalsIgnoreCase(sSplit[k])){
                         if(flagFind == false){
                             firstFind = dist;
                             flagFind = true;
                         }
                         findet++;
                         lastFind = dist;
                     }
                }
                if(flagFind==true){   
                }
            }
        }
        System.out.println(findet+" "+dist);
        return lastFind-firstFind-findet;
    }
    private void writeToFile(BufferedWriter bw ,int type) throws IOException {
        if(type == 1){
            for(int i = 0;i < business.file.size();i++){
                bw.append(business.file.get(i)+"\n");
            }
        }
        else if(type == 2){
            for(int i = 0;i < checkIn.file.size();i++){
                bw.append(checkIn.file.get(i)+"\n");
            }
        }
        else if(type == 3){
            for(int i = 0;i < review.file.size();i++){
                bw.append(review.file.get(i)+"\n");
            }
        }
        else if(type ==4){
            for(int i = 0;i < Users.file.size();i++){
                bw.append(Users.file.get(i)+"\n");
            }
        }

    }
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run(){new Project().setVisible(true);}});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JMenuItem Open;
    public static javax.swing.JTextField Search;
    private javax.swing.JButton SearchButton;
    private javax.swing.JButton Select;
    public static javax.swing.JTextField SelectFile;
    public javax.swing.JRadioButton businessR;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton checkR;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JTextField notify;
    private static javax.swing.JTextArea result;
    private javax.swing.JRadioButton reviewR;
    public static javax.swing.JTextArea suggestTxt;
    private javax.swing.JRadioButton usersR;
    // End of variables declaration//GEN-END:variables


}
