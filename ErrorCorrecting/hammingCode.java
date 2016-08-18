import java.util.*;
class hammingCode {
 static int set_parity_bit(int a[]) {
  int count = 0; //Initialising count to zero which will count the number of 1.
  int l = a.length;

  for (int i = 0; i & lt; l; ++i)
   if (a[i] == 1)
    ++count; //Incrementing count if value in array "a" is 1.

  if ((count % 2) == 0)
   return 0; //Returning 0 if even number of 1
  else
   return 1; //Returning 1 if odd number of 1
 }
 public static void main(String args[]) {
  Scanner scr = new Scanner(System.in);
  System.out.println("This is hamming code error detection and correction using EVEN parity");
  System.out.println();
  System.out.println("Enter 4 data bits.D4 D3 D2 D1");
  int n = 4; //set number of data bits
  int d[] = new int[4]; //Array to accept data bits
  for (int i = n - 1; i & gt; = 0; --i) {
   System.out.println("Enter the value of D" + (i + 1));
   d[i] = scr.nextInt();
  }

  /*.............. Formula for calculating 2^k&gt;=n+k+1 ...............*/

  int k = 0; // k stands for number of parity bits.Initializing it to zero.

  while (Math.pow(2, k) & lt;
   (n + k + 1)) // Calculating the value of k(number of parity bits).
  {
   ++k;
  }
  System.out.println();
  System.out.println(k + " parity bits are required for the transmission of data bits.");

  int p[] = new int[k]; //Array to store parity bits
  int h[] = new int[n + k + 1]; //Array to hold the hamming code.(n+k+1) as we start from pos 1.

  /********** Initialising array h[] to -1 ************/
  for (int i = 0; i & lt; 7; ++i)
   h[i] = -1;

  int count = 0;
  int c = 2;
  while (count & lt; 4) {
   ++c;
   if (c == 4)
    continue;

   h[c] = d[count];
   ++count;
  }
  int p1[] = {
   h[1],
   h[3],
   h[5],
   h[7]
  };
  int p2[] = {
   h[2],
   h[3],
   h[6],
   h[7]
  };
  int p3[] = {
   h[4],
   h[5],
   h[6],
   h[7]
  };
  int parity[] = new int[3];
  /************Setting the value of parity bit*************/
  parity[0] = set_parity_bit(p1);
  parity[1] = set_parity_bit(p2);
  parity[2] = set_parity_bit(p3);
  /************Inserting the parity bits in the hamming code**********/
  h[1] = parity[0];
  h[2] = parity[1];
  h[4] = parity[2];
  System.out.println("\nSENDER:");
  System.out.print("\nThe data bits entered are: ");
  for (int i = 3; i & gt; = 0; --i)
   System.out.print(d[i] + " ");

  System.out.println("\nThe Parity bits are: ");
  for (int i = 2; i & gt; = 0; --i)
   System.out.println("Value of P" + (i + 1) + " is " + parity[i] + " ");

  System.out.print("\nThe Hamming code is as follows :-\nD4 D3 D2 P3 D1 P2 P1 \n");
  for (int i = (n + k); i & gt; 0; --i)
   System.out.print(h[i] + " ");

  System.out.println();
  System.out.println("\nEnter the hamming code with error at any position of your choice.\nNOTE: ENTER A SPACE AFTER EVERY BIT POSITION.\nError should be present only at one bit position");
  for (int i = 7; i & gt; 0; --i)
   h[i] = scr.nextInt();

  int p4[] = {
   h[1],
   h[3],
   h[5],
   h[7]
  };
  int p5[] = {
   h[2],
   h[3],
   h[6],
   h[7]
  };
  int p6[] = {
   h[4],
   h[5],
   h[6],
   h[7]
  };

  parity[0] = set_parity_bit(p4);
  parity[1] = set_parity_bit(p5);
  parity[2] = set_parity_bit(p6);

  int position = (int)(parity[2] * Math.pow(2, 2) + parity[1] * Math.pow(2, 1) + parity[0] * Math.pow(2, 0));
  System.out.println("\nRECEIVER:");
  System.out.println("Error is detected at position " + position + " at the receiving end.");
  System.out.println("Correcting the error.... ");

  if (h[position] == 1)
   h[position] = 0;
  else
   h[position] = 1;

  System.out.print("The correct code is ");
  for (int i = 7; i & gt; 0; --i)
   System.out.print(h[i] + " ");
 }
}