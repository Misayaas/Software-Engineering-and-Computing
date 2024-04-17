// -*- Java -*-
/*
 * <copyright>
 *
 *  Copyright (c) 2002
 *  Institute for Information Processing and Computer Supported New Media (IICM),
 *  Graz University of Technology, Austria.
 *
 * </copyright>
 *
 * <file>
 *
 *  Name:    KWIC.java
 *
 *  Purpose: Demo system for practice in Software Architecture
 *
 *  Created: 11 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *    The basic KWIC system is defined as follows. The KWIC system accepts an ordered
 *  set of lines, each line is an ordered set of words, and each word is an ordered set
 *  of characters. Any line may be "circularly shifted" by repeadetly removing the first
 *  word and appending it at the end of the line. The KWIC index system outputs a
 *  listing of all circular shifts of all lines in alphabetical order.
 * </file>
 */

//package kwic.ms;

/*
 * $Log$
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is an implementation of the main/subroutine architectural solution
 * for the KWIC system. This solution is based on functional
 * decomposition of the system. Thus, the system is decomposed into a number of
 * modules, each module being a function. In this solution all functions share access
 * to data, which is stored in the "core storage". The system is decomposed into the
 * following modules (functions):
 * <ul>
 * <li>Master Control (main). This function controls the sequencing among the
 * other four functions.
 * <li>Input. This function reads the data lines from the input medium (file) and
 * stores them in the core for processing by the remaining modules. The characters are
 * stored in a character array (char[]). The blank space is used to separate words in
 * a particular line. Another integer array (int[]) keeps the starting indices of
 * each line in the character array.
 * <li>Circular Shift. This function is called after the input function has
 * completed its work. It prepares a two-dimensional integer array (int[][]) to keep
 * track of all circular shifts. The array is organized as follows: for each circular
 * shift, both index of its original line, together with the index of the starting word of
 * that circular shift are stored as one column of the array.
 * <li>Alphabetizing. This function takes as input the arrays produced by the input
 * and circular shift functions. It produces an array in the same format (int[][])
 * as that produced by circular shift function. In this case, however, the circular
 * shifts are listed in another order (they are sorted alphabetically).
 * <li>Output. This function uses the arrays produced by input and alphabetizing
 * function. It produces a nicely formated output listing of all circular shifts.
 * </ul>
 *
 * @author dhelic
 * @version $Id$
 */

public class KWIC {

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

    /*
     * Core storage for shared data
     *
     */

    /**
     * Input characters
     */

    private char[] chars_;

    /**
     * Array that keeps line indices (line index is the index of the first character of a line)
     */

    private int[] line_index_;

    /**
     * 2D array that keeps circular shift indices (each circular shift index is a column
     * in this 2D array, the first index is the index of the original line from line_index_
     * array, the second index is the index of the starting word from chars_ array of
     * that circular shift)
     */

    private int[][] circular_shifts_;

    /**
     * 2D array that keeps circular shift indices, sorted alphabetically
     */

    private int[][] alphabetized_;


//----------------------------------------------------------------------
/**
 * Constructors
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------
/**
 * Methods
 *
 */
//----------------------------------------------------------------------

//----------------------------------------------------------------------

    /**
     * This function controls all other functions in the system. It implements
     * the sequence of calls to other functions to obtain the desired functionality
     * of the system. Before any other function is called, main function checks the
     * command line arguments. The program expects exactly one command line argument
     * specifying the name of the file that contains the data. If the program have
     * not been started with proper command line arguments, main function exits
     * with an error message. Otherwise, the input function is called first to read the
     * data from the file. After that the circularShift and alphabetizing
     * functions are called to produce and sort the shifts respectivelly. Finally, the output
     * function prints the sorted shifts at the standard output.
     *
     * @param args command line argumnets
     */

    public static void main(String[] args) {
        KWIC kwic = new KWIC();
        kwic.input("Test_Case2.txt");
        kwic.circularShift();
        kwic.alphabetizing();
        kwic.output();
    }

//----------------------------------------------------------------------

