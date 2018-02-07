
package VPTR;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class VirtualPageTableRegister {

    public static int PAGE_SIZE = 16;
    public static int FRAME_NUMBER = 64;
    public static int PAGE_NUMBER = 100;
    public static int SECTOR_NUMBER = 1024;

    private int pageNumber;
    private int frameNumber;
    private int counter;
    private Item[] virtualPageTableRegister;
    private Item[] copy;
    private int programCounter;
    private Stack<Integer> st;

    public VirtualPageTableRegister(int pageNumber, int frameNumber) {
        this.pageNumber = pageNumber;
        this.frameNumber = frameNumber;
        virtualPageTableRegister = new Item[frameNumber];
        copy = new Item[pageNumber];
        st = new Stack<Integer>();
        getRandSector();
        getRandFrame();
    }

    public void getRandSector() {
        Map<Integer, Integer> map = new HashMap();
        int i = 0;
        while (i < pageNumber) {
            Random rand = new Random();
            int value = rand.nextInt(SECTOR_NUMBER);
            if (map.containsValue(value)) {
                continue;
            }
            map.put(value, value);
            copy[i] = new Item();
            copy[i].setPage(i);
            copy[i].setSector(value);
            i++;
        }
    }

    public void getRandFrame() {
        Map<Integer, Integer> map = new HashMap();
        int i = 0;
        while (i < frameNumber) {
            Random rand = new Random();
            int value = rand.nextInt(FRAME_NUMBER);
            if (map.containsValue(value)) {
                continue;
            }
            virtualPageTableRegister[i] = new Item();
            map.put(value, value);

            st.push(value);
            i++;
        }
        System.out.println("**********************************************");
        System.out.print("The allocated frames are: ");
        for (int f : st) {
            System.out.print(f + " ");
        }
        System.out.println();
    }

    public void updateVPTR() {
        Random rand = new Random();
        programCounter = rand.nextInt(pageNumber * PAGE_SIZE);
        int offset = programCounter % PAGE_SIZE;
        int page = programCounter / PAGE_SIZE;
        int frame = getCorFrame(page);

        int physicalAddress = offset + frame * PAGE_SIZE;
        int logicalAddress = programCounter;

        System.out.println("---------------------VPTR---------------------");
        System.out.println("\tPage#\tFrame#\tSector#\tV/I");
        for (int i = 0; i < virtualPageTableRegister.length; i++) {
            if (virtualPageTableRegister[i].isValid()) {
                System.out.println("\t" 
                        + virtualPageTableRegister[i].toString());
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println("pc = " + programCounter + ", page = " + page 
                + ", offset = " + offset);
        System.out.println("logcial address = " + logicalAddress 
                + ", physical address = " + physicalAddress);
        System.out.println("**********************************************");
    }

    public int checkPage(int page) {//if VPTR has the page,return frame number
        int temp = -1;
        for (int i = 0; i < frameNumber; i++) {
            if (page == virtualPageTableRegister[i].getPage()) {
                return virtualPageTableRegister[i].getFrame();
            }
        }
        return temp;

    }

    public int getIndex() {//if VPTR is not full,get first empty index
        int temp = 0;
        for (int i = 0; i < frameNumber; i++) {
            if (!virtualPageTableRegister[i].isValid()) {
                return i;
            }
        }
        return temp;

    }

    public int getCorFrame(int page) {
        Item item = copy[page];
        if (checkPage(page) > -1) {//target page already in VPTR;
            System.out.println("NO PAGE FAULT.");
            return checkPage(page);
        } else if (st.isEmpty()) {//all allocated frames are in VPTR 
            int m = victimPage();
            st.push(virtualPageTableRegister[m].getFrame());
            virtualPageTableRegister[m] = copy[page];
            virtualPageTableRegister[m].setFrame(st.pop());
            counter++;
            virtualPageTableRegister[m].setCount(counter);
            virtualPageTableRegister[m].setValid(true);
            System.out.println("There is a page fault.");
            return virtualPageTableRegister[m].getFrame();
        } else {//exit free frames
            int j = getIndex();
            virtualPageTableRegister[j] = copy[page];
            virtualPageTableRegister[j].setFrame(st.pop());
            counter++;
            virtualPageTableRegister[j].setCount(counter);
            virtualPageTableRegister[j].setValid(true);
            System.out.println("There is a page fault.");
            return virtualPageTableRegister[j].getFrame();
        }
    }

    //the algorithm is FIFO
    public int victimPage() {//return vicitm page's index, not page number
        int temp = 0;
        for (int i = 0; i < frameNumber; i++) {
            if (virtualPageTableRegister[temp].getCount() > 
                    virtualPageTableRegister[i].getCount()) {
                temp = i;
            }
        }
        return temp;
    }
}
