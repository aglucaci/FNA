package com.company;

/*
Read in .fasta or .fna (Fasta with Newick pasted below Seqs) file
fasta format:
>Sequence1
ATATATATATGCGGGCATCGATCGACTACTAGC
>Sequence2
GCATCGATCGACTACTAGCTATTGAGGTGATAT

FNA format:
>Sequence1
ATATATATATGCGGGCATCGATCGACTACTAGC
>Sequence2
GCATCGATCGACTACTAGCTATTGAGGTGATAT
((0,1));
*/


// Imports

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    //Class Declares
    //public static Dictionary CODONs = new Hashtable();
    public static Dictionary<String, Integer> CODONs = new Hashtable<String, Integer>();

    public static void main(String[] args) {
	    // write your code here
        System.out.println("v0.01.1");
        System.out.println("FNA Reader (2020)");

        //System.out.println("Reading 'example.fasta'");
        readFasta("example.fasta");
    }


    public static void readFasta(String fileName){
        System.out.println("Reading '" + fileName + "'");
        Scanner scanFile = null;
        //String fileName = "example.fasta"; // data file name

        try {
            scanFile = new Scanner(new File(fileName));
            //System.out.println("File found.. opening..");
            //System.out.println(scanFile.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(fileName + " not found");
        }

        int numLines = 0;
        int numSequences = 0;
        List<String> SeqIDs = new ArrayList<String>();
        List<Integer> SeqLengths = new ArrayList<>();
        int T = 0;
        int C = 0;
        int G = 0;
        int A = 0;

        try {
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                System.err.println(line);
                // System.err.println(scanFile.nextLine());
                numLines += 1; // Total number of lines in file.

                //If the line starts with '>' it is a sequence ID, so store in in an array.
                //Should we preserve sequence order?
                //Or can we use java dicts? seqID:sequence
                //if line.upper() starts with '-', 'A', 'G', 'T' or 'U', 'C' is is a sequence
                //Add support for nonspecif ic NT's (purines and pyrimidines) etc? 'N', 'Y' ..
                //Reference for this.

                if (line.startsWith(">")) {
                    numSequences += 1;
                    //Add to array or dict
                    SeqIDs.add(line.replace(">" ,""));
                    continue;
                }

                // Add to sequence length list.
                SeqLengths.add(line.length());

                // Check for multiple of 3.
                // TODO

                //Codon Counts
                if (multiple_of_three(line.length()) == true){
                    codon_frequencies(line);
                }



                // Loop over string
                for (int i = 0; i < line.length(); i++) {
                    // Count the nucleotide and get frequencies.
                    if (line.charAt(i) == 'T') {
                        //doThrow();
                        T += 1;
                    }

                    if (line.charAt(i) == 'C') {
                        //doThrow();
                        C += 1;
                    }

                    if (line.charAt(i) == 'G') {
                        //doThrow();
                        G += 1;
                    }

                    if (line.charAt(i) == 'A') {
                        //doThrow();
                        A += 1;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Report to user.

        double NTSum = T + C + G + A;

        System.out.println("Number of lines: " + numLines);
        System.out.println("Number of sequences: " + numSequences);
        System.out.println("Sequence IDs: " + SeqIDs);
        System.out.println("Sequence Lengths: " + SeqLengths);

        for (int n = 0; n < SeqLengths.size(); n++) {
            System.out.println(multiple_of_three(SeqLengths.get(n)));
        }

        System.out.println("T: " + T + " " + (T/NTSum));
        System.out.println("C: " + C + " " + (C/NTSum));
        System.out.println("G: " + G + " " + (G/NTSum));
        System.out.println("A: " + A + " " + (A/NTSum));
        System.out.println("Total chars: " + (int) NTSum);


        //Total number of codons..
        double CODONS_sum = 0;

        Enumeration enu = CODONs.elements();

        /*while (enu.hasMoreElements()) {
            System.out.println(enu.nextElement());
            value = (int) enu.nextElement();
            //CODONS_sum += (int) enu.nextElement();
        }*/

        for (Enumeration i = CODONs.elements(); i.hasMoreElements();) {
            //System.out.println("Value in Dictionary : " + i.nextElement());
            CODONS_sum += (int) i.nextElement();
        }

        System.out.println("Total number of CODONS: " + CODONS_sum);
        // Iterate over codons
        String key;
        for (Enumeration k = CODONs.keys(); k.hasMoreElements();) {
            key = (String) k.nextElement();
            System.out.println("Keys in Dictionary: " + key + " " + (CODONs.get(key)/ CODONS_sum));
        }


    }

    public static boolean multiple_of_three(int sequence_length) {
        if (sequence_length % 3 == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public static void codon_frequencies(String sequence) {

        //sliding windows of 3.
        String codon = "";
        int buffer = 0;

        for (int i = 0; i < sequence.length() / 3; i++) {
            codon = "";
            buffer = i * 3;
            //int value = 0;
            for (int n = 0; n < 3; n++) {
                codon += sequence.charAt(buffer + n);
            }

            //System.out.print(codon + "\n");
            if (CODONs.get(codon) == null) {
                CODONs.put(codon, 1);
            } else {
                //value = CODONs.get(codon);
                CODONs.put(codon, CODONs.get(codon) + 1);
            }


        }

    }

}


// END OF FILE


/*
[Functions]
        -Read in fasta
        -Store Sequence names
        -Store Sequences
        -
        - ..
        -Some descriptive statistics
        -Filename
        -Number of Sequences (Species?)
        -Sequence length range
        -Sequence length mean
        -Provide GC Content (%) (Total and by gene)
        -Calculate nucleotide frequency from input

        -Total branch length
        -Substitution rate?

*/

/*
Coding REFERENCEs:
https://beginnersbook.com/2013/12/java-string-startswith-method-example/
 */