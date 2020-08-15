package si.medius.studentskanaloga.beans;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 *SolverBean is used to handle business logic regarding the solvability of a given problem.
 *
 * @since 1.0.0
 * @author Tjasa Domadenik
 */
@RequestScoped
public class SolverBean implements Serializable {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SolverBean.class.getName());
    private static final long serialVersionUID = -5254169262662527230L;

    /**
     * Creates matrix for gaussian Elimination
     *
     * @param size size of given problem
     * @return matrix for given problem
     */
    private int[][] createMatrix(int size) {
        int[][] newTable = new int[size * size][size * size];
        for (int i = 0; i < size * size; i++) {
            newTable[i][i] = 1;
            int startRow = i - (i % size);
            int endRow = startRow + (size - 1);

            //column
            if (0 <= i + size && i + size < size * size)
                newTable[i][i + size] = 1;

            if (0 <= i - size && i - size < size * size)
                newTable[i][i - size] = 1;

            //row
            if (i + 1 < size * size && i + 1 <= endRow)
                newTable[i][i + 1] = 1;

            if (0 <= i - 1 && i - 1 < size * size && i - 1 >= startRow)
                newTable[i][i - 1] = 1;
        }

        return newTable;
    }

    /**
     * FindPivot searches for the first pivot (integer 1) in matrix from the starting row down the given column.
     * If no pivot is present it returns -1.
     *
     * @param matrix Gauss elimination matrix
     * @param startRow row in which the search starts
     * @param column column which is searched for the pivot
     * @return row where the pivot is present
     */
    private int findPivot(int[][] matrix, int startRow, int column) {
        for (int row = startRow; row < matrix.length; row++)
            if (matrix[row][column] == 1)
                return row;

        return -1;
    }

    /**
     * Performs the gauss elimination method.
     *
     * @param A Gauss elimination matrix
     * @param b problem we want to solve
     */
    private void gaussElimination(int[][] A, int[] b) {

        int nextUnfinishedRow = 0;
        int matrixSize = A.length;

        for (int col = 0; col < matrixSize; col++) {

            int pivotRow = findPivot(A, nextUnfinishedRow, col);

            /* no pivot? skip column*/
            if (pivotRow == -1) continue;

            /* put row to top */
            int[] temp = A[pivotRow];
            A[pivotRow] = A[nextUnfinishedRow];
            A[nextUnfinishedRow] = temp;

            int temp2 = b[pivotRow];
            b[pivotRow] = b[nextUnfinishedRow];
            b[nextUnfinishedRow] = temp2;

            for (int row = pivotRow + 1; row < matrixSize; row++) {
                //XOR the remaining rows with the pivot row (nextUnfinishedRow)
                if (A[row][col] == 1) {

                    for (int a = col; a < matrixSize; a++) {
                        A[row][a] ^= A[nextUnfinishedRow][a];
                    }

                    // XOR the row in b vector as well.
                    b[row] ^= b[nextUnfinishedRow];
                }
            }

            //update position of the next free row.
            nextUnfinishedRow++;
        }
    }

    /**
     * Gives the solution of gauss elimination method.
     * If there is no solution it returns null.
     *
     * @param A Gauss elimination matrix
     * @param b problem we want to solve
     * @return solution to the problem
     */
    private int[] backSubstitute(int[][] A, int[] b) {

        int matrixSize = b.length;
        int[] result = new int[matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            result[i] = 0;
        }

        for (int row = matrixSize; row-- != 0; ) {
            // Scan across the row to find the pivot, if one exists.
            int pivot = -1;

            for (int col = 0; col < matrixSize; col++) {
                if (A[row][col] == 1) {
                    pivot = col;
                    break;
                }
            }

            if (pivot == -1) {
                if (b[row] == 1)
                    return null;

            } else {

                result[row] = b[row];
                for (int col = pivot + 1; col < matrixSize; col++)
                    result[row] = (result[row] ^ (A[row][col] & result[col]));
            }
        }

        return result;
    }

    /**
     * Converts definition of the problem from string to integer array.
     *
     * @param definition definition of the problem
     * @return integer array of problem definition
     */
    private int[] convertStringToIntArray(String definition) {

        int[] numbers = new int[definition.length()];
        for (int i = 0; i < definition.length(); i++) {
            numbers[i] = Character.getNumericValue(definition.charAt(i)) ^ 1;
        }

        return numbers;
    }

    /**
     * Checks if lights out problem is solvable.
     *
     * @param definition definition of the problem
     * @return boolean
     */
    public boolean isSolvable(String definition) {

        int[] problem = convertStringToIntArray(definition);
        int[][] matrix = createMatrix((int) Math.sqrt(problem.length));

        Instant start = Instant.now();

        //solving the problem
        gaussElimination(matrix, problem);
        int[] solution = backSubstitute(matrix, problem);

        if (solution == null) {
            return false;
        }

        long steps = Arrays.stream(solution)
                .filter(value -> value == 1)
                .count();

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();
        log.info(String.format("Time elapsed solver: %d ns in %d steps", timeElapsed, steps));

        return true;

    }

}