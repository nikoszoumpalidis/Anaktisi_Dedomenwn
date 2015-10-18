
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class Parser {
     //static ArrayList<String> suggest = new ArrayList();
     static ArrayList<String> punctuation = new ArrayList();
     BufferedReader br = new BufferedReader(new FileReader("punctuation.txt"));
     String line2;

    public void fillArray() throws IOException{
        while((line2=br.readLine())!=null){
            punctuation.add(line2);
        }
    }
    private static String processWord(String input1) {
        boolean flagStart = false;
        boolean flagEnd = false;
        boolean Ok = false;
        int size = input1.length();

            for(int i = 0;i<punctuation.size();i++){
                if(input1.startsWith(punctuation.get(i))){
                       
                        input1=input1.substring(1);
                }   
                if(input1.endsWith(punctuation.get(i))){
                    int size1 = input1.length();
                    size1-=1;
                    input1=input1.substring(0,size1);
                }
            }
        String backSpace ="\\n";
        
        if(input1.contains(backSpace)){    
            String input2;
            input1 = input1.replace(backSpace,"\n");
        }
        return(input1);
    }
    boolean checkStart(String A){

        for(int i =0;i<punctuation.size();i++){
            if(A.startsWith(punctuation.get(i))){
                return false;
            }
        }
        return true;
    }
    boolean checkEnd(String A){
        for(int i =0;i<punctuation.size();i++){
            if(A.endsWith(punctuation.get(i))){
                return false;
            }
        }
        return true;
    }
   /* public boolean check(String A){
        for(int i=0;i<suggest.size();i++){
            if(suggest.get(i).equalsIgnoreCase(A)){
                return false;
            }
        }
        return true;
    }*/
    public Parser(BufferedReader b,int type) throws IOException{
        String line;
        fillArray();
        boolean Ok =false;
        boolean OkS = false;
        boolean OkF = false;
        if(type==1){
            while((line=b.readLine())!=null){

                String[] data = line.split(" ");
                String procLine="";
                String dataIn="";
                for(int i = 0;i<data.length;i++){
                    Ok =false;
                    OkS=false;
                    OkF=false;
                    procLine= data[i];
                    while(!Ok){
                       procLine= processWord(procLine);
                       OkS=checkStart(procLine);
                       OkF=checkEnd(procLine);
                       if(OkS == true && OkF ==true){
                            Ok = true;
                       }
                    }
                    /*if(suggest.size()==0)
                        suggest.add(procLine);
                    else if(check(procLine)==true){
                        suggest.add(procLine);
                    }*/
                    
                    dataIn+=procLine+" ";


                }
                business.businessAdd(dataIn);

            }

        }
        else if(type == 2){
            while((line=b.readLine())!=null){

                String[] data = line.split(" ");
                String procLine="";
                String dataIn="";
                for(int i = 0;i<data.length;i++){
                    Ok =false;
                    OkS=false;
                    OkF=false;
                    procLine= data[i];
                    while(!Ok){
                       procLine= processWord(procLine);
                       OkS=checkStart(procLine);
                       OkF=checkEnd(procLine);
                       if(OkS == true && OkF ==true){
                            Ok = true;
                       }
                    }
                    /*if(suggest.size()==0)
                        suggest.add(procLine);
                    else if(check(procLine)==true){
                        suggest.add(procLine);
                    }*/
                    dataIn+=procLine+" ";
                }
                checkIn.checkAdd(dataIn);
            }
        }
        else if(type == 3){
            while((line=b.readLine())!=null){
               
                String[] data = line.split(" ");
                String procLine="";
                String dataIn="";
                for(int i = 0;i<data.length;i++){
                    Ok =false;
                    OkS=false;
                    OkF=false;
                   procLine= data[i];
                    while(!Ok){
                       procLine= processWord(procLine);
                       OkS=checkStart(procLine);
                       OkF=checkEnd(procLine);
                       if(OkS == true && OkF ==true){
                            Ok = true;
                       }
                    }
                   /*if(suggest.size()==0)
                        suggest.add(procLine);
                    else if(check(procLine)==true){
                        suggest.add(procLine);
                    }*/
                    

                    dataIn+=procLine+" ";
                   

                }
               
               review.reviewAdd(dataIn);

            }
        }
        else{
            while((line=b.readLine())!=null){

                String[] data = line.split(" ");
                String procLine="";
                String dataIn="";
                for(int i = 0;i<data.length;i++){
                   Ok =false;
                   OkS=false;
                   OkF=false;
                   procLine= data[i];
                   while(!Ok){
                       procLine= processWord(procLine);
                       OkS=checkStart(procLine);
                       OkF=checkEnd(procLine);
                       if(OkS == true && OkF ==true){
                            Ok = true;
                       }
                   }
                   /*if(suggest.size()==0)
                        suggest.add(procLine);
                   else if(check(procLine)==true){
                        suggest.add(procLine);
                   }*/

                   dataIn+=procLine+" ";


                   }
                   Users.userAdd(dataIn);
              }
          }
      }
  }
