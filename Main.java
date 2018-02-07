package VPTR;

import static VPTR.VirtualPageTableRegister.FRAME_NUMBER;
import static VPTR.VirtualPageTableRegister.PAGE_NUMBER;
import static VPTR.VirtualPageTableRegister.SECTOR_NUMBER;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Do you want to run a program? If yes,"
                    + " press 'y' button,else press any other button.");
            Scanner s = new Scanner(System.in);
            String work = s.nextLine();
            if (work.equals("y")) {
                System.out.println("########################################");
                System.out.println("the page # is " + PAGE_NUMBER);
                System.out.println("the frame # is " + FRAME_NUMBER);
                System.out.println("the sector # is " + SECTOR_NUMBER);
                System.out.println("########################################");
                Scanner scanner = new Scanner(System.in);
                System.out.println("How many pages do you need?");
                int pageNumber = Integer.parseInt(scanner.nextLine());
                System.out.println("How many frames are allocated?");
                int frameNumber = Integer.parseInt(scanner.nextLine());
                if(pageNumber>0 && pageNumber<=PAGE_NUMBER&& frameNumber>0
                        &&frameNumber<=FRAME_NUMBER){
                VirtualPageTableRegister v = 
                        new VirtualPageTableRegister(pageNumber, frameNumber);
                int i = 0;
                while (i < 2 * pageNumber) {
                    v.updateVPTR();
                    i++;
                }}
                else {
                    System.out.println("Invalid page number or frame number.");
                    continue;}
            } else {
                return;
            }
        }
    }
}
