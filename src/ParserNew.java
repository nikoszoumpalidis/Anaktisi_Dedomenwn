
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kostas
 */
public class ParserNew {
    public ParserNew( ArrayList<BufferedReader> b,int type) throws IOException{
        String line;
       // fillArray();

        if(type==1){
            for(int i = 0;i<b.size();i++){
                while((line=b.get(i).readLine())!=null){
                    business.businessAdd(line);
                }
            }
        }
        else if(type == 2){
            for(int i = 0;i<b.size();i++){
                while((line=b.get(i).readLine())!=null){
                    checkIn.checkAdd(line);
                }
            }
        }
        else if(type == 3){
            for(int i = 0;i<b.size();i++){
                while((line=b.get(i).readLine())!=null){
                   review.reviewAdd(line);
                }
            }
        }
        else{
            for(int i = 0;i<b.size();i++){
                while((line=b.get(i).readLine())!=null){
                   Users.userAdd(line);
                }
              }
          }
      }
}
