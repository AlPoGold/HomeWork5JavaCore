import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TicTacToeField {
    public static void main(String[] args) {
        //create field
        int[] ticTacToeArray = {1, 0, 2, 0, 0, 1, 2, 0, 3};

        // Pack the values into an integer
        int packedValue = packTicTacToeField(ticTacToeArray);

        // Write the packed value to a file
        writePackedValueToFile(packedValue, "ticTacToeFile.txt");
        File writtenFile = new File(".\\ticTacToeFile.txt");

        System.out.println(writtenFile.length());
    }

    private static int packTicTacToeField(int[] array) {
        /**
         *      Inside the loop, it performs bitwise operations to pack each value into the packedValue.
         *
         *     (array[i] & 0b11) extracts the last two bits of the current array element.
         *     << (i * 2) shifts the extracted bits to the left by i * 2 positions to make room for the next value.
         *     |= is a bitwise OR assignment operator, which combines the current packed value with the new value.
         */
        int packedValue = 0;
        for (int i = 0; i < array.length; i++) {
            packedValue = packedValue | (array[i] & 0b11) << (i * 2); // Use bitwise operations to pack values
        }
        return packedValue;
    }

    // Write the packed value to a file
    private static void writePackedValueToFile(int packedValue, String fileName) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(fileName))) {

            /**
             * It uses a DataOutputStream to write the packed value into a file.
             * (packedValue >> 16) & 0xFF, (packedValue >> 8) & 0xFF,
             * and packedValue & 0xFF are used to extract the three bytes from the packed value.
             * The bitwise shift operations (>>) are used to isolate each byte, and & 0xFF ensures
             * that only the least significant 8 bits are considered.
             */
            // Write the packed value using three bytes
            outputStream.writeByte((packedValue >> 16) & 0xFF);
            outputStream.writeByte((packedValue >> 8) & 0xFF);
            outputStream.writeByte(packedValue & 0xFF);
            System.out.println("Values written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
