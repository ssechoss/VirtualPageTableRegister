# VirtualPageTableRegister
The project is coded on java. 
It contains 3 classes: 
	Item: one lined item in VPTR, including: page, frame, sector, isValid, count (for FIFO algorithm).
	VirtualPageTableRegister: with each Program Counter generated, it updates the VPTR and shows whether there is a page fault and output logical and physical address.
	Main: Run the project continuously, until the operator chooses to exit.

Note:
1. The victim page is selected by FIFO algorithm.
	2. Each program will update PC randomly times. In this project, I set the update times is 
2* page number, to show my VPTR works well and make output not that long.

Caution:
	The input page number and frame number must be positive numbers. Otherwise, it won’t work well. (If the input page number or frame number is invalid(negative or greater than the max number), it will ask to reinput.)