    /**
     * Input function reads the raw data from the specified file and stores it in the core storage.
     * If some system I/O error occurs the program exits with an error message.
     * The format of raw data is as follows. Lines are separated by the line separator
     * character(s) (on Unix '\n', on Windows '\r\n'). Each line consists of a number of
     * words. Words are delimited by any number and combination of the space chracter (' ')
     * and the horizontal tabulation chracter ('\t'). The entered data is parsed in the
     * following way. All line separators are removed from the data, all horizontal tabulation
     * word delimiters are replaced by a single space character, and all multiple word
     * delimiters are replaced by a single space character. Then the parsed data is represented
     * in the core as two arrays: chars_ array and line_index_ array.
     *
     * @param file Name of input file
     */

    public void input(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            reader.close();

            StringBuilder builder = new StringBuilder();
            for(String l : lines){
                builder.append(l);
                builder.append(" ");
            }
            chars_ = builder.toString().toCharArray();

            line_index_ = new int[lines.size()];
            int index = 0;
            for(int i = 0; i < lines.size(); i++){
                line_index_[i] = index;
                index += lines.get(i).length() + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//----------------------------------------------------------------------

    /**
     * This function processes arrays prepared by the input
     * function and produces circular shifts of the stored lines. A circular
     * shift is a line where the first word is removed from the begin of a line
     * and appended at the end of the line. To obtain all circular shifts of a line
     * we repeat this process until we can't obtain any new lines. Circular shifts
     * are represented as a 2D array that keeps circular shift indices (each circular
     * shift index is a column in this 2D array, the first index is the index of
     * the original line from line_index_ array, the second index is the index of
     * the starting word from chars_ array of that circular shift)
     */

    public void circularShift() {
        ArrayList<int[]> shifts = new ArrayList<>();
        for(int i = 0; i < line_index_.length; i++){
            String line;
            if(i == line_index_.length - 1){
                line = new String(chars_, line_index_[i], chars_.length - line_index_[i]).trim();
            }
            else{
                line = new String(chars_, line_index_[i], line_index_[i + 1] - line_index_[i]).trim();
            }
            String[] words = line.split(" ");
            for(int j = 0; j < words.length; j++){
                shifts.add(new int[]{i, j});
            }
        }
        circular_shifts_ = shifts.toArray(new int[0][]);
    }

//----------------------------------------------------------------------

    /**
     * This function sorts circular shifts lines alphabetically. The sorted shifts
     * are represented in the same way as the unsorted shifts with the only difference
     * that now they are ordered alphabetically. This function implements binary search
     * to sort the shifts.
     */

    public void alphabetizing() {
        ArrayList<int[]> sortedShifts = new ArrayList<>(Arrays.asList(circular_shifts_));
        sortedShifts.sort((a, b) -> {
            String shiftA = getShift(a);
            String shiftB = getShift(b);
            return shiftA.compareTo(shiftB);
        });
        alphabetized_ = sortedShifts.toArray(new int[0][]);
    }

    public String getShift(int[] shift){
        String line;
        if(shift[0] == line_index_.length - 1){
            line = new String(chars_, line_index_[shift[0]], chars_.length - line_index_[shift[0]]).trim();
        }
        else{
            line = new String(chars_, line_index_[shift[0]], line_index_[shift[0] + 1] - line_index_[shift[0]]).trim();
        }
        String[] words = line.split(" ");
        String[] shiftedWords = new String[words.length];
        System.arraycopy(words, shift[1], shiftedWords, 0, words.length - shift[1]);
        System.arraycopy(words, 0, shiftedWords, words.length - shift[1], shift[1]);
        return String.join(" ", shiftedWords);
    }

//----------------------------------------------------------------------

    /**
     * This function prints the sorted shifts at the standard output.
     */

    public void output() {
        for(int[] shift : alphabetized_){
            System.out.println(getShift(shift));
        }
    }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
