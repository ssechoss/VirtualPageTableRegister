
package VPTR;

import java.util.Random;

public class Item {
    private int page;
    private int frame;
    private boolean isValid;
    private int sector;    
    private int count;
    
    public Item(){
    this.page =-1;
    this.frame=-1;
    this.isValid=false;
    this.count=-1;
    this.sector=-1;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return page + "\t" + frame+"\t"+  sector + "\t"+  isValid ;
    }
    
   
}
