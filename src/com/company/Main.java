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


//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    //Declares

    public static void main(String[] args) {
	// write your code here
        System.out.println("v0.01");
        System.out.println("FNA Reader (2019)");

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
        try {
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                System.err.println(line);
                // System.err.println(scanFile.nextLine());
                numLines += 1;

                //If the line starts with '>' it is a sequence ID, so store in in an array.
                //Should we preserve sequence order?

                //Or can we use java dicts? seqID:sequence

                //if line.upper() starts with '-', 'A', 'G', 'T' or 'U', 'C' is is a sequence
                //Add support for nonspecific NT's (purines and pyrimidines) etc? 'N', 'Y' ..
                //Reference for this.
                if (line.startsWith(">")) {
                    numSequences += 1;
                    //Add to array or dict
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(numLines);
        System.out.println(numSequences);
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