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
 *  Name:    Alphabetizer.java
 *
 *  Purpose: Sorts circular shifts alphabetically
 *
 *  Created: 23 Sep 2002
 *
 *  $Id$
 *
 *  Description:
 *    Sorts circular shifts alphabetically
 * </file>
 */



/*
 * $Log$
 */

/**
 * An object of the Alphabetizer class sorts all lines, that it gets
 * from CircularShifter. Methods to access sorted lines are provided.
 *
 * @author dhelic
 * @version $Id$
 */

public class Alphabetizer {

//----------------------------------------------------------------------
/**
 * Fields
 *
 */
//----------------------------------------------------------------------

    /**
     * Array holding sorted indices of lines
     */

    private int sorted_[];

    /**
     * CircularShifter that provides lines
     */

    private CircularShifter shifter_;

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
     * Sorts all lines from the shifter.
     *
     * @param shifter the source of lines
     */

    public void alpha(CircularShifter shifter) {
        this.shifter_ = shifter;
        sorted_ = new int[shifter_.getLineCount()];
        for (int i = 0; i < sorted_.length; i++) {
            sorted_[i] = i;
        }
        // Perform heap sort on the sorted_ array
        for (int i = (sorted_.length / 2) - 1; i >= 0; i--) {
            siftDown(i, sorted_.length);
        }
        for (int i = sorted_.length - 1; i >= 0; i--) {
            int temp = sorted_[0];
            sorted_[0] = sorted_[i];
            sorted_[i] = temp;
            siftDown(0, i);
        }
    }

//----------------------------------------------------------------------

    /**
     * This method builds and reconstucts the heap for the heap sort algorithm.
     *
     * @param root   heap root
     * @param bottom heap bottom
     */

    private void siftDown(int root, int bottom) {
        int maxChild;
        boolean done = false;
        while ((root * 2 < bottom) && (!done)) {
            if (root * 2 + 1 == bottom) {
                maxChild = root * 2;
            } else if (shifter_.getLineAsString(sorted_[root * 2]).compareTo(shifter_.getLineAsString(sorted_[root * 2 + 1])) > 0) {
                maxChild = root * 2;
            } else {
                maxChild = root * 2 + 1;
            }

            if (shifter_.getLineAsString(sorted_[root]).compareTo(shifter_.getLineAsString(sorted_[maxChild])) < 0) {
                int temp = sorted_[root];
                sorted_[root] = sorted_[maxChild];
                sorted_[maxChild] = temp;
                root = maxChild;
            } else {
                done = true;
            }
        }
    }

//----------------------------------------------------------------------

    /**
     * Gets the line from the specified position.
     * String array representing the line is returned.
     *
     * @param line line index
     * @return String[]
     * @see #getLineAsString
     */

    public String[] getLine(int line) {
        return shifter_.getLine(sorted_[line]);
    }

//----------------------------------------------------------------------

    /**
     * Gets the line from the specified position.
     * String representing the line is returned.
     *
     * @param line line index
     * @return String[]
     * @see #getLine
     */

    public String getLineAsString(int line) {
        return shifter_.getLineAsString(sorted_[line]);
    }

//----------------------------------------------------------------------

    /**
     * Gets the number of lines.
     *
     * @return int
     */

    public int getLineCount() {
        return shifter_.getLineCount();
    }

//----------------------------------------------------------------------
/**
 * Inner classes
 *
 */
//----------------------------------------------------------------------

}
