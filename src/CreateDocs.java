
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kostas
 */
public class CreateDocs {

    CreateDocs() {
        
            try {
                int i = 0;
                String line;
                BufferedWriter bw;
                for(int j = 0;j<business.file.size();j++) {
                    bw = new BufferedWriter(new FileWriter("Docs\\Business\\bus"+i+".txt"));
                    bw.append(business.file.get(j));
                    i++;
                    bw.close();
                }
                i = 0;
            for(int j = 0;j<Users.file.size();j++) {
                    bw = new BufferedWriter(new FileWriter("Docs\\User\\user"+i+".txt"));
                    bw.append(Users.file.get(j));
                    i++;
                    bw.close();
                }
                i=0;
                for(int j = 0;j<review.file.size();j++) {
                    bw = new BufferedWriter(new FileWriter("Docs\\Review\\review"+i+".txt"));
                    bw.append(review.file.get(j));
                    i++;
                    bw.close();
                }
                i=0;
                for(int j = 0;j<checkIn.file.size();j++) {
                    bw = new BufferedWriter(new FileWriter("Docs\\CheckIn\\checkin"+i+".txt"));
                    bw.append(checkIn.file.get(j));
                    i++;
                    bw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(CreateDocs.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    public void CreateDocs(BufferedReader br,int type){

    }

}
